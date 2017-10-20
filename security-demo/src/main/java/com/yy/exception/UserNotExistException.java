package com.yy.exception;

/**
 * 用户不存在
 * Created by luyuanyuan on 2017/10/20.
 */
public class UserNotExistException extends RuntimeException {

    private String id;

    public UserNotExistException(String id) {
        super("用户不存在");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
