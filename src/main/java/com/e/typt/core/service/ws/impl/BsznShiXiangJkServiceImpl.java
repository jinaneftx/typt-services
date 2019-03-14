package com.e.typt.core.service.ws.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e.typt.core.entity.dto.ws.TaSpApproveInfo;
import com.e.typt.core.entity.dto.ws.TaSpApproveMaterial;
import com.e.typt.core.entity.dto.ws.TaSpApproveResult;
import com.e.typt.core.entity.dto.ws.TaSpRightsInfo;
import com.e.typt.core.entity.dto.ws.TaSpServicesGuide;
import com.e.typt.core.mapper.ws.BsznShiXiangJkMapper;
import com.e.typt.core.service.ws.BsznShiXiangJkService;
@WebService(serviceName = "BsznShiXiangJkService", // 与接口中指定的name一致
targetNamespace = "http://ws.service.core.typt.e.com/", // 与接口中的命名空间一致,一般是接口的包名倒
endpointInterface = "com.e.typt.core.service.ws.BsznShiXiangJkService"// 接口地址
)
@Component
public class BsznShiXiangJkServiceImpl implements BsznShiXiangJkService {

	@Autowired
	BsznShiXiangJkMapper bsznShiXiangJkMapper;
	
	@Override
	public TaSpServicesGuide taSpServicesGuideSel(String approveId) {
		TaSpServicesGuide taSpServicesGuide=bsznShiXiangJkMapper.taSpServicesGuideSel(approveId);
		return taSpServicesGuide;
	}

	@Override
	public TaSpApproveInfo taSpApproveInfoSel(String approveId) {
		TaSpApproveInfo taSpApproveInfo=bsznShiXiangJkMapper.taSpApproveInfoSel(approveId);
		return taSpApproveInfo;
	}

	@Override
	public TaSpRightsInfo taSpRightsInfoSel(String rightsId) {
		TaSpRightsInfo taSpRightsInfo=bsznShiXiangJkMapper.taSpRightsInfoSel(rightsId);
		return taSpRightsInfo;
	}

	@Override
	public TaSpApproveMaterial taSpApproveMaterialSel(String approveId) {
		TaSpApproveMaterial taSpApproveMaterial =bsznShiXiangJkMapper.taSpApproveMaterialSel(approveId);
		return taSpApproveMaterial;
	}

	@Override
	public TaSpApproveResult taSpApproveResultSel(String attachId) {
		TaSpApproveResult taSpApproveResult=bsznShiXiangJkMapper.taSpApproveResultSel(attachId);
		return taSpApproveResult;
	}

}
