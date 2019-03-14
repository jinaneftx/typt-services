package com.e.typt.core.mapper.ws;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.e.typt.core.entity.dto.ws.TaSpApproveInfo;
import com.e.typt.core.entity.dto.ws.TaSpApproveMaterial;
import com.e.typt.core.entity.dto.ws.TaSpApproveResult;
import com.e.typt.core.entity.dto.ws.TaSpRightsInfo;
import com.e.typt.core.entity.dto.ws.TaSpServicesGuide;

@Mapper
@Repository
public interface BsznShiXiangJkMapper {
	
	//实施清单办事指南表查询  事项id
	public TaSpServicesGuide taSpServicesGuideSel(@Param("approveId")String approveId);
	//实施清单基本信息表查询  事项id
	public TaSpApproveInfo taSpApproveInfoSel(@Param("approveId")String approveId);
	//目录清单表查询  目录id
	public TaSpRightsInfo taSpRightsInfoSel(@Param("rightsId")String rightsId);
	//实施清单材料表查询  事项id
	public TaSpApproveMaterial taSpApproveMaterialSel(@Param("approveId")String approveId);
	//材料附件表查询  附件id
	public TaSpApproveResult taSpApproveResultSel(@Param("attachId")String attachId);
	

}
