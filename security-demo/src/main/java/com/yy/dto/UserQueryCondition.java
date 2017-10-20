/**
 * 
 */
package com.yy.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by luyuanyuan on 2017/10/19.
 */
public class UserQueryCondition {

	@ApiModelProperty(value = "用户名")
	private String username;
	@ApiModelProperty(value = "用户年龄起始值")
	private int age;
	@ApiModelProperty(value = "用户年龄终止值")
	private int ageTo;
	@ApiModelProperty(value = "任意参数")
	private String xxx;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(int ageTo) {
		this.ageTo = ageTo;
	}

	public String getXxx() {
		return xxx;
	}

	public void setXxx(String xxx) {
		this.xxx = xxx;
	}
	
}
