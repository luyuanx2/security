package com.yy.security.core.properties;

/**
 * Created by luyuanyuan on 2017/10/23.
 */
public class BrowserProperties {

    private SessionProperties session = new SessionProperties();

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginResponseType loginType = LoginResponseType.JSON;

    private int rememberMeSeconds = 3600;

    private String signUpUrl = "/imooc-signUp.html";

    private String signOutUrl;

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }
}
