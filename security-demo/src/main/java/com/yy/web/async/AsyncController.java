package com.yy.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 异步处理请求
 * Created by luyuanyuan on 2017/10/20.
 */
@RestController
@Slf4j
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;
    @RequestMapping("/order")
    public DeferredResult<String> order() throws Exception {

        log.info("主线程开始");
        String s = RandomStringUtils.randomNumeric(6);

        mockQueue.setPlaceOrder(s);

        DeferredResult<String> deferredResult = new DeferredResult<>();
        deferredResultHolder.getMap().put(s,deferredResult);

        log.info("主线程结束");

        return deferredResult;


//          Callable<String> result = new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				logger.info("副线程开始");
//				Thread.sleep(1000);
//				logger.info("副线程返回");
//				return "success";
//			}
//		};
//        return resullt;


    }

}
