package com.rminfo.jinmaocs.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rminfo.jinmaocs.entity.R5rmAppUser;
import com.rminfo.jinmaocs.service.R5rmAppUserService;
import com.rminfo.jinmaocs.util.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
public class GetUsersController {

    protected static final Logger logger = LoggerFactory.getLogger(GetUsersController.class);

    @Autowired
    private R5rmAppUserService r5rmAppUserService;

    /**
     * 获取用户分页列表
     * @param page
     * @param rows
     * @param param
     * @return
     */
    //    @RequestMapping(value = "/getMoreBook",produces="application/json;charset=utf-8")//处理返回中文乱码
    @GetMapping("/list")
    public JSONObject getList(@RequestParam(required = false, name = "page", defaultValue = "1") String page,
                              @RequestParam(required = false, name = "rows", defaultValue = "10") String rows,
                              String param) {
        JSONObject jsonObject = DataUtil.getSuccess();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("page", page);
            map.put("rows", rows);
            PageInfo<R5rmAppUser> pageInfo = new PageInfo<>(r5rmAppUserService.selectAll(map));
            jsonObject.put("data", JSONObject.toJSON(pageInfo));

        } catch (Exception e) {
            logger.error(e.getMessage());
            return DataUtil.getError(e.getMessage());
        }
        jsonObject.put("param", param);
        jsonObject.put("page", page);
        jsonObject.put("rows", rows);
        logger.info("日志打印：访问成功！！！！！！！！！！！1");
        return jsonObject;
    }
}
