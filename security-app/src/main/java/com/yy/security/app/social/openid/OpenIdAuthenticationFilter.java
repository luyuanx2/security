package com.yy.security.app.social.openid;

import com.yy.security.core.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 鲁源源 on 2017/11/19.
 */
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String openIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_OPENID;
    private String providerIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_PROVIDERID;
    private boolean postOnly = true;

    // ~ Constructors
    // ===================================================================================================

    public OpenIdAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID, "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String openId = obtainOpenId(request);
        String providerId = obtainProviderId(request);

        if (openId == null) {
            openId = "";
        }

        if (providerId == null) {
            providerId = "";
        }

        openId = openId.trim();

        OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(
                openId, providerId);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private String obtainProviderId(HttpServletRequest request) {
        return request.getParameter(providerIdParameter);
    }

    private String obtainOpenId(HttpServletRequest request) {
        return request.getParameter(openIdParameter);
    }

    /**
     * Provided so that subclasses may configure what is put into the authentication
     * request's details property.
     *
     * @param request that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details
     * set
     */
    protected void setDetails(HttpServletRequest request,
                              OpenIdAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
