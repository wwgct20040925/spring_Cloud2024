package com.gct.api;

import com.gct.pojo.PayDTO;
import com.gct.result.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("cloud-payment-service")
public interface PayFeignApi {

    @PostMapping("pay/add")
    ResultData addPay(@RequestBody PayDTO payDTO);

    @GetMapping("pay/get/{id}")
    ResultData getById(@PathVariable("id") int id);

    @GetMapping("/pay/show")
    String getInfo();
}
