package com.gct;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableDiscoveryClient      //服务注册与发现
@EnableFeignClients         //开启feign支持
public class MainFeign8000 {
    public static void main(String[] args) {
        SpringApplication.run(MainFeign8000.class,args);
    }
}