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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


/**
 * Created by luyuanyuan on 2017/10/23.
 */
@Configuration
public class  BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer mySocialSecurityConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);
        http.apply(validateCodeSecurityConfig)
                .and()
                //配置自定义手机登录过滤器相关
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(mySocialSecurityConfig)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
//                    .invalidSessionUrl("/session/invalid")
                    .invalidSessionStrategy(invalidSessionStrategy)
                    //最大session数
                    .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                    //达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
                    .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                    //并发登录处理策略
                    .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
                .logout()
                    .logoutUrl("/signOut")
                    //.logoutSuccessUrl("/signOut.html")
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .deleteCookies("JSESSIONID")
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
                        "/user/regist"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
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
