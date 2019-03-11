package com.e.typt.core.service.ws.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e.typt.core.entity.dto.ws.GjjShTable;
import com.e.typt.core.entity.dto.ws.GjjTestTable;
import com.e.typt.core.mapper.ws.GjjTestTableMapper;
import com.e.typt.core.service.ws.XXKCService;
/**
 * 接口实现
 * 
 *
 */
@WebService(serviceName = "XXKCService", // 与接口中指定的name一致
		targetNamespace = "http://ws.service.core.typt.e.com/", // 与接口中的命名空间一致,一般是接口的包名倒
		endpointInterface = "com.e.typt.core.service.ws.XXKCService"// 接口地址
)
@Component
public class XXKCServiceImpl implements XXKCService {
	@Autowired
	GjjTestTableMapper gjjTestTableMapper;

	@Override
	public String test(String FWType, String FWName, String methodName, String args) {
		return "XXKCServiceImpl.test";
	}

	@Override
	public GjjTestTable gjjTestTableXx(String sfzh, String ywdjh) {
		GjjTestTable gjjTestTable=gjjTestTableMapper.gjjTestTableXx(sfzh, ywdjh);
		return gjjTestTable;
	}

	@Override
	public GjjShTable gjjShTableXx(String sfzh, String ywdjh) {
		GjjShTable gjjShTable=gjjTestTableMapper.gjjShTableXx(sfzh, ywdjh);
		return gjjShTable;
	}

	
}
