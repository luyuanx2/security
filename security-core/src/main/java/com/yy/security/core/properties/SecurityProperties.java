package com.yy.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by luyuanyuan on 2017/10/23.
 */
@ConfigurationProperties(prefix = "yy.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
