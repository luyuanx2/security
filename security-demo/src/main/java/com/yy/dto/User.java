/**
 * 
 */
package com.yy.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.yy.validator.MyConstraint;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by luyuanyuan on 2017/10/19.
 */
public class User {
	
	public interface UserSimpleView {};
	public interface UserDetailView extends UserSimpleView {};

	@ApiModelProperty(value = "用户id")
	private String id;

	@MyConstraint(message = "这是一个测试")
	@ApiModelProperty(value = "用户名")
	private String username;
	
	@NotBlank(message = "密码不能为空")
	@ApiModelProperty(value = "密码")
	private String password;
	
	@Past(message = "生日必须是过去的时间")
	@ApiModelProperty(value = "生日")
	private Date birthday;

	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	
}
