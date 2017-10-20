package com.yy.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by luyuanyuan on 2017/10/20.
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器---preHandle");

        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());

        request.setAttribute("startTime", new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器---postHandle");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("拦截器---time interceptor 耗时:"+ (new Date().getTime() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        System.out.println("拦截器---afterCompletion");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("拦截器---time interceptor 耗时：" + (new Date().getTime() - start));

        System.out.println("拦截器捕获异常是" + e);
    }
}
