package com.e.typt.core.mapper.ws;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.e.typt.core.entity.dto.ws.GjjShTable;
import com.e.typt.core.entity.dto.ws.GjjTestTable;


@Mapper
@Repository
public interface GjjTestTableMapper {
	
	public GjjTestTable gjjTestTableXx(@Param("sfzh")String sfzh,@Param("ywdjh")String ywdjh);
	
	public GjjShTable gjjShTableXx(@Param("sfzh")String sfzh,@Param("ywdjh")String ywdjh);

}
