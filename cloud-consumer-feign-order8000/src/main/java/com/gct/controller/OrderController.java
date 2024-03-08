package com.gct.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.gct.api.PayFeignApi;
import com.gct.pojo.PayDTO;
import com.gct.result.ResultData;
import com.gct.result.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer/pay")
public class OrderController {
   @Resource
   private PayFeignApi payFeignApi;


    @PostMapping("add")
    public ResultData add(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);

    }
    @GetMapping("get/{id}")
    public ResultData getById(@PathVariable("id") int id){
        ResultData resultData;
        try {
            System.out.println("开始时间是:"+DateUtil.now());
            resultData = payFeignApi.getById(id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("结束时间是:"+DateUtil.now());
            resultData = ResultData.fail(ReturnCodeEnum.RC999,e.getMessage());
        }
        return resultData;
    }


    @GetMapping("show")
    public String get(){
        return payFeignApi.getInfo();
    }

}
