package com.gct.controller;

import com.gct.api.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/consumer/pay")
public class OrderCircuitController {

    @Resource
    private PayFeignApi payFeignApi;

    /**
     * 测试断路器
     * @param id
     * @return
     */
    @GetMapping("/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id)
    {
        return payFeignApi.myCircuit(id);
    }
    //myCircuitFallback就是服务降级后的兜底处理方法
    public String myCircuitFallback(Integer id,Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return "myCircuitFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
    }


    /**
     * 测试信号量舱壁
     * @param id
     * @return
     */
    @GetMapping("/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadFallback",type = Bulkhead.Type.SEMAPHORE)
    public String myBulkhead(@PathVariable("id") Integer id){
        return payFeignApi.myBulkhead(id);
    }
    public String myBulkheadFallback(Integer id,Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return "myBulkheadFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
    }


    /**
     * 测试线程池信号舱壁
     * @param id
     * @return
     */
    @GetMapping("/PoolBulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myPoolBulkheadFallback",type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myPoolBulkhead(@PathVariable("id") Integer id){

        System.out.println(Thread.currentThread().getName().toString()+"服务开始了");
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Thread.currentThread().getName().toString()+"服务结束了");
        return CompletableFuture.supplyAsync(()->payFeignApi.myPoolBulkhead(id));
    }
    public CompletableFuture<String> myPoolBulkheadFallback(Integer id,Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return CompletableFuture.supplyAsync(()-> "myPoolBulkheadFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
    }

    /**
     * 测试线程池隔离
     * @param id
     * @return
     */
    @GetMapping("/rateLimit/{id}")
    @RateLimiter(name = "cloud-payment-service",fallbackMethod = "myRateLimitFallback")
    public String myRateLimit(@PathVariable("id") Integer id){
        return payFeignApi.myRatelimit(id);
    }
    public String myRateLimitFallback(Integer id,Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return "myRateLimitFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
    }


}
