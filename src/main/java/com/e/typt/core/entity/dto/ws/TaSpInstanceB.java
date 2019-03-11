package com.e.typt.core.entity.dto.ws;

import lombok.Data;

@Data
public class TaSpInstanceB {
	//实例编码
	private String instancecode;
	//事项编码
	private String approvecode;
	//项目名称
	private String instancename;
	//受理人
	private String acceptname;
	//受理时间
	private String accepttime;
	//申请者ID
	private String applyid;
	//申请者类型
	private String applytype;
	//申请者名称
	private String applyname;
	//办理机构ID
	private String orgid;
	//区域编码
	private String areacode;
	//项目状态
	private String projectstate;
	//不予受理或补正补齐原因
	private String notacceptreason;
	//补正补齐时间
	private String correctiontime;
	//事项ID
	private String approveid;
	//办件来源
	private String instancesource;
	//提交时间
	private String submittime;
	//备注
	private String remark;
	//事项名称
	private String approcename;
	//机构名称
	private String orgname;
	//最后修改时间
	private String lastupdatetime;
	//分发代码对应厅局或市州
	private String systemid;
	//数据操作标志位是否已交换处理
	private String modflag;
	//数据交换操作时间
	private String modupdatatime;

}
