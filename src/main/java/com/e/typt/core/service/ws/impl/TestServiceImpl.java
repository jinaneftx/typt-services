package com.e.typt.core.service.ws.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e.typt.core.mapper.ws.TestServiceMapper;
import com.e.typt.core.service.ws.TestService;
@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestServiceMapper t;
	@Override
	public String testMybatis(String id) {
		String testMybatis = t.testMybatis("a");
		System.out.println("testMybatis  "+testMybatis);
		return testMybatis;
	}
	@Override
	public String testMybatis1(String id) {
		String testMybatis = t.testMybatis1("a");
		System.out.println("testMybatis1  "+testMybatis);
		return testMybatis;
	}

}
