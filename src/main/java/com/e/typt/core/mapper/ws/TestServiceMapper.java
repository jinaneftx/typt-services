package com.e.typt.core.mapper.ws;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TestServiceMapper {
	@Select("select count(0) from xxkc.data_check")
	public String testMybatis(String id);
	
	public String testMybatis1(String id);
}
