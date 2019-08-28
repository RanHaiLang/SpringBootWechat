package com.rminfo.jinmaocs.controller;

import com.github.pagehelper.PageInfo;
import com.rminfo.jinmaocs.service.UeditorService;
import com.rminfo.jinmaocs.util.Commom;
import com.rminfo.jinmaocs.util.ConfigConstants;
import com.rminfo.jinmaocs.util.ConfigUtil;
import com.rminfo.jinmaocs.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by 磊 on 2017/8/11.
 */

/**
 * Created by 磊 on 2017/6/9.
 */
@Controller
@RequestMapping("/ueditor")
public class UeditorController {
    @Value("${jinmao.url}")
    private String url;
    @Autowired
    UeditorService ueditorService;
    /*保存ueditor中的内容，返回至一个画面*/
    @RequestMapping(value = "switchable")
    public ModelAndView switchable()  {
        ModelAndView mav = new ModelAndView("webPage/ueditor/switchable");
        return mav;

    }
    /*编辑*/
    @RequestMapping(value = "editorPage")
    public ModelAndView skip(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView mav = new ModelAndView("webPage/ueditor/homeUeditor");
        request.setAttribute("jinmaourl",url);
        //设置请求和响应的编码统一为UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String shuaxin = request.getParameter("shuaxin");
        String code = request.getParameter("ueditorCode");
        if (StringUtils.isEmpty(shuaxin)) {
            mav.addObject("shuaxin", 1);
        } else {
            mav.addObject("shuaxin", 2);
        }
        mav.addObject("ueditorOldValue", "");
        mav.addObject("ueditorCode", code);
        if (StringUtils.isEmpty(code)) {
            mav.addObject("ueditorOldValue", "");
            return mav;
        } else {
            if (StringUtils.isEmpty(ueditorService.selectUeditor(code))) {
                mav.addObject("ueditorOldValue", "");
            } else {
                mav.addObject("ueditorOldValue", URLDecoder.decode(ueditorService.selectUeditor(code), "UTF-8"));
            }
            return mav;
        }

    }
   /* @RequestMapping(value = "/newValue", method = RequestMethod.POST)
    @ResponseBody
    public String newValue(@RequestParam("code") String code){
       *//* String  ueditorValue = URLDecoder.decode(ueditorValue,"UTF-8")*//*
        return appScreensService.selectUeditor("1.1.1");
    }*/
    /*@RequestMapping(value = "/saveValue", method = RequestMethod.POST)
    @ResponseBody
    public void saveValue(@RequestParam("ueditorValue") String ueditorValue,@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=utf-8");
        Map map = new HashMap<>();
        map.put("ueditorValue",ueditorValue);
        map.put("code",code);
        appScreensService.updateUeditor(map);
       // ueditorValue = URLDecoder.decode(ueditorValue,"UTF-8");//对传过来的值进行解码
    }*/

