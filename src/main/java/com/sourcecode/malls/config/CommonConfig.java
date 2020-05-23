package com.sourcecode.malls.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

import com.sourcecode.malls.properties.RedisSessionProperties;

@Configuration
//@EnableCaching
@EnableConfigurationProperties
@EnableRedisHttpSession
@EnableAsync
@EnableAspectJAutoProxy
@EnableRedisRepositories(basePackages = "com.sourcecode.malls.repository.redis.impl")
public class CommonConfig {
	@Autowired
	private RedisSessionProperties redisProperties;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public LettuceConnectionFactory connectionFactory() {
		LettuceConnectionFactory factory = new LettuceConnectionFactory();
		factory.getStandaloneConfiguration().setHostName(redisProperties.getHost());
		factory.getStandaloneConfiguration().setPort(redisProperties.getPort());
		factory.getStandaloneConfiguration().setPassword(RedisPassword.of(redisProperties.getPassword()));
		return factory;
	}

}
