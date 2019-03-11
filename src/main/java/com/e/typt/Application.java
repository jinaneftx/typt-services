package com.e.typt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.e.typt.common.util.SpringBeanUtil;


@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableCaching
public class Application {
	private static ApplicationContext applicationContext;

    public static void main(String[] args) {
    	applicationContext = SpringApplication.run(Application.class, args);
    	SpringBeanUtil.setApplicationContext(applicationContext);

    }
}
