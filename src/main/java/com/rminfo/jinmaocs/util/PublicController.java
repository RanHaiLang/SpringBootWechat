package com.rminfo.jinmaocs.util;

import com.rminfo.jinmaocs.service.UeditorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Coral Yu on 2017/6/1.
 */
@RequestMapping("/currency")
@Controller
public class PublicController {

    @Autowired
    UeditorService ueditorService;



    /**
     * 下载文档
     * @param fileName
     * @param response
     */
    @RequestMapping(value="/download")
    public void download(String fileName, HttpServletResponse response){
        FileUtil fileUtil=new FileUtil();
        fileUtil.download(fileName,response);
    }
    /*下载apk*/
    @RequestMapping(value="/downloadUpdate")
    public void downloadUpdate(String fileName, HttpServletResponse response){
        FileUtil fileUtil=new FileUtil();
        fileUtil.downloadUpdate(fileName,response, ConfigUtil.getValue(ConfigConstants.APKPATH));
    }
    /*下载模板*/
    @RequestMapping(value="/downloadModel/{nameOut}")
    public void downloadModel(HttpServletResponse response, @PathVariable("nameOut") String nameOut){
        FileUtil fileUtil=new FileUtil();
        String name = nameOut + ".xlsx";
        fileUtil.downloadUpdate(name,response, ConfigUtil.getValue(ConfigConstants.DOCPATH));
    }
    /*下载附件*/
    @RequestMapping(value="/downloadDoc")
    public void downloadDoc(HttpServletResponse response, String downId){
        FileUtil fileUtil=new FileUtil();
        fileUtil.downloadUpdate(ueditorService.selectDocNewname(downId),response, ConfigUtil.getValue(ConfigConstants.DOCPATH));
    }
    /*下载主题图片*/
    @RequestMapping(value="/downloadimage")
    public void downloadimage(HttpServletResponse response, String filename){
        FileUtil fileUtil=new FileUtil();
        fileUtil.downloadUpdate(filename,response, ConfigUtil.getValue(ConfigConstants.FILEPATH));
    }
}
