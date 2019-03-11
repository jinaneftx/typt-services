package com.e.typt.core.service.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.e.typt.core.entity.dto.ws.GjjShTable;
import com.e.typt.core.entity.dto.ws.GjjTestTable;


/**
 * 接口
 * 
 *
 */
@WebService(name = "XXKCService", // 暴露服务名称
		targetNamespace = "http://ws.service.core.typt.e.com/"// 命名空间,一般是接口的包名倒序
)
public interface XXKCService {
	
	@WebMethod(operationName="test",action="")
	@WebResult(name = "test", targetNamespace = "")
	public String test(@WebParam(name = "FWType") String FWType,
			@WebParam(name = "FWName") String FWName,
			@WebParam(name = "methodName") String methodName,
			@WebParam(name = "args") String args);
	
	@WebMethod(operationName="gjjTestTableXx",action="")
	@WebResult(name = "gjjTestTableXx", targetNamespace = "")
	public GjjTestTable gjjTestTableXx(@WebParam(name="sfzh")String sfzh,@WebParam(name="ywdjh")String ywdjh);
	@WebMethod(operationName="gjjShTableXx",action="")
	@WebResult(name = "gjjShTableXx", targetNamespace = "")
	public GjjShTable gjjShTableXx(@WebParam(name="sfzh")String sfzh,@WebParam(name="ywdjh")String ywdjh);

}