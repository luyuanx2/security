package com.yy.security.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yy.security.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 鲁源源 on 2017/11/1.
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String signOutUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    public MyLogoutSuccessHandler(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("退出成功");

        if(StringUtils.isNotBlank(signOutUrl)){
            //如果没有配置退出成功页面，返回json数据
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
        }else{
            response.sendRedirect(signOutUrl);
        }
    }
}
