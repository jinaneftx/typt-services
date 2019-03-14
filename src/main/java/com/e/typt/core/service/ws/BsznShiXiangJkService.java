package com.e.typt.core.service.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.ibatis.annotations.Param;

import com.e.typt.core.entity.dto.ws.GjjTestTable;
import com.e.typt.core.entity.dto.ws.TaSpApproveInfo;
import com.e.typt.core.entity.dto.ws.TaSpApproveMaterial;
import com.e.typt.core.entity.dto.ws.TaSpApproveResult;
import com.e.typt.core.entity.dto.ws.TaSpRightsInfo;
import com.e.typt.core.entity.dto.ws.TaSpServicesGuide;

@WebService(name = "BsznShiXiangJkService", // 暴露服务名称
targetNamespace = "http://ws.service.core.typt.e.com/"// 命名空间,一般是接口的包名倒序
)
public interface BsznShiXiangJkService {

	//实施清单办事指南表查询  事项id
	@WebMethod(operationName="taSpServicesGuideSel",action="")
	@WebResult(name = "taSpServicesGuideSel", targetNamespace = "")
	public TaSpServicesGuide taSpServicesGuideSel(@Param("approveId")String approveId);
	//实施清单基本信息表查询  事项id
	@WebMethod(operationName="taSpApproveInfoSel",action="")
	@WebResult(name = "taSpApproveInfoSel", targetNamespace = "")
	public TaSpApproveInfo taSpApproveInfoSel(@Param("approveId")String approveId);
	//目录清单表查询  目录id
	@WebMethod(operationName="taSpRightsInfoSel",action="")
	@WebResult(name = "taSpRightsInfoSel", targetNamespace = "")
	public TaSpRightsInfo taSpRightsInfoSel(@Param("rightsId")String rightsId);
	//实施清单材料表查询  事项id
	@WebMethod(operationName="taSpApproveMaterialSel",action="")
	@WebResult(name = "taSpApproveMaterialSel", targetNamespace = "")
	public TaSpApproveMaterial taSpApproveMaterialSel(@Param("approveId")String approveId);
	//材料附件表查询  附件id
	@WebMethod(operationName="taSpApproveResultSel",action="")
	@WebResult(name = "taSpApproveResultSel", targetNamespace = "")
	public TaSpApproveResult taSpApproveResultSel(@Param("attachId")String attachId);
}
