package com.e.typt.core.entity.dto.ws;

import lombok.Data;

@Data
public class TaSpInstanceMaterialB {

	//材料实例ID
	private String materialinsid;
	//事项材料ID
	private String materialid;
	//办件实例ID
	private String instanceid;
	//材料名称
	private String materialname;
	//份数
	private String copiesnum;
	//提交时间
	private String submittime;
	//排序
	private String ordernum;
	//提交状态
	private String submitstate;
	//最后修改时间
	private String lastupdatetime;
	//分发代码对应厅局或市州
	private String systemid;
	//数据操作标志位是否已交换处理
	private String modflag;
	//数据交换操作时间
	private String modupdatatime;
	//材料否有效
	private String remark1;
}
