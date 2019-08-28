package com.rminfo.jinmaocs.controller;

import com.alibaba.fastjson.JSONObject;
import com.rminfo.jinmaocs.entity.R5rmAppUser;
import com.rminfo.jinmaocs.mapper.R5rmAppUserMapper;
import com.rminfo.jinmaocs.token.annotation.AuthToken;
import com.rminfo.jinmaocs.token.utils.ConstantKit;
import com.rminfo.jinmaocs.token.utils.Md5TokenGenerator;
import com.rminfo.jinmaocs.util.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 登录验证
 */
@RestController
public class LoginController {

    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    Md5TokenGenerator tokenGenerator;

    @Autowired
    R5rmAppUserMapper userMapper;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome token authentication:";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@RequestParam String usercode, @RequestParam String password) {
        JSONObject result;
        try {
            //判断用户账号密码是否正确
            Map<String, String> map = new HashMap<>();
            map.put("usercode", usercode);
            map.put("password", password);
            R5rmAppUser user = userMapper.getUser(map);
            logger.info("用户信息user:" + user);
            if (user != null) {
                //连接redis数据库
                Jedis jedis = new Jedis(ConstantKit.REDIS_SRC, ConstantKit.REDIS_PORT);
                //设置连接密码
                jedis.auth(ConstantKit.REDIS_PASSWORD);
                String token = tokenGenerator.generate(usercode, password);
                jedis.set(usercode, token);
                //设置key生存时间，当key过期时，它会被自动删除，时间是秒
                jedis.expire(usercode, ConstantKit.TOKEN_EXPIRE_TIME);
                jedis.set(token, usercode);
                jedis.expire(token, ConstantKit.TOKEN_EXPIRE_TIME);
                Long currentTime = System.currentTimeMillis();
                jedis.set(token + usercode, currentTime.toString());
                jedis.expire(token + usercode, ConstantKit.TOKEN_EXPIRE_TIME);

                //用完关闭
                jedis.close();

                result = DataUtil.getSuccess();
                //给用户返回有效token
                result.put("token", token);
            } else {
                return DataUtil.getError("用户名或密码错误！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return DataUtil.getError(e.getMessage());
        }
        return result;

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @AuthToken
    public JSONObject logout(HttpServletRequest request) {
        JSONObject result = DataUtil.getSuccess();
        try {
            R5rmAppUser user = (R5rmAppUser) request.getSession().getAttribute("usercode");//取值
            if (user != null) {
                //连接redis数据库
                Jedis jedis = new Jedis(ConstantKit.REDIS_SRC, ConstantKit.REDIS_PORT);
                //设置连接密码
                jedis.auth(ConstantKit.REDIS_PASSWORD);
                //删除
                jedis.del(user.getUsrcode());
                jedis.del(user.getToken());
                jedis.del(user.getToken() + user.getUsrcode());

                //用完关闭
                jedis.close();
            } else {
                return DataUtil.getError("未获取到有效用户！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return DataUtil.getError(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @AuthToken
    public JSONObject test(HttpServletRequest request) {
        logger.info("已进入测试路径");
        R5rmAppUser usercode = (R5rmAppUser) request.getSession().getAttribute("usercode");//取值
        return DataUtil.getData("访问成功！！！！！！！");
    }

}