    /*保存ueditor中的内容，返回至一个画面*/
    @RequestMapping(value = "saveUeditor", method = RequestMethod.POST)
    public ModelAndView saveUeditor(@RequestParam("ueditorCode") String ueditorCode, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ModelAndView mav = new ModelAndView("redirect:homePage");
        request.setAttribute("jinmaourl",url);
        //设置请求和响应的编码统一为UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //拿到编辑器的内容
        String content = request.getParameter("myEditor");
        //如果不为空
        if (content != null) {
            /*//将内容设置进属性
            request.setAttribute("content",content);
            //转发到content.jsp
            request.getRequestDispatcher("content.jsp").forward(request, response);
            mav.addObject("content", content);*/
            try {
                String ueditorValue = URLEncoder.encode(content, "UTF-8");
                Map map = new HashMap<>();
                map.put("ueditorValue", ueditorValue);
                map.put("code", ueditorCode);
                ueditorService.updateUeditor(map);
                /*mav.addObject("content", URLDecoder.decode(String.valueOf(map.get("ueditorValue")),"UTF-8"));*//*URLDecoder.decode(ueditorValue,"UTF-8")*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.getWriter().append("内容为空!");
            mav.addObject("content", "内容为空!");
        }
        return mav;

    }

    /*返回一个不可编辑的页面*/
    @RequestMapping(value = "lookNotice", method = RequestMethod.GET)
    public ModelAndView fanhui(@RequestParam("ueditorCode") String code, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ModelAndView mav = new ModelAndView("webPage/ueditor/seepage");
        request.setAttribute("jinmaourl",url);
        //设置请求和响应的编码统一为UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        mav.addObject("ueditorCode", code);
        if (StringUtils.isEmpty(ueditorService.selectUeditor(code))) {
            mav.addObject("ueditorOldValue", "");
        } else {
            mav.addObject("ueditorOldValue", URLDecoder.decode(ueditorService.selectUeditor(code), "UTF-8"));/*URLDecoder.decode(ueditorValue,"UTF-8")*/
        }
        return mav;
    }


    /*主页分页*/
    @RequestMapping(value = "/homePage")
    @ResponseBody
    public ModelAndView homePage(@RequestParam(required = false, defaultValue = "1") int page,
                                 @RequestParam(required = false, defaultValue = "10") int rows, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("webPage/ueditor/homePage");
        request.setAttribute("jinmaourl",url);
        Map map = new HashMap<>();
        int total = ueditorService.selectPagesCount();
        int start = (page - 1) * rows;
        int stop = ((page - 1) * rows) + rows;
        map.put("start", start);
        map.put("stop", stop);
        List<Map> list = ueditorService.selectPages(map);
        PageInfo pageInfo = new PageInfo<>(list);
        pageInfo.setTotal(total);
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(rows);
        int num = total % rows;
        if (num != 0) {
            pageInfo.setPages(total / rows + 1);
        } else {
            pageInfo.setPages(total / rows);
        }
        mav.addObject("pageInfo", pageInfo);
        mav.addObject("page", page);
        mav.addObject("rows", rows);
        mav.addObject("result", request.getParameter("updateFile"));
        return mav;
    }

    /*判断code是否重复*/
    @RequestMapping(value = "/manyCode", method = RequestMethod.POST)
    @ResponseBody
    public int manyCode(@RequestParam("ueditorCode") String code) {
        int i = ueditorService.selectCodeCount(code);
        return i;
    }

    /*获取图片路径*/
    @RequestMapping(value = "/getPath", method = RequestMethod.POST)
    @ResponseBody
    public String getPath() {
        String i = ConfigUtil.getValue("formalUrl");
        return ConfigUtil.getValue("formalUrl");
    }

    /*新增公告*/
    @RequestMapping(value = "/addUeditor")
    @ResponseBody/*@RequestParam("ueditorCode") String ueditorCode,*/
    public ModelAndView addUeditor(@RequestParam("ueditorDesc") String ueditorDesc, @RequestParam("classNoctice") String classNoctice) {
        ModelAndView mav = new ModelAndView("redirect:homePage");
        String ueditorCode;
        Map map = new HashMap<>();
        String codeLast = ueditorService.selectnotcode();
        if (codeLast == null || codeLast == "") {
            ueditorCode = "NO1001";
        } else {
            String[] s = codeLast.split("NO");
            int codenum = Integer.parseInt(s[1]);
            int codenum1 = codenum + 1;
            String code = "NO" + codenum1;
            ueditorCode = code;
        }
        map.put("ueditorCode", ueditorCode);
        map.put("ueditorDesc", ueditorDesc);
        map.put("classNoctice", classNoctice);
        ueditorService.addUeditor(map);
        return mav;
    }

    /*接口，返回所有公告主题*/
    @RequestMapping(value = "/noticePage", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Map> noticePage(HttpServletRequest request) {
       /* @RequestMapping(value = "noticeLikePage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
        @ResponseBody
        public List<Map> noticeLikePage(@RequestParam("likeDesc") String likeDesc) {
            return ueditorService.selectLikePage(likeDesc);
        }*/
        String likeDesc = request.getParameter("likeDesc");
        String classNoctice = request.getParameter("classNoctice");
        if(classNoctice.equals("")){
            classNoctice=null;
        }
        if (StringUtils.isEmpty(likeDesc)) {
                return ueditorService.selectPageJson(classNoctice);
        } else {
            if (StringUtils.isEmpty(classNoctice)) {
                return ueditorService.selectLikePage(likeDesc);
            } else {
                Map map = new HashMap<>();
                map.put("class", classNoctice);
                map.put("likeNotice", likeDesc);
                return ueditorService.selectPageJsonLike(map);
            }
        }
    }

    /*
   上传文件
   */
    @RequestMapping(value = "/FileUplod", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView FileUplod(MultipartFile[] myfiles, String DocumentDesc, String noticeCode, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:homePage");
        int i;
        FileUtil fileUtil = new FileUtil();
        for (MultipartFile file : myfiles) {
            //此处MultipartFile[]表明是多文件,如果是单文件MultipartFile就行了
            if (file.isEmpty()) {
                System.out.println("文件未上传!");
            } else {
                try {
                    Map<String, Object> map = new HashMap<>();
                    map.put("noticeCode", noticeCode);//关联ID
                    map.put("DocumentDesc", DocumentDesc);//文件描述
                    //得到上传的文件名
                    String fileName = file.getOriginalFilename();
                    map.put("fileName", fileName);//文件名
                    //得到服务器项目发布运行所在地址
                    String path1 = ConfigUtil.getValue(ConfigConstants.DOCPATH);
                    //  此处未使用UUID来生成唯一标识,用日期做为标识
                    String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                    String newName = time + "_" + fileName;
                    map.put("newName", newName);
                    String path = path1 + newName;
                    //查看文件上传路径,方便查找
                    System.out.println(path);
                    //把文件上传至path的路径
                    File localFile = new File(path);
                    file.transferTo(localFile);
                    model.addObject("updateFile", 1);
                   /* map.put("result","1");*/
                    /*map.put("path",request.getContextPath()+"currency/downloadDoc/"+newName);*/
                    request.getContextPath();
                    ueditorService.addDoc(map);
                } catch (NullPointerException exception) {
                    /*map.put("result","0");*/
                    model.addObject("updateFile", 0);

                    exception.printStackTrace();
                } catch (IOException e) {
                    /*map.put("result","0");*/
                    model.addObject("updateFile", 0);
                    e.printStackTrace();
                }
            }
        }
        /*map.put("result","0");*/
        return model;
    }

    /*删除附件*/
    @RequestMapping(value = "/deleteDoc", method = RequestMethod.POST)
    @ResponseBody
    public int deleteDoc(@RequestParam("docId") String docId) {
        int i = 1;
        try {
            ueditorService.deleteDoc(docId);
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        return i;
    }

    /*z查询附件*/
    @RequestMapping(value = "/selectDoc", method = RequestMethod.POST)
    @ResponseBody
    public List<Map> selectDoc(@RequestParam("noticeCode") String noticeCode) {
        return ueditorService.selectDoc(noticeCode);
    }

    /*获取图片格式数组*/
    @RequestMapping(value = "/imageFormat", method = RequestMethod.POST)
    @ResponseBody
    public boolean imageFormat(@RequestParam("strFormat") String strFormat) {
        boolean blo = false;
        String format = "." + strFormat;
        String[] str = Commom.imageList();
        for (int i = 0; i < str.length - 1; i++) {
            if (format.equals(str[i])) {
                blo = true;
                break;
            }
        }
        return blo;
    }

    /*获取图片信息*/
    @RequestMapping(value = "/imageNews", method = RequestMethod.POST)
    @ResponseBody
    public Map imageNews(@RequestParam("noticeCodeT") String noticeCodeT) {
        Map map = ueditorService.selectimage(noticeCodeT);
        return map;
    }

    /*
  上传主题图片*/
    @RequestMapping(value = "/FileUplodimage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public int FileUplodimage(MultipartFile[] myfilesimage, String noticeCodeT, HttpServletRequest request) {
        /*ModelAndView model = new ModelAndView("redirect:homePage");*/
        int i = 0;
        for (MultipartFile file : myfilesimage) {
            //此处MultipartFile[]表明是多文件,如果是单文件MultipartFile就行了
            if (file.isEmpty()) {
                System.out.println("文件未上传!");
            } else {
                try {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", noticeCodeT);
                    //得到上传的文件名
                    String fileName = file.getOriginalFilename();
                    map.put("oldName", fileName);//文件名
                    //得到服务器项目发布运行所在地址
                    String path1 = ConfigUtil.getValue(ConfigConstants.FILEPATH);
                    //  此处未使用UUID来生成唯一标识,用日期做为标识
                    String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                    String newName = time + "_" + fileName;
                    map.put("newName", newName);
                    String path = path1 + newName;
                    //查看文件上传路径,方便查找
                    System.out.println(path);
                    //把文件上传至path的路径
                    File localFile = new File(path);
                    file.transferTo(localFile);
                    /*model.addObject("updateFile", 1);*/
                    i = 1;
                    request.getContextPath();
                    ueditorService.updateUeditorimage(map);
                } catch (NullPointerException exception) {
                    /*model.addObject("updateFile", 0);*/
                    i = 0;
                    exception.printStackTrace();
                } catch (IOException e) {
                    /*model.addObject("updateFile", 0);*/
                    i = 0;
                    e.printStackTrace();
                }
            }
        }
        //*map.put("result","0");*//*
        return i;
    }

    /*查询公告内容*/
    @RequestMapping(value = "/selectNoticeUpdate", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map selectNoticeUpdate() {
        Map map = new HashMap<>();
        map.put("notice", ueditorService.selectNoticeUpdate("notice"));
        String i = ueditorService.selectNoticeUpdate("notice");
        return map;
    }

    /*
  保存公告条内容*/
    @RequestMapping(value = "/saveNoticeUpdate", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ModelAndView saveNoticeUpdate(String noticeUpdateAll) {
        ModelAndView model = new ModelAndView("redirect:homePage");
        Map map = new HashMap<>();
        map.put("desc", noticeUpdateAll);
        map.put("code", "notice");
        ueditorService.saveNoticeUpdate(map);
        return model;
    }

    /*返回主题图片路径*/
    @RequestMapping(value = "/imagePath", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String imagePath(@RequestParam("noticeCode") String noticeCode) {
        String name = ueditorService.selectimagePath(noticeCode);
        String path = ConfigUtil.getValue("formalUrl") + name;
        return path;
    }

    /*返回图片路径，封装到map中*/
    @RequestMapping(value = "/imagePathapq", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map imagePathapq() {
        Map map = new HashMap<>();
        map.put("imgUrl", ConfigUtil.getValue("formalUrl"));
        return map;
    }

    /*前五条公告*/
    @RequestMapping(value = "/noticeFive", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map noticeFive() {
        Map map = new HashMap<>();
        map.put("start", 0);
        map.put("stop", 5);
        List<Map> list = ueditorService.selectPagesFive(map);
        Map map2 = new HashMap();
        map2.put("data",list);
        return map2;
    }

    /*单选框选择类别*/
    @RequestMapping(value = "/selectClass", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Map> selectClass() {
        return ueditorService.selectClass();
    }


}
