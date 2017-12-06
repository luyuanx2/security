package com.yy.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yy.dto.User;
import com.yy.dto.UserQueryCondition;
import com.yy.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyuanyuan on 2017/10/19.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    @Autowired
    private SecurityProperties securityProperties;

    //@Autowired
    //private AppSignUpUtils appSignUpUtils;


    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {

        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        String userId = RandomStringUtils.random(6);
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
        //appSignUpUtils.doPostSignUp(new ServletWebRequest(request),userId);
    }


    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable) {

        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    @ApiOperation(value = "根据用户id查询用户详细信息")
    public User getInfo(@ApiParam(value = "用户id") @PathVariable String id){

//        throw new UserNotExistException(id);
        User user = new User();
        user.setUsername("tom");
        return user;
    }



    @GetMapping("/me")
    //public Object getCurrentUser(@AuthenticationPrincipal UserDetails user){
    public Object getCurrentUser(Authentication user,HttpServletRequest request) throws UnsupportedEncodingException {
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");
        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2()
                .getJwtSigningKey().getBytes("UTF-8"))
        .parseClaimsJws(token).getBody();

        String company = (String) claims.get("company");
        System.out.println("--------"+company);
        return user;
    }

    @PostMapping
    @ApiOperation(value = "创建用户")
    public User create(@Valid @RequestBody User user,BindingResult errors) {

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation(value = "修改用户信息")
    public User update(@Valid @RequestBody User user, BindingResult errors) {

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation(value = "删除用户")
    public void delete(@ApiParam(value = "用户id") @PathVariable String id) {
        System.out.println(id);
    }
}
