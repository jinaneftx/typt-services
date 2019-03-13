package com.e.typt.core.entity.dto.ws;

import lombok.Data;

@Data
public class TaSpApproveMaterial {

	  //材料ID
	  private String materialid;
	  //事项ID
	  private String approveid;
	  //类型ID
	  private String typeid;
	  //材料名称
	  private String materialtitle;
	  //材料拼音
	  private String materialpy;
	  //份数
	  private String copiesnum;
	  //材料规格
	  private String materialstandard;
	  //是否需要电子材料
	  private String iselectronicmaterial;
	  //提交方式
	  private String commitway;
	  //受理标准
	  private String acceptstandard;
	  //排序
	  private String ordernum;
	  //是否有空白模板
	  private String isblank;
	  //空白模板名称
	  private String blankname;
	  //是否有示范文本
	  private String ismodel;
	  //示范文本名称
	  private String modelname;
	  //来源渠道
	  private String sourcechannel;
	  //填报须知
	  private String materialexplain;
	  //材料类型
	  private String materialtype;
	  //最后修改时间
	  private String lastupdatetime;
	  //分发代码对应厅局或市州
	  private String systemid;
	  //数据操作标志位是否已交换处理
	  private String modflag;
}
