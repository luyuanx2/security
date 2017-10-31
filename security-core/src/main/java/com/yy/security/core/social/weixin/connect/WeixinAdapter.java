package com.yy.security.core.social.weixin.connect;

import com.yy.security.core.social.weixin.api.Weixin;
import com.yy.security.core.social.weixin.api.WeixinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 * Created by luyuanyuan on 2017/10/31.
 */
public class WeixinAdapter implements ApiAdapter<Weixin> {


    private String openId;

    public WeixinAdapter(){
    }

    public WeixinAdapter(String openId){
        this.openId = openId;
    }
    @Override
    public boolean test(Weixin weixin) {
        return true;
    }

    @Override
    public void setConnectionValues(Weixin api, ConnectionValues connectionValues) {
        WeixinUserInfo userInfo = api.getUserInfo(openId);
        connectionValues.setProviderUserId(userInfo.getOpenid());
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(Weixin weixin) {
        return null;
    }

    @Override
    public void updateStatus(Weixin weixin, String s) {
        //do nothing
    }
}
