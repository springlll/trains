package com.qk.trains.service;

import com.qk.trains.entity.TestMode;
import com.qk.trains.mapper.TestModeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: trains
 * @description: 试验性质服务
 * @author: Xiaotian
 * @create: 2018-09-19 09:28
 **/
@Service
public class TestModeService {

	@Autowired
	TestModeMapper testModeMapper;

	public List<TestMode> getTestModes() {
		return testModeMapper.listAll();
	}

	public TestMode getDefaultTestMode() {
		return testModeMapper.getDefaultTestMode();
	}
}
