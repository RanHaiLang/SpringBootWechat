package com.rminfo.jinmaocs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rminfo.jinmaocs.entity.AccessToken;
import com.rminfo.jinmaocs.entity.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

import static com.rminfo.jinmaocs.util.HttpRequestUtil.doGetStr;
import static com.rminfo.jinmaocs.util.HttpRequestUtil.doPostStr;

/**
 * Created with IntelliJ IDEA.
 * User: SeaRan
 * Date: 2019/8/20
 * Time: 17:08
 * 项目名：jinmaocs
 * 描述：微信公众号
 * Description: No Description
 */
@RestController
@RequestMapping("wechatpublic")
public class WechatPublicNumberController {
    @Autowired
    private Constant constant;

    //获取token路径
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private static String mAccessToken="";
    //过期时间为7200秒
    private static int Expires_Period = 7200;

    private static int GetAccessToken_Time=0;


    /**
     * 消息推送
     * @param unionid 开放者平台id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/sendmessages",method = RequestMethod.POST)
    public JSONObject sendTextMessageToUsers(@RequestParam(defaultValue = "")String unionid) throws IOException {
        //获取微信公众号的所有用户
        JSONObject o = getwechcatalluser("");

        //微信公众号openid数组
        JSONArray jsonArray = o.getJSONObject("data").getJSONArray("openid");

        //获取access_token
        AccessToken accessToken = getAccessToken();
        String json=null;
        //循环遍历公众号openid
        for (int i=0;i<jsonArray.size();i++){
            String openid = (String) jsonArray.get(i);
            //获取微信公众号用户的信息
            String url ="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken.getToken()+"&openid="+openid+"&lang=zh_CN";
            JSONObject jsonObject = doGetStr(url);

            if(jsonObject.get("unionid").equals(unionid)){//小程序unionid与公众号unionid对比是否相同
                System.out.println(jsonObject);
                //模板id
                String modelId = constant.getPmodelId();


                /*json参数说明：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277*/
                json = "{\n" +
                        "           \"touser\":\""+jsonObject.get("openid")+"\",\n" +
                        "           \"template_id\":\""+modelId+"\",\n" +
                        "           \"url\":\"https://www.baidu.com/\",\n"+
                        /*"           \"miniprogram\":{\n" +
                        "               \"appid\":\""+constant.getAppid()+"\",\n" +
                        "               \"pagepath\":\"pages/index/index\"\n" +
                        "           },\n"+*/
                        "           \"data\":{\n" +
                        "                   \"first\": {\n" +
                        "                       \"value\":\"安全隐患\",\n" +
                        "                       \"color\":\"#173177\"\n" +
                        "                   },\n" +
                        "                   \"keyword1\":{\n" +
                        "                       \"value\":\"111\",\n" +
                        "                       \"color\":\"#173177\"\n" +
                        "                   },\n" +
                        "                   \"keyword2\": {\n" +
                        "                       \"value\":\"222\",\n" +
                        "                       \"color\":\"#173177\"\n" +
                        "                   },\n" +
                        "                   \"keyword3\": {\n" +
                        "                       \"value\":\"333\",\n" +
                        "                       \"color\":\"#173177\"\n" +
                        "                   },\n" +
                        "                   \"remark\":{\n" +
                        "                       \"value\":\"请尽快处理！\",\n" +
                        "                       \"color\":\"#173177\"\n" +
                        "                   }\n" +
                        "           }\n" +
                        "       }";

            }
        }
        //发送消息路径
        String action = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken.getToken();
        System.out.println("json:"+json);

        JSONObject jsonObject = null;

        if(json!=null){
            jsonObject = doPostStr(action,json);
        }


        return jsonObject;
    }


    /**
     *获取微信公众号的所有用户
     * @param nextopenid
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getwechatuser")
    @ResponseBody
    public JSONObject getwechcatalluser(String nextopenid) throws IOException {
        //获取access_token
        AccessToken accessToken = getAccessToken();
        String action = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken.getToken()+"&next_openid="+nextopenid;
        JSONObject jsonObject = doGetStr(action);
        return jsonObject;
    }

    /**
     * 获取AccessToken
     * @return 返回拿到的access_token及有效期
     */
    public AccessToken getAccessToken() throws IOException{
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID",constant.getPappid() ).replace("APPSECRET", constant.getPsecret());//将URL中的两个参数替换掉
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

}
