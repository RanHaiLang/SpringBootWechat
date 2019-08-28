package com.rminfo.jinmaocs.service.impl;

import com.rminfo.jinmaocs.entity.R5rmAppUser;
import com.rminfo.jinmaocs.mapper.WechatUserMapper;
import com.rminfo.jinmaocs.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: SeaRan
 * Date: 2019/7/31
 * Time: 17:22
 * 项目名：jinmaocs
 * 描述：TODO
 * Description: No Description
 */
@Service
public class WechatUserServiceImpl implements WechatUserService {

    @Autowired
    private WechatUserMapper wechatUserMapper;


    @Override
    public R5rmAppUser selectAppUser(String username,String password) {
        Map map = new HashMap();
        map.put("username",username);
        map.put("password",password);
        return wechatUserMapper.selectAppUser(map);
    }

    @Override
    public R5rmAppUser selectBindUser(String username) {
        Map map = new HashMap();
        map.put("username",username);
        return wechatUserMapper.selectBindUser(map);
    }

    @Override
    public R5rmAppUser selectYesNoBindUser(String openid) {
        Map map = new HashMap();
        map.put("openid",openid);
        return wechatUserMapper.selectYesNoBindUser(map);
    }

    @Override
    public int userBinding(String username, String openid) {
        Map map = new HashMap();
        map.put("username",username);
        map.put("openid",openid);
        return wechatUserMapper.userBinding(map);
    }
}
