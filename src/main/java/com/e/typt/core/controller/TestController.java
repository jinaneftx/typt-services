package com.e.typt.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.e.typt.core.service.ws.TestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value={"/test"})
@Api(tags={"测试方法"})
@Slf4j
public class TestController {
	@Autowired
	private TestService testService;
	@RequestMapping("/test.api")
	@ApiOperation(value = "测试.api")
	public String test(String param){
		
		return "return "+param;
	}
	@RequestMapping("/ZBSZFGJJGLZX/queryGjjDWZHXX.api")
	@ApiOperation(value = "淄博接口测试.api")
	public JSONObject test1(String token,String spnode){
		log.debug("token:"+token+",spnode:"+spnode);
		JSONObject o = new JSONObject();
		JSONObject body = new JSONObject();
		JSONObject accInfo = new JSONObject();
		accInfo.put("spidno", "370322198302264912");
		accInfo.put("grzfgjjzhzt", "正常");
		accInfo.put("jcbl", "24");
		accInfo.put("spcode", "935563753");
		accInfo.put("gzdwmc", "山东天璨环保科技有限公司");
		accInfo.put("spname", "李慧远");
		accInfo.put("khrq", "20130827");
		accInfo.put("spgz", "4800");
		JSONArray accInfos = new JSONArray();
		accInfos.add(accInfo);
		accInfos.add(accInfo);
		body.put("total", 0);
		body.put("list", accInfos);
		o.put("body", body);
		return o;
	}
	
	@RequestMapping("/testMybatis")
	@ApiOperation(value = "testMybatis")
	public String testMybatis(String param){
		
		return testService.testMybatis(param);
	}
	@RequestMapping("/testMybatis1")
	@ApiOperation(value = "testMybatis1")
	public String testMybatis1(String param){
		
		return testService.testMybatis1(param);
	}
}
