package com.e.typt.core.entity.dto.ws;

import lombok.Data;

@Data
public class TaSpRightsInfo {

	  //目录ID
	  private String rightsid;
	  //基本编码
	  private String rightscode;
	  //目录父编码
	  private String parentcode;
	  //事项名称
	  private String rightsname;
	  //事项英文名称
	  private String rightsengname;
	  //事项类型
	  private String typecode;
	  //设定依据
	  private String according;
	  //目录状态
	  private String rightsstate;
	  //版本号
	  private String versionnum;
	  //备注
	  private String remark;
	  //区域编码
	  private String areacode;
	  //机构ID
	  private String orgid;
	  //父事项名称
	  private String parentname;
	  //行业编码
	  private String industrycode;
	  //行业名称
	  private String industryname;
	  //数据操作标志位是否已交换处理
	  private String modflag;
	  //最后修改时间
	  private String lastupdatetime;
	  //数据是否有效
	  private String isvalid;

}
