package com.yy.security.browser.validate.code.impl;

import com.yy.security.core.validate.code.ValidateCode;
import com.yy.security.core.validate.code.ValidateCodeRepository;
import com.yy.security.core.validate.code.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by 鲁源源 on 2017/11/19.
 */
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {
    /**
     * 验证码放入session的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(request,getSessionKey(request,validateCodeType),code);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request,getSessionKey(request,validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        sessionStrategy.removeAttribute(request,getSessionKey(request,validateCodeType));
    }

    /**
     * 构建验证码放入session时的key
     *
     * @param request
     * @return
     */
    private String getSessionKey(ServletWebRequest request,ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }

}
