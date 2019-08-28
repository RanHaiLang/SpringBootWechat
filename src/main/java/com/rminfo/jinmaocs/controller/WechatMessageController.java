package com.rminfo.jinmaocs.controller;

import com.alibaba.fastjson.JSONObject;
import com.rminfo.jinmaocs.entity.AccessToken;
import com.rminfo.jinmaocs.entity.Constant;
import com.rminfo.jinmaocs.util.CheckUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rminfo.jinmaocs.util.HttpRequestUtil.doGetStr;
import static com.rminfo.jinmaocs.util.HttpRequestUtil.doPostStr;

/**
 * Created with IntelliJ IDEA.
 * User: SeaRan
 * Date: 2019/8/7
 * Time: 10:12
 * 项目名：jinmaocs
 * 描述：微信小程序
 * Description: No Description
 */
@RestController
@RequestMapping("wechatmessage")
public class WechatMessageController {

    @Autowired
    private Constant constant;

    //获取token路径
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private static String mAccessToken="";
    //过期时间为7200秒
    private static int Expires_Period = 7200;

    private static int GetAccessToken_Time=0;





    /**
     * 微信接入
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/connect",method = {RequestMethod.GET, RequestMethod.POST})
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        response.setCharacterEncoding("UTF-8");

        boolean isGet = request.getMethod().toLowerCase().equals("get");

        if(isGet){
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");


            PrintWriter out = response.getWriter();

            if(CheckUtil.checkSignature(signature, timestamp, nonce)){
                //如果校验成功，将得到的随机字符串原路返回
                out.print(echostr);
            }
        }else {
            acceptMessage(request,response);
        }

    }



    /**
     * 聊天处理方法 发送数据
     * @param request 作用域
     * @param response 作用域
     * @see  Exception
     */
    public void acceptMessage(HttpServletRequest request,HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String str = null;

        //将request请求，传到Message工具类的转换方法中，返回接收到的Map对象

        Map<String, String> map = xmlToMap(request);

        //从集合中，获取XML各个节点的内容

        String ToUserName = map.get("ToUserName");

        String FromUserName = map.get("FromUserName");

        String CreateTime = map.get("CreateTime");

        String MsgType = map.get("MsgType");

        String Content = map.get("Content");

        String MsgId = map.get("MsgId");

        if (MsgType.equals("text")) {//判断消息类型是否是文本消息(text)
            boolean result=Content.matches("[0-9]+");
            if(result){


            }else {

            }




        }
    }


    /**
     * 发送信息:参数视情况而定
     * @param openid（必须）
     * @param evt_code
     * @param desc
     * @param datetime
     * @param formId（必须）
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/sendmessage",method = RequestMethod.POST)
    public JSONObject sendTextMessageToUser(String openid,String evt_code,String desc,String datetime,String formId) throws IOException {
        //模板id
        String modelId = "pgGuRC-OM_MGO17f6uvn2qEhI915dp_bbdmqq8RT5vI";
        String json = "{\n" +
                "           \"touser\":\""+openid+"\",\n" +
                "           \"template_id\":\""+modelId+"\",\n" +
                "           \"page\":\"pages/baoxiu/baoxiu\",\n"+
                "           \"form_id\":\""+formId+"\",  \n" +
                "           \"data\":{\n" +
                "                   \"keyword1\": {\n" +
                "                       \"value\":\"安全隐患\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\":{\n" +
                "                       \"value\":\""+evt_code+"\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword3\": {\n" +
                "                       \"value\":\""+desc+"\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword4\": {\n" +
                "                       \"value\":\""+datetime+"\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        //获取access_token
        AccessToken accessToken = getAccessToken();
        //发送消息路径
        String action = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="+accessToken.getToken();
        System.out.println("json:"+json);

        JSONObject jsonObject = doPostStr(action,json);

        return jsonObject;
    }



    /**
     * 获取AccessToken
     * @return 返回拿到的access_token及有效期
     */
    public AccessToken getAccessToken() throws IOException{
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID",constant.getAppid() ).replace("APPSECRET", constant.getSecret());//将URL中的两个参数替换掉
        //如果为空，或者过期，需要重新获取
        if (StringUtils.isEmpty(mAccessToken) || HasExpired())
        {
            //获取
            JSONObject jsonObject = doGetStr(url);//使用刚刚写的doGet方法接收结果
            if(jsonObject!=null){ //如果返回不为空，将返回结果封装进AccessToken实体类
                token.setToken(jsonObject.getString("access_token"));//取出access_token
                token.setExpiresIn(jsonObject.getInteger("expires_in"));//取出access_token的有效期
                mAccessToken = jsonObject.getString("access_token");
                Date date = new Date();
                int timestamp = Integer.parseInt(String.valueOf(date.getTime()/1000));
                GetAccessToken_Time = timestamp;
            }
        }else {
            token.setToken(mAccessToken);
        }


        return token;
    }

    /**
     * 判断Access_token是否过期
     * @return
     */
    private static Boolean HasExpired()
    {
        if (GetAccessToken_Time != 0)
        {
            //过期时间，允许有一定的误差，一分钟。获取时间消耗
            Date date = new Date();//当前时间
            int timestamp = Integer.parseInt(String.valueOf(date.getTime()/1000));
            if((timestamp-GetAccessToken_Time)>Expires_Period){
                return true;
            }
        }
        return false;
    }



    /**

     * 新建方法，将接收到的XML格式，转化为Map对象

     * @param request 将request对象，通过参数传入

     * @return 返回转换后的Map对象

     */

    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {

        Map<String, String> map = new HashMap<String, String>();

        //从dom4j的jar包中，拿到SAXReader对象。

        SAXReader reader = new SAXReader();



        InputStream is = request.getInputStream();//从request中，获取输入流

        Document doc =  reader.read(is);//从reader对象中,读取输入流

        Element root = doc.getRootElement();//获取XML文档的根元素

        List<Element> list = root.elements();//获得根元素下的所有子节点

        for (Element e : list) {

            map.put(e.getName(), e.getText());//遍历list对象，并将结果保存到集合中

        }
        is.close();
        return map;

    }



}
