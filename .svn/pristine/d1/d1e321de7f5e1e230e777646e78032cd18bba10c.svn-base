package com.rminfo.jinmaocs.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by carl on 2017/1/11.
 */
public class ConfigUtil {
    public static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(ConfigUtil.class.getResource("/").getPath()+"application.properties"));
            properties.load(new FileInputStream(ConfigUtil.class.getResource("/").getPath()+"application-" + properties.getProperty("spring.profiles.active") + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(5%2);
    }
    public static String[]  videoList(){
        String  video=ConfigUtil.getValue("videoNamesArray");
        String[]  videoList=video.split(",");
        return videoList;
    }
}
