package com.yy.security.app.social;

import com.yy.security.app.AppSecurityException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

/**
 * Created by 鲁源源 on 2017/11/20.
 */
@Component
public class AppSignUpUtils {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;


    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    public void saveConnectionData(WebRequest request,ConnectionData connectionData){
        redisTemplate.opsForValue().set(getKey(request),connectionData,10, TimeUnit.MINUTES);
    }

    public void doPostSignUp(WebRequest request, String userId){
        String key = getKey(request);
        if(!redisTemplate.hasKey(key)){
            throw new AppSecurityException("无法找到缓存的用户社交账号信息");
        }
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
                .createConnection(connectionData);
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

        redisTemplate.delete(key);
    }

    private String getKey(WebRequest request) {

        String deviceId = request.getHeader("deviceId");
        if(StringUtils.isBlank(deviceId)){
            throw  new AppSecurityException("设备id参数不能为空");
        }

        return "yy:security:social.connect." + deviceId;
    }
}
