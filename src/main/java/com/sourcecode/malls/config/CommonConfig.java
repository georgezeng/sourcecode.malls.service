package com.sourcecode.malls.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.sourcecode.malls.properties.RedisSessionProperties;

@Configuration
@EnableScheduling
@EnableRedisHttpSession
@EnableAspectJAutoProxy
@EnableRedisRepositories(basePackages="com.sourcecode.malls.repository.redis.impl")
public class CommonConfig {
	@Autowired
	private RedisSessionProperties redisProperties;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public LettuceConnectionFactory connectionFactory() {
		LettuceConnectionFactory factory = new LettuceConnectionFactory();
		factory.getStandaloneConfiguration().setHostName(redisProperties.getHost());
		factory.getStandaloneConfiguration().setPort(redisProperties.getPort());
		factory.getStandaloneConfiguration().setPassword(RedisPassword.of(redisProperties.getPassword()));
		return factory;
	}
	
	@Bean
	public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
		return new OpenEntityManagerInViewFilter();
	}
}