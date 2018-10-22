package com.qk.trains.service;

import com.qk.trains.entity.BreakTest;
import com.qk.trains.entity.Direction;
import com.qk.trains.mapper.BreakTestMapper;
import com.qk.trains.mapper.DirectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: trains
 * @description: 方位服务类
 * @author: Xiaotian
 * @create: 2018-08-31 10:47
 **/
@Service
public class BreakTestService {

	@Autowired
	BreakTestMapper breakTestMapper;

	/**
	 * 获取方位列表
	 *
	 * @return 方位集合
	 */
	public List<BreakTest> getBreakTests() {
		return breakTestMapper.listAll();
	}
}
