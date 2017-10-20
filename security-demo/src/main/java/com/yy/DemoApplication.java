package com.yy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by 鲁源源 on 2017/10/17.
 */
@SpringBootApplication
@RestController
@EnableSwagger2//自动生成restful api文档,访问http://localhost:8080/swagger-ui.html即可看到
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }

    //spring-boot 前端参数转换配置 http://www.jianshu.com/p/3a5fc2564501
}
