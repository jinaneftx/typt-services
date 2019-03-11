package com.e.typt.core.config.ws;


import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.e.typt.core.service.ws.XXKCService;


@Configuration
public class CxfConfig {
	@Autowired
	private Bus bus;

	
	@Autowired
	XXKCService xxkcService;

	/** JAX-WS **/
/*	@Bean
	public Endpoint endpoint2() {
		EndpointImpl endpoint = new EndpointImpl(bus, commonService);
		endpoint.publish("/CommonService2");
		return endpoint;
	}*/
	
/*	@Bean
	public Endpoint endpoint3() {
		EndpointImpl endpoint = new EndpointImpl(bus, commonService);
		endpoint.publish("/CommonService3");
		LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
		endpoint.getInInterceptors().add(loggingInInterceptor);
		endpoint.getInInterceptors().add(new LoggingOutInterceptor());
		endpoint.getInInterceptors().add(new AuthInterceptor());
		
		String phase = loggingInInterceptor.getPhase();
		System.out.println("phase:--"+phase);
		
		//调用endpoint的publish方法，来发布web service
		Endpoint.publish("http://192.168.24.215:8889/hjy",hw);
		EndpointImpl ep=(EndpointImpl)Endpoint.publish("http://192.168.24.215:8891/hjy",hw);
		//添加In拦截器,该AuthInterceptor就会负责检查用户名，密码是否正确
		ep.getInInterceptors().add(new AuthInterceptor());
		//添加Out拦截器
		ep.getOutInterceptors().add(new LoggingOutInterceptor());

		return endpoint;
	}*/
	
	@Bean
	public Endpoint endpoint4() {
		EndpointImpl endpoint = new EndpointImpl(bus, xxkcService);
		endpoint.publish("/xxkcService");
		return endpoint;
	}
}
