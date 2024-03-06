package com.gct.controller;

import com.gct.pojo.PayDTO;

import com.gct.result.ResultData;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        return forEntity.getBody();
    }
    @GetMapping("show")
    public String get(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(PaymentSrv_URL + "/pay/show", String.class);
        return forEntity.getBody();
    }
    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }
}
