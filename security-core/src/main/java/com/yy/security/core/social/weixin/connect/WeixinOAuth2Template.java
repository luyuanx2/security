package com.yy.security.core.social.weixin.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * 完成微信的OAuth2认证流程的模板类。国内厂商实现的OAuth2每个都不同, spring默认提供的OAuth2Template适应不了，只能针对每个厂商自己微调。
 * Created by luyuanyuan on 2017/10/31.
 */
public class WeixinOAuth2Template extends OAuth2Template {

    private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    private Logger logger = LoggerFactory.getLogger(getClass());

    public WeixinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String authenticateUrl, String accessTokenUrl) {

        super(clientId, clientSecret, authorizeUrl, authenticateUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }
}
