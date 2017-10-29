package com.yy.security.browser;

import com.yy.security.core.authentication.AbstractChannelSecurityConfig;
import com.yy.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.yy.security.core.properties.SecurityConstants;
import com.yy.security.core.properties.SecurityProperties;
import com.yy.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


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

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserDetailsService userDetailsService;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private DataSource dataSource;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);
        http.apply(validateCodeSecurityConfig)
                .and()
                //配置自定义手机登录过滤器相关
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
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

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //系统启动时创建 "记住我" 功能表
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

}
