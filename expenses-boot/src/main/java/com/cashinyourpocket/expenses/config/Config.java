package com.cashinyourpocket.expenses.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "com.cashinyourpocket.expenses.data.repository")
@ComponentScan("com.cashinyourpocket.expenses.aspects")
public class Config {

}