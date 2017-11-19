package com.yy.security.core.support;

/**
 * Created by luyuanyuan on 2017/10/23.
 */
public class SimpleResponse {

    public SimpleResponse(Object content){
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
