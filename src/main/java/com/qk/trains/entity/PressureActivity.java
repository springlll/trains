package com.qk.trains.entity;

import java.util.Date;

public class PressureActivity {
	/**
	 * 对应数据库字段 id
	 */
	private Long id;

	/**
	 * 对应数据库字段 train_activity_id
	 * 列车活动ID，需要先存储列车活动，才能获取到活动ID
	 */
	private Long trainActivityId;
	
	/**
	 * 对应数据库字段 machine_number
	 * 设备编号
	 */
	private String machineNumber;

	/**
	 * 对应数据库字段 in_place_time
	 * 到位时间
	 */
	private Date inPlaceTime;
	
	/**
	 * 对应数据库字段 end_time
	 * 结束时间
	 */
	private Date endTime;
	
	/**
	 * 对应数据库字段 peak_pressure
	 * 峰值压力值
	 */
	private Integer peakPressure;
	/**
	 * 对应数据库字段 activity
	 * 是否活动
	 */
	private Boolean activity;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTrainActivityId() {
		return trainActivityId;
	}
	public void setTrainActivityId(Long trainActivityId) {
		this.trainActivityId = trainActivityId;
	}
	public String getMachineNumber() {
		return machineNumber;
	}
	public void setMachineNumber(String machineNumber) {
		this.machineNumber = machineNumber;
	}
	public Date getInPlaceTime() {
		return inPlaceTime;
	}
	public void setInPlaceTime(Date inPlaceTime) {
		this.inPlaceTime = inPlaceTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getPeakPressure() {
		return peakPressure;
	}
	public void setPeakPressure(Integer peakPressure) {
		this.peakPressure = peakPressure;
	}
	public Boolean getActivity() {
		return activity;
	}
	public void setActivity(Boolean activity) {
		this.activity = activity;
	}
	
	@Override
	public String toString() {
		return "LightActivity{" +
				"id=" + id +
				", trainActivityId=" + trainActivityId +
				", machineNumber=" + machineNumber +
				", inPlaceTime=" + inPlaceTime +
				", endTime=" + endTime +
				", peakPressure='" + peakPressure + '\'' +
				", activity=" + activity +
				'}';
	}
}
