package com.yy.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * Created by 鲁源源 on 2017/11/19.
 */
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
