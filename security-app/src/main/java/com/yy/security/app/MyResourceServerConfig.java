package com.yy.security.app;

import com.yy.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.yy.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yy.security.core.properties.SecurityConstants;
import com.yy.security.core.properties.SecurityProperties;
import com.yy.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 资源服务器
 * Created by 鲁源源 on 2017/11/6.
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Autowired
    protected AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer mySocialSecurityConfig;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);
        http.apply(validateCodeSecurityConfig)
                .and()
                //配置自定义手机登录过滤器相关
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(mySocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html",
                        securityProperties.getBrowser().getSignOutUrl(),
                        "/user/regist","/social/signUp"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
