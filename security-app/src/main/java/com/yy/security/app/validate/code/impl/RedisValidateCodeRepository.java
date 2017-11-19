package com.yy.security.app.validate.code.impl;

import com.yy.security.core.validate.code.ValidateCode;
import com.yy.security.core.validate.code.ValidateCodeException;
import com.yy.security.core.validate.code.ValidateCodeRepository;
import com.yy.security.core.validate.code.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * Created by 鲁源源 on 2017/11/19.
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        redisTemplate.opsForValue().set(buildKey(request,validateCodeType),code,30, TimeUnit.MINUTES);
    }

    private Object buildKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)){
            throw new ValidateCodeException("请在请求头中携带deviceId参数");
        }

        return "code:" + validateCodeType.toString().toLowerCase() + ":" + deviceId;
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        Object value = redisTemplate.opsForValue().get(buildKey(request, validateCodeType));
        if (value == null){
            return null;
        }
        return (ValidateCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        redisTemplate.delete(buildKey(request,validateCodeType));
    }
}
