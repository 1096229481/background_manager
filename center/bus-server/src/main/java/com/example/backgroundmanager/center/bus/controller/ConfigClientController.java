package com.example.backgroundmanager.center.bus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author as huangzd
 */
@Slf4j
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        log.info("info--------------------"+configInfo);
        log.error("error--------------------"+configInfo);
        log.warn("warn--------------------"+configInfo);
        log.debug("DEBUG--------------------"+configInfo);
        return configInfo;
    }

}
