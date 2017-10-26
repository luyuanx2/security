package com.yy.security.browser;

import com.yy.security.core.authentication.AbstractChannelSecurityConfig;
import com.yy.security.core.properties.SecurityConstants;
import com.yy.security.core.properties.SecurityProperties;
import com.yy.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by luyuanyuan on 2017/10/23.
 */
@Configuration
public class  BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SecurityProperties securityProperties;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);
        http.apply(validateCodeSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*"
//                        securityProperties.getBrowser().getSignUpUrl(),
//                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".json",
//                        securityProperties.getBrowser().getSession().getSessionInvalidUrl()+".html",
//                        "/user/regist"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
