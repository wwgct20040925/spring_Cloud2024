package com.gct.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.gct.result.ResultData;
import com.gct.service.PayService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
@RequestMapping("pay/gateway")
public class PayGatewayController {

    @Resource
    private PayService payService;

    @GetMapping("get/{id}")
    public ResultData gatewayById(@PathVariable("id") int id) {
        long startTime = System.currentTimeMillis();
        ResultData resultData = payService.gatewayByid(id);
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime:"+(endTime-startTime)+" 毫秒");
        return resultData;
    }
    @GetMapping(value = "info")
    public ResultData<String> getGatewayInfo()
    {
        return ResultData.success("gateway info test："+ IdUtil.simpleUUID());
    }

    @GetMapping(value = "filter")
    public ResultData<String> getGatewayFilter(HttpServletRequest request)
    {
        String result = "";
        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements())
        {
            String headName = headers.nextElement();
            String headValue = request.getHeader(headName);
            System.out.println("请求头名: " + headName +"\t\t\t"+"请求头值: " + headValue);
            if(headName.equalsIgnoreCase("gct")
                    || headName.equalsIgnoreCase("zlq")) {
                result = result+headName + "\t " + headValue +" ";
            }
        }
        return ResultData.success("getGatewayFilter 过滤器 test： "+result+" \t "+ DateUtil.now());
    }
}
