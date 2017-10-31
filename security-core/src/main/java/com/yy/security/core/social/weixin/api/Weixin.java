package com.yy.security.core.social.weixin.api;

/**
 * 微信api
 * Created by luyuanyuan on 2017/10/31.
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}
