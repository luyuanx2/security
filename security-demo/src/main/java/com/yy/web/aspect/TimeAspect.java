package com.yy.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by luyuanyuan on 2017/10/20.
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.yy.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{

        System.out.println("时间切面开始");

        Arrays.stream(pjp.getArgs()).forEach(x ->{
            System.out.println("切面获得的参数是:" + x);
        });

        long start = new Date().getTime();

        Object proceed = pjp.proceed();

        System.out.println("切面耗时:" + (new Date().getTime() - start));
        System.out.println("切面完成");
        return proceed;
    }
}
