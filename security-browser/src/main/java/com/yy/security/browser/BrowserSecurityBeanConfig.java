package com.yy.security.browser;

import com.yy.security.browser.logout.MyLogoutSuccessHandler;
import com.yy.security.browser.session.MyExpiredSessionStrategy;
import com.yy.security.browser.session.MyInvalidSessionStrategy;
import com.yy.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * Created by luyuanyuan on 2017/11/1.
 */
@Configuration
public class BrowserSecurityBeanConfig {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new MyInvalidSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new MyExpiredSessionStrategy(securityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new MyLogoutSuccessHandler(securityProperties.getBrowser().getSignUpUrl());
    }
}
