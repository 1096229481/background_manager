package com.example.backgroundmanager.center.bus.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @author as huangzd
 */
@Slf4j
@RestController
@RequestMapping("nacosClient")
public class NacosClientController {
    @Value("${spring.cloud.nacos.config.namespace}")
    private String nameSpace;

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddr;

    @Value("${spring.cloud.nacos.config.file-extension:yml}")
    private String fileExtension;

    @Value("${spring.cloud.nacos.config.group:DEFAULT_GROUP}")
    private String group;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.profiles.active}")
    private String active;

    private String getDataId() {
        StringBuffer dataId = new StringBuffer(applicationName);
        if (StringUtils.isNotBlank(active)) {
            dataId.append("-" + active);
        }
        dataId.append("." + fileExtension);
        return dataId.toString();
    }

    @GetMapping("/getConfigInfo")
    public String getConfigInfo() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, nameSpace);
        ConfigService configService = null;
        try {
            configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(getDataId(),group,5000L);
            configService.publishConfig(getDataId(),group,NacosServerConfigBean.CONFIG);
            System.out.println(content);
            return content;
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/updateConfigInfo")
    public String updateConfigInfo() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, nameSpace);
        ConfigService configService = null;
        try {
            configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(getDataId(),group,5000L);
            configService.publishConfig(getDataId(),group,NacosServerConfigBean.CONFIG);
            System.out.println(content);
            return content;
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }

}
