package com.qk.trains.entity;

/**
 * @program: trains
 * @description: 设备实体类
 * @author: Xiaotian
 * @create: 2018-09-20 11:05
 **/
public class Device {
	private Integer id;
	private Integer type;
	private String machineNumber;
	private Integer stationId;
	private Long lastUsedTime;
	private String remarks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMachineNumber() {
		return machineNumber;
	}

	public void setMachineNumber(String machineNumber) {
		this.machineNumber = machineNumber;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public Long getLastUsedTime() {
		return lastUsedTime;
	}

	public void setLastUsedTime(Long lastUsedTime) {
		this.lastUsedTime = lastUsedTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
