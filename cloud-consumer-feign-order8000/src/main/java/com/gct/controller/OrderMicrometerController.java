package com.gct.controller;

import com.gct.api.PayFeignApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer/pay")
public class OrderMicrometerController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("micrometer/{id}")
    public String micrometer(@PathVariable("id") int id){
        return payFeignApi.micrometer(id);
    }
}
