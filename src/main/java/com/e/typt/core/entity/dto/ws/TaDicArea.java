package com.e.typt.core.entity.dto.ws;

import lombok.Data;

@Data
public class TaDicArea {
	
	//区域编码
	private String areacode;
	//区域名称
	private String areaname;
	//区域父编码
	private String parentcode;
	//状态
	private String state;
	//排序号
	private String ordernum;
	

}
