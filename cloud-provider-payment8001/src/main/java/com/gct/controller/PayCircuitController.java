package com.gct.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PayCircuitController {

    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id) {
        if (id == 0) throw new RuntimeException("----circuit id 不能负数");
        if (id == 2) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello, circuit! inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }

    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id) {
        if (id == 0) throw new RuntimeException("----circuit id 不能负数");
        if (id == 2) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello, circuit! inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }

    @GetMapping(value = "/pay/PoolBulkhead/{id}")
    public String myPoolBulkhead(@PathVariable("id") Integer id) {
        if (id == 0) throw new RuntimeException("----circuit id 不能负数");
        if (id == 2) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("错误了");
                e.printStackTrace();
            }
        }
        return "Hello, circuit! inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }

    //=========Resilience4j ratelimit 的例子
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id) {
        if(id==Integer.valueOf(1)){
            throw new RuntimeException("----circuit id 不能负数");
        }

        return "Hello, myRatelimit欢迎到来 inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }
}
