package com.rminfo.jinmaocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created with IntelliJ IDEA.
 * User: SeaRan
 * Date: 2019/8/9
 * Time: 10:18
 * 项目名：jinmaocs
 * 描述：TODO
 * Description: No Description
 */

//继承SpringBootServletInitializer子类
public class ServletInitializer extends SpringBootServletInitializer {

    //重写configure方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JinmaocsApplication.class);
    }
}
