package com.e.typt.core.entity.dto.ws;

import lombok.Data;

@Data
public class TaSpApproveInfo {
	
	//基本编码
	private String rightscode;
	//目录ID
	private String rightsid;
	//事项ID
	private String approveid;
	//基础编码+实施编码
	private String approvecode;
	//主基本编码
	private String parentcode;
	//主项名称
	private String parentname;
	//事项名称
	private String approvename;
	//区域编码
	private String areacode;
	//实施机构
	private String orgid;
	//部门名称
	private String orgname;
	//事项类型
	private String typecode;
	//办件类型
	private String transtype;
	//事项来源
	private String approvesource;
	//事项性质
	private String approveproperties;
	//办理深度
	private String transactlevel;
	//最少次数
	private String minseq;
	//标准说明
	private String minexplain;
	//服务对象
	private String serveobject;
	//法人对象分类
	private String themetype;
	//生命周期分类
	private String lifeconern;
	//是否为自有系统办理
	private String isselfsystem;
	//事项状态
	private String approvestate;
	//版本号
	private String versionnum;
	//备注
	private String remark;
	//是否收费
	private String ischarge;
	//是否中介服务事项
	private String isintermediaryservices;
	//数据是否有效
	private String isvalid;
	//自然人分类
	private String naturalpersontype;
	//认证类型
	private String certificationtype;
	//场景分类
	private String scenetype;
	//主事项编码
	private String approvecodemain;
	//权限划分
	private String authoritydivision;
	//行使内容
	private String approvecontent;
	//实施主体性质
	private String implementationsubject;
	//受理条件
	private String acceptancecondition;
	//联办机构
	private String jointagency;
	//数量限制
	private String quantitativerestriction;
	//人生事件
	private String lifeevent;
	//特定对象分类
	private String specificobjectclassification;
	//经营活动分类
	private String businessactivityclassificati;
	//运行系统
	private String operationsystem;
	//行政复议及诉讼
	private String administrativereconsideration;
	//群体分类
	private String peoplecode;
	//已有业务系统
	private String selfsystem;
	//咨询回复时限
	private String consultlimit;
	//排序号
	private String approvesn;
	//申请人权利
	private String applicanepower;
	//申请人义务
	private String applicantduy;
	//行政复议部门的名称
	private String xzfydepname;
	//行政复议部门的地址
	private String xzfydepaddress;
	//行政复议部门的电话
	private String xzfydepphone;
	//行政复议部门的网址
	private String xzfydepwebsite;
	//行政诉讼部门的名称
	private String xzssdepname;
	//行政诉讼部门的地址
	private String xzssdepaddess;
	//行政诉讼部门的电话
	private String xzssdepphone;
	//行政诉讼部门的网址
	private String xzssdepwebsite;
	//最后修改时间
	private String lastupdatetime;
	//分发代码对应厅局或市州
	private String systemid;
	//数据操作标志位是否已交换处理
	private String modflag;
	
}
