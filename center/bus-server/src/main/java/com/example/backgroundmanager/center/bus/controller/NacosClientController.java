package com.example.backgroundmanager.center.bus.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    private ConfigService getConfigService() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, nameSpace);
        ConfigService configService = null;
        try {
            configService = NacosFactory.createConfigService(properties);
        } catch (NacosException e) {
            e.printStackTrace();
            log.error("获取nacos配置异常");
        }
        return configService;
    }

    @GetMapping("/getConfigInfo")
    public String getConfigInfo() {

        ConfigService configService = getConfigService();
        Preconditions.checkNotNull(configService,"获取nacos配置异常");
        String content = null;
        try {
            content = configService.getConfig(getDataId(), group, 5000L);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        System.out.println(content);
        return content;

    }

    /**
     * 不好实现，之后再考虑
     * @param config
     * @return
     */
    @GetMapping("/updateStartWay")
    public String updateStartWay(@RequestParam String config) {
        ConfigService configService = getConfigService();
        Preconditions.checkNotNull(configService,"获取nacos配置异常");
        try {
            String content = configService.getConfig(getDataId(), group, 5000L);
            content = content.replace("ai",config);
            boolean b =configService.publishConfig(getDataId(), group, content);
            System.out.println(content);
            return content;
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }

}
