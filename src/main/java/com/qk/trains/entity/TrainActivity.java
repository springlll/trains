package com.qk.trains.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: trains
 * @description: 列车活动实体类
 * @author: Xiaotian
 * @create: 2018-09-07 11:34
 **/
public class TrainActivity {
	/**
	 * 对应数据库字段 id
	 */
	private Long id;

	/**
	 * 对应数据库字段 start_time
	 * 活动开始时间
	 */
	private Long startTime;

	/**
	 * 对应数据库字段 finish_time
	 * 活动结束时间
	 */
	private Long finishTime;

	/**
	 * 对应数据库字段 station_id
	 * 站点编号
	 */
	private Integer stationId;

	/**
	 * 对应数据库字段 track
	 * 股道
	 */
	private Integer track;

	/**
	 * 对应数据库字段 train_number
	 * 列车号
	 */
	private String trainNumber;

	/**
	 * 对应数据库字段 test_mode_id
	 * 试风模式ID
	 */
	private Integer testModeId;

	/**
	 * 对应数据库字段 test_type
	 * 试风方式
	 */
	private Integer testType;

	/**
	 * 对应数据库字段 signal_type
	 * 信号性质
	 */
	private Integer signalType;

	/**
	 * 对应数据库字段 pressure_machine_number
	 * 试风仪机器编号
	 */
	private String pressureMachineNumber;

	/**
	 * 对应数据库字段 peak_pressure
	 * 峰值压力值
	 */
	private Integer peakPressure;

	/**
	 * 不写入数据库，历史数据取值从原始数据提取
	 * 实时风压值
	 */
	private Map<Long, Integer> realtimePressure;

	/**
	 * 对应数据库字段 pressure_end_time
	 * 试风结束时间
	 */
	private Long pressureEndTime;

	/**
	 * 不写入数据库，历史数据取值从finish_time
	 * 最后更新时间
	 */
	private Long lastUpdateTime;

	/**
	 * 对应数据库字段 lightActivities
	 * 信号灯活动列表，另外的表存储
	 */
	private List<LightActivity> lightActivities = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Long finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public Integer getTrack() {
		return track;
	}

	public void setTrack(Integer track) {
		this.track = track;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public Integer getTestModeId() {
		return testModeId;
	}

	public void setTestModeId(Integer testModeId) {
		this.testModeId = testModeId;
	}

	public Integer getTestType() {
		return testType;
	}

	public void setTestType(Integer testType) {
		this.testType = testType;
	}

	public Integer getSignalType() {
		return signalType;
	}

	public void setSignalType(Integer signalType) {
		this.signalType = signalType;
	}

	public String getPressureMachineNumber() {
		return pressureMachineNumber;
	}

	public void setPressureMachineNumber(String pressureMachineNumber) {
		this.pressureMachineNumber = pressureMachineNumber;
	}

	public Integer getPeakPressure() {
		return peakPressure;
	}

	public void setPeakPressure(Integer peakPressure) {
		this.peakPressure = peakPressure;
	}

	public Map<Long, Integer> getRealtimePressure() {
		return realtimePressure;
	}

	public void setRealtimePressure(Map<Long, Integer> realtimePressure) {
		this.realtimePressure = realtimePressure;
	}

	public Long getPressureEndTime() {
		return pressureEndTime;
	}

	public void setPressureEndTime(Long pressureEndTime) {
		this.pressureEndTime = pressureEndTime;
	}

	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public List<LightActivity> getLightActivities() {
		return lightActivities;
	}

	public void setLightActivities(List<LightActivity> lightActivities) {
		this.lightActivities = lightActivities;
	}

	@Override
	public String toString() {
		return "TrainActivity{" +
				"id=" + id +
				", startTime=" + startTime +
				", finishTime=" + finishTime +
				", stationId=" + stationId +
				", track=" + track +
				", trainNumber='" + trainNumber + '\'' +
				", testModeId=" + testModeId +
				", testType=" + testType +
				", signalType=" + signalType +
				", pressureMachineNumber='" + pressureMachineNumber + '\'' +
				", peakPressure=" + peakPressure +
				", realtimePressure=" + realtimePressure +
				", pressureEndTime=" + pressureEndTime +
				", lastUpdateTime=" + lastUpdateTime +
				", lightActivities=" + lightActivities +
				'}';
	}
	
	
	/**
	 * @program: trains  分支实体类与原程序兼容
	 * @description: 列车活动实体类
	 * @author: 陈泽键
	 * @create: 2018-09-28 
	 **/
	
	
	/**
	 * 对应数据库字段  train_activity_id
	 */
	private Long trainactivityid;
//	/**
//	 * 对应数据库字段 stationId 站点ID
//	 */
//	private Integer stationId;
//	/**
//	 * 对应数据库字段 track 股道号
//	 */
//	private Integer track;
	/**
	 * 对应数据库字段 train  车次
	 */
	private String train;
	/**
	 * 对应数据库字段 activity_time 活动时间
	 */
	private Date activitytime;
	/** 
	 * 对应数据库字段 activity 是否活动
	 */
	private Boolean activity;
	public Long getTrainactivityid() {
		return trainactivityid;
	}
	public void setTrainactivityid(Long trainactivityid) {
		this.trainactivityid = trainactivityid;
	}
//	public Integer getStationId() {
//		return stationId;
//	}
//	public void setStationId(Integer stationId) {
//		this.stationId = stationId;
//	}
//	public Integer getTrack() {
//		return track;
//	}
//	public void setTrack(Integer track) {
//		this.track = track;
//	}
	public String getTrain() {
		return train;
	}
	public void setTrain(String train) {
		this.train = train;
	}
	public Date getActivity_time() {
		return activitytime;
	}
	public void setActivity_time(Date activitytime) {
		this.activitytime = activitytime;
	}
	public Boolean getActivity() {
		return activity;
	}
	public void setActivity(Boolean activity) {
		this.activity = activity;
	}
	//分支使用
//	@Override   
//	public String toString() {
//		return "TrainActivity{" +
//				"trainactivityid=" + trainactivityid +
//				", stationId=" + stationId +
//				", track=" + track +
//				", train=" + train +
//				", track=" + track +
//				", activitytime='" + activitytime + '\'' +
//				", activity=" + activity +
//				'}';
//	}
}
