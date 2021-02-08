package com.lzj.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Author: lzj
 * @Date： 2021/2/7 11:33
 */


@EnableConfigurationProperties
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.lzj")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
