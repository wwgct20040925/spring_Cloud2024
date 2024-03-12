package com.gct.gateway;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {
    @Override
    public GatewayFilter apply(MyGatewayFilterFactory.Config config) {

        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                System.out.println(config.getGct()+"读取配置");
                if (exchange.getRequest().getQueryParams().containsKey(config.getGct())) {
                    return chain.filter(exchange);
                }
                else {
                    exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
                    return exchange.getResponse().setComplete();
                }

            }
        };
    }
    public MyGatewayFilterFactory()
    {
        super(MyGatewayFilterFactory.Config.class);
    }


    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("gct");
    }
    public static class Config{
        @Getter@Setter
        private String gct;

    }
}
