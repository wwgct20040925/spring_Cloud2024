package com.gct.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import jakarta.security.auth.message.callback.SecretKeyCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class feignConfig {

//    @Bean
//    public Request.Options options(){
//        return new Request.Options(5000,5000);
//    }
    @Bean
    public Retryer retryer() {
        return new Retryer.Default(100,1,5);
    }
    @Bean
    Logger.Level level(){
        return Logger.Level.FULL;
    }
}
