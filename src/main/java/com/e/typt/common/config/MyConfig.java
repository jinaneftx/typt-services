package com.e.typt.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;
@Configuration
@Slf4j
public class MyConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("MyConfig.addInterceptors");
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
	}
	/* Springboot配置静态资源swagger-ui.html不能访问
	 * 引入ftl配置时设置了spring: mvc: static-path-pattern: /static/**,
	 * 然后swagger-ui.html就打不开了，添加下面的配置后可以正常打开.
	 * 添加下面注释掉的写法后，接口提供的方法（资源管理中配置的那些）不能使用。
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
		//registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(0);
	}
}
