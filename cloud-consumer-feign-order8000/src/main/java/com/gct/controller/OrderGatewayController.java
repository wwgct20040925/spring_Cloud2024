package com.gct.controller;

import com.gct.api.PayFeignApi;
import com.gct.result.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consumer/pay/gateway")
public class OrderGatewayController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("get/{id}")
    public ResultData getById(@PathVariable("id") int id){
        return payFeignApi.gatewayById(id);
    }
}
