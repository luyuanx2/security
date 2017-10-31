package com.yy.security.core.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看用户绑定的社交账号
 * Created by 鲁源源 on 2017/10/31.
 */
@Component("connect/status")
public class MyConnectStatusView extends AbstractView {

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        Map<String, List<Connection<?>>> connectionMap = (Map<String, List<Connection<?>>>) map.get("connectionMap");

        Map<String, Boolean> result = new HashMap<>();

        for(String key : connectionMap.keySet()){
            result.put(key, CollectionUtils.isNotEmpty(connectionMap.get(key)));
        }

        httpServletResponse.setContentType("application/json;charset=UTF-8");

        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));

    }
}
