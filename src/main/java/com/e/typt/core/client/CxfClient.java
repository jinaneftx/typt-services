package com.e.typt.core.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.e.typt.core.config.ws.AddHeaderInterceptor;



public class CxfClient {
	public static void main(String[] args) {
		cl2();
	}

	/**
	 * 方式1.代理类工厂的方式,需要拿到对方的接口
	 * http://localhost:8081/ws/CommonService2/sayHello?name=hhh
	 */
	/*public static void cl1() {
		try {
			// 接口地址
			String address = "http://localhost:8081/ws/CommonService3?wsdl";
			// 代理工厂
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			// 设置代理地址
			jaxWsProxyFactoryBean.setAddress(address);
			// 设置接口类型
			jaxWsProxyFactoryBean.setServiceClass(CommonService.class);
			// 创建一个代理接口实现
			CommonService cs = (CommonService) jaxWsProxyFactoryBean.create();
			// 数据准备
			String userName = "Leftso";
			// 调用代理接口的方法调用并返回结果
			//String result = cs.sayHello(userName);
			//System.out.println("返回结果:" + result);
			
			Client client = ClientProxy.getClient(cs);
			HTTPConduit http = (HTTPConduit) client.getConduit();
			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy ();
			httpClientPolicy.setConnectionTimeout (360000);
			httpClientPolicy.setAllowChunking (false);
			httpClientPolicy.setReceiveTimeout (320000);
			http.setClient (httpClientPolicy);
			
			client.getOutInterceptors().add(new AddHeaderInterceptor("admin", "123456"));
			//System.out.println(cs.sayHello("我是带着Header的sayHello").toString());
			System.out.println(cs.sayObj("Jack", 23).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * 动态调用方式
	 */
	public static void cl2() {
		// 创建动态客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://localhost:8081/ws/CommonService3?wsdl");
		// 需要密码的情况需要加上用户名和密码
		// client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
		client.getOutInterceptors().add(new AddHeaderInterceptor("admin", "123456"));
		Object[] objects = new Object[0];
		try {
			// invoke("方法名",参数1,参数2,参数3....);
			
			objects = client.invoke("sayObj", "Jean",18);
			System.out.println("返回数据:" + objects[0].toString());
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}
}
