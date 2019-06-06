package com.sourcecode.malls.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EntityScan(basePackages = "com.sourcecode.malls.domain")
@EnableJpaRepositories(basePackages = "com.sourcecode.malls.repository.jpa.impl")
public class DataSourceConfig {
}
