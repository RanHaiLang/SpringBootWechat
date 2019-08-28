package com.rminfo.jinmaocs.aspect;

import com.rminfo.jinmaocs.entity.R5rmAppUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 对controller里面所有请求的日志打印
 */
@Aspect
@Component
public class HttpAspect {

    private  final static Logger logger=LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.rminfo.jinmaocs.controller.*.*(..))")
    public void webLog(){
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("请求URL : " + request.getRequestURL().toString());
        logger.info("请求方法类型HTTP_METHOD : " + request.getMethod());
        logger.info("请求IP : " + request.getRemoteAddr());
        logger.info("请求方法所在路径CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("请求参数ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("返回内容RESPONSE : " + ret);
        //清除请求后的用户信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        R5rmAppUser usercode = (R5rmAppUser)request.getSession().getAttribute("usercode");//取值
        request.getSession().removeAttribute("usercode");
    }
}

