package com.yy.security.core.validate.code.sms;

import com.yy.security.core.properties.SecurityProperties;
import com.yy.security.core.validate.code.ValidateCode;
import com.yy.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by luyuanyuan on 2017/10/25.
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());

        return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());
    }
}
