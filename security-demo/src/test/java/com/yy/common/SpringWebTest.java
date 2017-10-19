package com.yy.common;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Random;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * spring测试基类
 * Created by luyuanyuan on 2017/10/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringWebTest {

    protected final Random random = new Random();
    /**
     * 自动注入应用程序上下文
     **/
    @Autowired(required = false)
    protected WebApplicationContext context;
    /**
     * 自动注入servlet上下文
     **/
    @Autowired(required = false)
    protected ServletContext servletContext;
    /**
     * 选配 只有在SecurityConfig起作用的情况下
     **/
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired(required = false)
    protected FilterChainProxy springSecurityFilter;
    @Autowired(required = false)
    protected MockMvcConfigurer mockMvcConfigurer;
    /**
     * mock请求
     **/
    @Autowired(required = false)
    protected MockHttpServletRequest request;
    /**
     * mockMvc等待初始化
     **/
    protected MockMvc mockMvc;
//    protected WebClient webClient;
//    protected WebDriver driver;


    /**
     * 准备测试环境所需的各个字段
     */
    @Before
    public void prepareFields() {

        createMockMVC();

        if (mockMvc == null)
            return;

        // 现在创建其他
//        createWebClient();
//
//        createWebDriver();
    }

    /**
     * 创建{@link #mockMvc}
     * <span>2.2以后需要添加更多的filter或者其他什么的可以覆盖{@link #buildMockMVC(DefaultMockMvcBuilder)}不用再重新实现一次这个
     * 方法了。</span>
     */
    public void createMockMVC() {
        MockitoAnnotations.initMocks(this);
        // ignore it, so it works in no-web fine.
        if (context == null)
            return;
        DefaultMockMvcBuilder builder = webAppContextSetup(context);
        builder = buildMockMVC(builder);
        if (springSecurityFilter != null) {
            builder = builder.addFilters(springSecurityFilter);
        }

        if (mockMvcConfigurer != null) {
            builder = builder.apply(mockMvcConfigurer);
        }
        mockMvc = builder.build();
    }

    /**
     * 构建{@link #mockMvc}的辅助方法
     *
     * @param builder builder
     * @return new Builder
     * @since 2.2
     */
    protected DefaultMockMvcBuilder buildMockMVC(DefaultMockMvcBuilder builder) {
        return builder;
    }

    /**
     * @return 获取随机http url
     */
    protected String randomHttpURL() {
        StringBuilder stringBuilder = new StringBuilder("http://");
        stringBuilder.append(RandomStringUtils.randomAlphabetic(2 + random.nextInt(4)));
        stringBuilder.append(".");
        stringBuilder.append(RandomStringUtils.randomAlphabetic(2 + random.nextInt(4)));
        if (random.nextBoolean()) {
            stringBuilder.append(".");
            stringBuilder.append(RandomStringUtils.randomAlphabetic(2 + random.nextInt(4)));
        }
        return stringBuilder.toString();
    }

    /**
     * @return 获取随机email地址
     */
    protected String randomEmailAddress() {
        return RandomStringUtils.randomAlphabetic(random.nextInt(5) + 3)
                + "@"
                + RandomStringUtils.randomAlphabetic(random.nextInt(5) + 3)
                + "."
                + RandomStringUtils.randomAlphabetic(random.nextInt(2) + 2);
    }

    /**
     * <p>位数不足无法保证其唯一性,需要客户端代码自行校验唯一性.</p>
     * <p>具体的区间是10000000000-19999999999</p>
     *
     * @return 获取一个随机的手机号码
     */
    protected String randomMobile() {
        String p1 = String.valueOf(100000 + random.nextInt(100000));
        //还有5位 而且必须保证5位
        String p2 = String.format("%05d", random.nextInt(100000));
        return p1 + p2;
    }


}
