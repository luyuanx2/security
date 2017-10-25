package com.yy.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * Created by luyuanyuan on 2017/10/25.
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -7973503854003437625L;

    public ValidateCodeException(String msg){
        super(msg);
    }
}
