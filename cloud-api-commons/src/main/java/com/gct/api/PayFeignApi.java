package com.gct.api;

import com.gct.config.PayFeignConfig;
import com.gct.pojo.PayDTO;
import com.gct.result.ResultData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "gateway9527",configuration = {PayFeignConfig.class})
public interface PayFeignApi {

    @PostMapping("pay/add")
    ResultData addPay(@RequestBody PayDTO payDTO);

    @GetMapping("pay/get/{id}")
    ResultData getById(@PathVariable("id") int id);

    @GetMapping("/pay/show")
    String getInfo();

    /**
     * 测试断路器是否成功运行
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    /**
     * 测试信号量舱壁是否成功运行
     * @param id
     * @return
     */
    @GetMapping("/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);

    /**
     * 测试线程池信号舱壁是否成功运行
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/PoolBulkhead/{id}")
    public String myPoolBulkhead(@PathVariable("id") Integer id);

    /**
     * 测试限流
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);
    /**
     * 测试分布式链路追踪
     */
    @GetMapping("/pay/micrometer/{id}")
    public String micrometer(@PathVariable("id") int id);

    /**
     * 测试网关查询
     * @param id
     * @return
     */
    @GetMapping("pay/gateway/get/{id}")
    public ResultData gatewayById(@PathVariable("id") int id);

    /**
     * 测试网关查询2
     * @return
     */
    @GetMapping(value = "pay/gateway/info")
    public ResultData<String> getGatewayInfo();

    /**
     * 测试网关过滤器
     * @param request
     * @return
     */
    @GetMapping(value = "pay/gateway/filter")
    public ResultData<String> getGatewayFilter(HttpServletRequest request);
}
