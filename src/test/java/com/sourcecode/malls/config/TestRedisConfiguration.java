package com.sourcecode.malls.config;

import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class TestRedisConfiguration {

    private RedisServer redisServer;

    public TestRedisConfiguration(RedisProperties redisProperties) {
        this.redisServer = new RedisServer(redisProperties.getRedisPort());
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}