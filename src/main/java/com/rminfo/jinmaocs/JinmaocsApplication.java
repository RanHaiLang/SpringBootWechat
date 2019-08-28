package com.rminfo.jinmaocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages = "com.rminfo.jinmaocs.mapper")
public class JinmaocsApplication {


    public static void main(String[] args) {
        SpringApplication.run(JinmaocsApplication.class, args);
    }

}
