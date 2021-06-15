package com.exciter.learn05.controller;

import com.exciter.learn05.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> userListRouterFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/users2/list"), serverRequest -> {
            List<UserEntity> result = new ArrayList<>();
            result.add(new UserEntity().setId(1).setUsername("张三"));
            result.add(new UserEntity().setId(2).setUsername("李四"));
            result.add(new UserEntity().setId(3).setUsername("王五"));
            return ServerResponse.ok().bodyValue(result);
        });
    }

    @Bean
    public RouterFunction<ServerResponse> userGetRouterFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/users2/get"), serverRequest -> {
            Integer id = serverRequest.queryParam("id").map(s -> StringUtils.isEmpty(s) ? null : Integer.valueOf(s)).get();
            UserEntity userEntity = new UserEntity().setId(id).setUsername(UUID.randomUUID().toString());
            return ServerResponse.ok().bodyValue(userEntity);
        });
    }

    @Bean
    public RouterFunction<ServerResponse> demoRouterFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/users2/demo"), serverRequest -> ServerResponse.ok().bodyValue("demo"));
    }
}
