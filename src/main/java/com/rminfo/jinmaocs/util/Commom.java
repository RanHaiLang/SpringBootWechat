package com.rminfo.jinmaocs.util;

/**
 * Created by Coral Yu on 2017/8/1.
 */
public class Commom {
    public  static  String[]  videoList(){
       String  video= ConfigUtil.getValue("videoNamesArray");
        String[]  videoList=video.split(",");
        return videoList;
    }
    public  static  String[]  imageList(){
        String  image= ConfigUtil.getValue(ConfigConstants.EXTENDNAMESARRAY);
        String[]  imageList=image.split(",");
        return imageList;
    }
}
