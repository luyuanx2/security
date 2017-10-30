/**
 * 
 */
package com.yy.security;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 第三方登录时默认注册用户
 * Created by luyuanyuan on 2017/10/30.
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ConnectionSignUp#execute(org.springframework.social.connect.Connection)
	 */
	@Override
	public String execute(Connection<?> connection) {
		//根据社交用户信息默认创建用户并返回用户唯一标识
		//return connection.getDisplayName();
		return RandomStringUtils.random(6);
	}


}
