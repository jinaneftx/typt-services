package com.e.typt.core.entity.dto.ws;

import lombok.Data;

@Data
public class TaSpApproveResult {

	//附件ID
	private String attachid;
	//附件编码
	private String attachcode;
	//清单材料表ID
	private String businessid;
	//附件名称
	private String attachname;
	//附件后缀
	private String attachtype;
	//附件路径
	private String attachpath;
	//附件内容
	private String attachcontent;
	//附件大小
	private String attachsize;
	//最后修改时间
	private String lastupdatime;
	//附件内容下发状态
	private String operateflag;
	//分发代码对应厅局或市州
	private String systemid;
	//数据操作标志位是否已交换处理
	private String modflag;
	
}
