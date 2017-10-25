package com.yy.security.core.validate.code.sms;

/**
 * Created by luyuanyuan on 2017/10/25.
 */
public interface SmsCodeSender {

    void send(String mobile, String code);
}
