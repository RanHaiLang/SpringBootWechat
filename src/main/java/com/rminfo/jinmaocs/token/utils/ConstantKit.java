package com.rminfo.jinmaocs.token.utils;


import com.rminfo.jinmaocs.util.ConfigConstants;
import com.rminfo.jinmaocs.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Value;

public final class ConstantKit {

    /**
     * 设置删除标志为真
     */
    public static final Integer DEL_FLAG_TRUE = 1;

    /**
     * 设置删除标志为假
     */
    public static final Integer DEL_FLAG_FALSE = 0;

    /**
     * redis存储token设置的过期时间，10分钟
     */
    public static final Integer TOKEN_EXPIRE_TIME = 60 * 60;

    /**
     * 设置可以重置token过期时间的时间界限
     */
    public static final Integer TOKEN_RESET_TIME = 1000 * 100;

    /**
     * redis连接地址
     */
    public static final String REDIS_SRC = ConfigUtil.getValue(ConfigConstants.REDISSRC);

    /**
     * redis连接端口
     */
    public static final Integer REDIS_PORT = Integer.valueOf(ConfigUtil.getValue(ConfigConstants.REDISPORT));

    /**
     * redis连接密码
     */
    public static final String REDIS_PASSWORD = ConfigUtil.getValue(ConfigConstants.REDISPASSWORD);

}
