package com.gct.controller;

import com.gct.pojo.PayDTO;

import com.gct.result.ResultData;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer/pay")
public class OrderController {
    public static final String PaymentSrv_URL = "http://cloud-payment-service";


    @Resource
    private RestTemplate restTemplate;

    @PostMapping("add")
    public ResultData add(@RequestBody PayDTO payDTO) {
        ResponseEntity<ResultData> entity = restTemplate.postForEntity(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
        return entity.getBody();

    }

    @GetMapping("get/{id}")
    public ResultData get(@PathVariable("id") int id) {
        ResponseEntity<ResultData> forEntity = restTemplate.getForEntity(PaymentSrv_URL + "/pay/get/" + id, ResultData.class);
        System.out.println("gct");
        return forEntity.getBody();
    }
}
