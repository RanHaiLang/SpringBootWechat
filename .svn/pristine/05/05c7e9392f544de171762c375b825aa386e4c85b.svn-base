package com.rminfo.jinmaocs.token.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.rminfo.jinmaocs.entity.R5rmAppUser;
import com.rminfo.jinmaocs.service.R5rmAppUserService;
import com.rminfo.jinmaocs.token.annotation.AuthToken;
import com.rminfo.jinmaocs.token.utils.ConstantKit;
import com.rminfo.jinmaocs.util.SpringBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Think
 * @Title: AuthorizationInterceptor
 * @ProjectName token-authentication
 * @Description: TODO
 * @date 2019/1/1815:50
 */
public class AuthorizationInterceptor implements HandlerInterceptor {
    protected static final Logger log = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    //实例化service
    private static R5rmAppUserService r5rmAppUserService = (R5rmAppUserService) SpringBeanUtils.getBean("rminterFaceTableRepository");

    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "Authorization";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized 无效token！";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    /**
     * 存放登录用户模型Key的Request Key
     */
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 如果打上了AuthToken注解则需要验证token
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {

            //从请求头中获取token
            String token = request.getHeader(httpHeaderName);
            //从携带参数中获取token
//            String token = request.getParameter(httpHeaderName);
            log.info("从访问路径中所获取到的token："+ token);
            String usercode = "";
            Jedis jedis = new Jedis(ConstantKit.REDIS_SRC, ConstantKit.REDIS_PORT);
            jedis.auth(ConstantKit.REDIS_PASSWORD);
            if (token != null && token.length() != 0) {
                usercode = jedis.get(token);
                log.info("从访问路径中所获取到的usercode："+ usercode);
            }else {
                unauthorizedErrorMessage = "请求未发现token";
            }
            if (usercode != null && !usercode.trim().equals("")) {
                //log.info("token birth time is: {}",jedis.get(usercode+token));
                //获取token创建时间
                Long tokeBirthTime = Long.valueOf(jedis.get(token + usercode));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String tokeBirthTimeString = sdf.format(new Date(Long.parseLong(String.valueOf(tokeBirthTime))));
                log.info("token创建时间: {}", tokeBirthTimeString);
                Long diff = System.currentTimeMillis() - tokeBirthTime;
                log.info("token失效时长token is exist : {} ms", diff);
                //重新设置Redis中的token过期时间
                if (diff > ConstantKit.TOKEN_RESET_TIME) {
                    jedis.expire(usercode, ConstantKit.TOKEN_EXPIRE_TIME);
                    jedis.expire(token, ConstantKit.TOKEN_EXPIRE_TIME);
                    log.info("重新设置有效时长Reset expire time success!");
                    Long newBirthTime = System.currentTimeMillis();
                    jedis.set(token + usercode, newBirthTime.toString());
                }
                //获取请求用户信息，存入session
                Map<String,String> map = new HashMap<>();
                map.put("usercode",usercode);
                R5rmAppUser r5rmAppUser = r5rmAppUserService.getUser(map);
                r5rmAppUser.setToken(token);
                request.getSession().setAttribute("usercode", r5rmAppUser);//存值

                //用完关闭
                jedis.close();
                request.setAttribute(REQUEST_CURRENT_KEY, usercode);
                return true;
            } else {
                JSONObject jsonObject = new JSONObject();

                PrintWriter out = null;
                try {
                    response.setStatus(unauthorizedErrorCode);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    jsonObject.put("code", ((HttpServletResponse) response).getStatus());
//                    jsonObject.put("message", HttpStatus.UNAUTHORIZED);
                    jsonObject.put("message", unauthorizedErrorMessage);
                    out = response.getWriter();
                    out.println(jsonObject);

                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                }

            }

        }

        request.setAttribute(REQUEST_CURRENT_KEY, null);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
