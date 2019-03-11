package com.e.typt.core.config.ws;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.Service;
import org.apache.cxf.service.invoker.MethodDispatcher;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 * 通过PhaseInterceptor，可以指定拦截器在哪个阶段起作用
 *
 */
@Component
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
	public String userId;
	public String userPass;
	//由于AbstractPhaseInterceptor无无参数构造器，使用继承的方式，需要显示调用父类有参数的构造器
	public AuthInterceptor() {
		//super表示显示调用父类有参数的构造器
		//显示调用父类构造器之后，程序将不会隐式调用父类无参数的构造器
		super(Phase.PRE_INVOKE);
	}
	//实现自己的拦截器时，需要实现handleMessage方法。
	//handleMessage方法中的形参就是被拦截到的Soap消息
	//一旦程序获取了SOAP消息，剩下的事情就可以解析SOAP消息或修改SOAP消息
	@Override
	public void handleMessage(SoapMessage msg) throws Fault {
		System.out.println("-------"+msg);
		//从这里可以看出，我们已经拦截到了SOAP消息
		
		Exchange exchange = msg.getExchange();  
        BindingOperationInfo bop = exchange.get(BindingOperationInfo.class);  
        MethodDispatcher md = (MethodDispatcher) exchange.get(Service.class)  
                .get(MethodDispatcher.class.getName());  
        Method method = md.getMethod(bop);  
        System.out.println("********method name:" + method.getName()); 
		
		//得到SOAP消息所有Header
		List<Header> headers=msg.getHeaders();
		
		//如果没有Header
		if(headers==null||headers.size()<1){
			throw new Fault(new IllegalArgumentException("根本没有Header，不能调用"));						
		}
		
		//假如要求第一个Header携带了用户名，密码信息
		Header firstHeader=headers.get(0);
		Element ele=(Element)firstHeader.getObject();
		
		NodeList userIds=ele.getElementsByTagName("userId");
		NodeList userPasses=ele.getElementsByTagName("userPass");
		/*int length = userIds.getLength();
		Node item = userIds.item(0);*/
		System.out.println(userIds);
		System.out.println(userPasses);
		if(userIds.getLength()!=1){
			throw new Fault(new IllegalArgumentException("用户名的格式不正确！"));
		}
		if(userPasses.getLength()!=1){
			throw new Fault(new IllegalArgumentException("密码的格式不正确！"));
		}
		
		//得到第一个userId元素里的文本内容，以该内容作为用户名字
		userId=userIds.item(0).getTextContent();
		userPass=userPasses.item(0).getTextContent();
		//实际项目中，应该去查询数据库，该用户名密码是否被授权调用web service
		if(!userId.equals("admin") || !userPass.equals("123456")){
			throw new Fault(new IllegalArgumentException("用户名密码不正确！"));
		}

		
	}
	
	

}
