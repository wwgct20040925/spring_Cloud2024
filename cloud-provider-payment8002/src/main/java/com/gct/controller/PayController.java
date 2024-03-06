package com.gct.controller;

import com.gct.pojo.Pay;
import com.gct.result.ResultData;
import com.gct.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pay")
@Tag(name = "支付微服务模块",description = "支付CRUD")
@Slf4j
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping ("add")
    @Operation(summary = "新增",description = "新增支付流水方法,json串做参数")
    private ResultData add(@RequestBody Pay pay){
        return payService.add(pay);
    }

    @DeleteMapping("del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData del(@PathVariable("id") int id){
        System.out.println(id);
        return payService.del(id);
    }

    @PutMapping("update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData update(@RequestBody Pay pay){
        return payService.updateByid(pay);
    }

    @GetMapping("get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData get(@PathVariable("id") int id){
        return payService.get(id);
    }

    @Value("${server.port}")
    private String port;
    @GetMapping("show")
    private String show(@Value("${com.gct}") String name){
        return name+port;
    }

}
