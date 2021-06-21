package com.exciter.learn07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession() //自动化配置Spring Session使用Redis作为数据源
public class SessionConfiguration {

    /**
     * 创建{@link RedisOperationsSessionRepository} 使用的RedisSerializer Bean
     * @return RedisSerializer Bean
     */
    @Bean(name = "springSessionDefaultRedisSerializer")
    public RedisSerializer springSessionDefaultRedisSerializer(){
        return RedisSerializer.json();
    }
}
