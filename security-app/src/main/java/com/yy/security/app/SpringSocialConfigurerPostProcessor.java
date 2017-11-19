package com.yy.security.app;

import com.yy.security.core.social.MySpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by 鲁源源 on 2017/11/20.
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(StringUtils.equals("mySocialSecurityConfig",beanName)){
            MySpringSocialConfigurer config = (MySpringSocialConfigurer) bean;
            config.signupUrl("/social/signUp");
            return config;
        }
        return bean;
    }
}
