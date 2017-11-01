package com.yy.test;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * Created by 鲁源源 on 2017/11/1.
 */

//@RequestMapping("/qqLogin/")
//@Controller
public class WeixinTestController {

    private static final String TOKEN="weixintoken";

    @RequestMapping("weixin")
    public String weixin(HttpServletRequest request,HttpServletResponse response) throws Exception {

        //获取请求参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //1.构造字符串数组
        String[] arr={TOKEN,nonce,timestamp};
        //2.字符串数组排序
        Arrays.sort(arr);
        //3.将排序后的三个字符串拼接成一个字符串
        StringBuffer sb=new StringBuffer();
        for (String str :arr) {
            sb.append(str);
        }

        String sha1=SecurityKitUtils.sha1(sb.toString());

        //System.out.println(sha1.equals(signature));
        if(sha1.equals(signature))
        {
            try {
                OutputStream os = response.getOutputStream();
                BufferedWriter resBr = new BufferedWriter(new OutputStreamWriter(os));
                resBr.write(echostr);
                resBr.flush();
                resBr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
