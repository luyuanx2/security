package com.yy.security.browser;

import com.yy.security.core.properties.LoginResponseType;
import com.yy.security.core.properties.SecurityConstants;
import com.yy.security.core.properties.SecurityProperties;
import com.yy.security.core.support.SimpleResponse;
import com.yy.security.core.support.SocialUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luyuanyuan on 2017/10/23.
 */
@RestController
public class BrowserSecurityController {

    @Autowired
    private SecurityProperties securityProperties;

    private Logger log = LoggerFactory.getLogger(getClass());
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    //   /authentication/require
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest saveRequest = requestCache.getRequest(request, response);

        if (saveRequest != null){
            String targetUrl = saveRequest.getRedirectUrl();
            log.info("引发跳转的请求是:" + targetUrl);
            //if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
            if(LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("357fe0991ae50e632bfa3");
    }

    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return userInfo;
    }

    /**
     * session失效处理
     * @return
     */
    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse sessionInvalid(){
        String message = "session失效";
        return new SimpleResponse(message);
    }
}
