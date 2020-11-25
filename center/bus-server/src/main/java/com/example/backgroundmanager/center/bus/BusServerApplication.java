package com.example.backgroundmanager.center.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author as huangzd
 */
@EnableDiscoveryClient
@SpringBootApplication
public class BusServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusServerApplication.class, args);
    }
}

