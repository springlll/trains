package com.qk.trains.service;

import com.qk.trains.entity.Direction;
import com.qk.trains.entity.Track;
import com.qk.trains.mapper.DirectionMapper;
import com.qk.trains.mapper.TrackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: trains
 * @description: 方位服务类
 * @author: Xiaotian
 * @create: 2018-08-31 10:47
 **/
@Service
public class DirectionService {
	@Autowired
	DirectionMapper directionMapper;

	/**
	 * 获取方位列表
	 *
	 * @return 方位集合
	 */

	@Cacheable(cacheNames = "directions")
	public List<Direction> getDirectionList() {
		return directionMapper.listAllActive();
	}
}
