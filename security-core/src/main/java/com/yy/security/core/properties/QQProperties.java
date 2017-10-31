package com.yy.security.core.properties;

/**
 * qq登录相关配置
 * Created by luyuanyuan on 2017/10/30.
 */
public class QQProperties extends org.springframework.boot.autoconfigure.social.SocialProperties{

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
