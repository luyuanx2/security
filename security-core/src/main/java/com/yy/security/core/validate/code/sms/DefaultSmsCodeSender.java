package com.yy.security.core.validate.code.sms;

/**
 * Created by luyuanyuan on 2017/10/25.
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送短信验证码" + code);
    }
}
