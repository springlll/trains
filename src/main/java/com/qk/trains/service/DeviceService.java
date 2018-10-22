package com.qk.trains.service;

import com.qk.trains.entity.Device;
import com.qk.trains.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: trains
 * @description: 设备服务
 * @author: Xiaotian
 * @create: 2018-09-21 14:32
 **/
@Service
public class DeviceService {

	@Autowired
	DeviceMapper deviceMapper;

	@Cacheable(cacheNames = "devices")
	public List<Device> getAllDevice() {
		return deviceMapper.listAll();
	}

	@Cacheable(cacheNames = "stationIdByDevice", key = "#machineNumber")
	public Integer getStationIdByMachineNumber(String machineNumber) {
		return deviceMapper.getStationIdByMachineNumber(machineNumber);
	}

	@CacheEvict(cacheNames = "devices")
	public void addDevice(int type, String machineNumber, int stationId, String remarks) {
		Device device = new Device();
		device.setType(type);
		device.setMachineNumber(machineNumber);
		device.setStationId(stationId);
		device.setRemarks(remarks);
		deviceMapper.add(device);
	}

	@CacheEvict(cacheNames = "devices")
	public void deleteDevice(int id) {
		deviceMapper.delete(id);
	}

	@CacheEvict(cacheNames = "devices")
	public void updateDevice(int id, int type, String machineNumber, int stationId, long lastUsedTime, String remarks) {
		Device device = new Device();
		device.setId(id);
		device.setType(type);
		device.setMachineNumber(machineNumber);
		device.setStationId(stationId);
		device.setLastUsedTime(lastUsedTime);
		device.setRemarks(remarks);
		deviceMapper.update(device);
	}

	public void updateDeviceUsedTime(String machineNumber, long time) {
		deviceMapper.updateDeviceUsedTime(machineNumber, time);
	}
}
