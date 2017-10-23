package com.yy.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by luyuanyuan on 2017/10/23.
 */
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
