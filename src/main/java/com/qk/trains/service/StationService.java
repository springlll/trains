package com.qk.trains.service;

import com.qk.trains.entity.Device;
import com.qk.trains.entity.Station;
import com.qk.trains.mapper.DeviceMapper;
import com.qk.trains.mapper.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: trains
 * @description: 站点服务
 * @author: Xiaotian
 * @create: 2018-09-21 14:32
 **/
@Service
public class StationService {

	@Autowired
	StationMapper stationMapper;

	@Cacheable(cacheNames = "stations")
	public List<Station> getAllStation() {
		return stationMapper.listAll();
	}

	@CacheEvict(cacheNames = "stations")
	public void addStation(String name, String remarks) {
		Station station = new Station();
		station.setName(name);
		station.setRemarks(remarks);
		stationMapper.add(station);
	}

	@CacheEvict(cacheNames = "stations")
	public void deleteStation(int id) {
		stationMapper.delete(id);
	}

	@CacheEvict(cacheNames = "stations")
	public void updateStation(int id, String name, String remarks) {
		Station station = new Station();
		station.setId(id);
		station.setName(name);
		station.setRemarks(remarks);
		stationMapper.update(station);
	}
}
