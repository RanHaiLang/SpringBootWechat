package com.rminfo.jinmaocs.entity;

import com.rminfo.jinmaocs.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: SeaRan
 * Date: 2019/7/29
 * Time: 15:04
 * 项目名：jinmaocs
 * 描述：TODO
 * Description: No Description
 */

@Component
@PropertySource("classpath:WechatConstants.properties")
public class Constant {
    @Value("${APPID}")
    public String appid;
    @Value("${SECRET}")
    public String secret;

    @Value("${PAPPID}")
    public String pappid;
    @Value("${PSECRET}")
    public String psecret;
    @Value("${pmodelId}")
    public String pmodelId;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPappid() {
        return pappid;
    }

    public void setPappid(String pappid) {
        this.pappid = pappid;
    }

    public String getPsecret() {
        return psecret;
    }

    public void setPsecret(String psecret) {
        this.psecret = psecret;
    }

    public String getPmodelId() {
        return pmodelId;
    }

    public void setPmodelId(String pmodelId) {
        this.pmodelId = pmodelId;
    }
}
