package com.yy.security.core.social.weixin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

/**
 * 微信api实现
 * Created by luyuanyuan on 2017/10/31.
 */
public class WeixinImpl extends AbstractOAuth2ApiBinding implements Weixin {

    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    @Override
    public WeixinUserInfo getUserInfo(String openId) {


        return null;
    }
}
