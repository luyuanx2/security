package com.yy.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器
 * Created by luyuanyuan on 2017/10/25.
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
