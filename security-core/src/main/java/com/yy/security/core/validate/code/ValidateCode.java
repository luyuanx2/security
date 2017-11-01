package com.yy.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by luyuanyuan on 2017/10/25.
 */
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = 7305237380288498692L;
    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code,LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }

    //验证码是否过期，当前时间是否大于过期时间,如果大于,说明该验证码就过期了
    public boolean isExpried(){
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }


}
