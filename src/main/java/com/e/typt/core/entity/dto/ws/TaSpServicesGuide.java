package com.e.typt.core.entity.dto.ws;

import lombok.Data;

@Data
public class TaSpServicesGuide {

	  //事项编码
	  private String approvecode;
	  //区域编码
	  private String areacode;
	  //事项名称
	  private String approvename;
	  //事项类型
	  private String typecode;
	  //设定依据
	  private String settinggist;
	  //办理形式
	  private String transacionfrom;
	  //通办范围
	  private String doscope;
	  //预约办理
	  private String maketransaction;
	  //网上支付
	  private String onlinepayment;
	  //物流快递
	  private String logisticsexpress;
	  //事项ID
	  private String approveid;
	  //法定期限
	  private String approvelimit;
	  //法定期限特殊情况说明
	  private String approvelimitexplain;
	  //承诺期限
	  private String commitmentlimit;
	  //承诺期限特殊情况说明
	  private String commitmentlimitexplain;
	  //收费标准
	  private String chargestandard;
	  //收费依据
	  private String chargegist;
	  //咨询电话
	  private String consulttel;
	  //投诉电话
	  private String complainttel;
	  //办理地点
	  private String transactaddresss;
	  //工作时间
	  private String worktime;
	  //版本号
	  private String versionnum;
	  //备注
	  private String remark;
	  //机构名称
	  private String orgname;
	  //结果样本名称
	  private String resultsamplename;
	  //特殊程序
	  private String specialprocedure;
	  //最后修改时间
	  private String lastupdatetime;
	  //分发代码对应厅局或市州
	  private String systemid;
	  //数据操作标志位是否已交换处理
	  private String modflag;
}
