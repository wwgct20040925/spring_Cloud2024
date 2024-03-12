package com.gct.gateway;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
@Component
@Slf4j
public class MyTypeRoutePredicateFactory extends AbstractRoutePredicateFactory<MyTypeRoutePredicateFactory.Config> {

    public MyTypeRoutePredicateFactory(){
        super(MyTypeRoutePredicateFactory.Config.class);
    }
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String type = serverWebExchange.getRequest().getQueryParams().getFirst("Type");
                if(type==null){
                    return false;
                }
                if (type.equals(config.getType())){
                    return true;
                }
                return false;
            }
        };
    }
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("Type");
    }

    @Validated
    public static class Config{
        @NotEmpty
        @Setter
        @Getter
        private String Type;
    }
}
