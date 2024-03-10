package com.gct.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayFeignConfig {

//    @Bean
//    public Request.Options options(){
//        return new Request.Options(5000,5000);
//    }
//    @Bean
//    public Retryer retryer() {
//        return new Retryer.Default(100,1,4);
//    }
    @Bean
    Logger.Level level(){
        return Logger.Level.FULL;
    }
}
