package com.yy.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by luyuanyuan on 2017/10/25.
 */
public interface ValidateCodeProcessor {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE";

    /**
     * 创建校验码
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);
}
