package com.rminfo.jinmaocs.mapper;

import com.rminfo.jinmaocs.entity.R5rmAppUser;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: SeaRan
 * Date: 2019/7/31
 * Time: 16:45
 * 项目名：jinmaocs
 * 描述：TODO
 * Description: No Description
 */
@Repository
public interface WechatUserMapper {
    //判断用户是否存在
    R5rmAppUser selectAppUser(Map map);

    //判断用户是否绑定
    R5rmAppUser selectBindUser(Map map);
    //根据openid判断是否绑定了用户
    R5rmAppUser selectYesNoBindUser(Map map);
    //绑定用户
    int userBinding(Map map);
}
