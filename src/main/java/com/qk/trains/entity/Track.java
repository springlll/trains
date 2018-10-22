package com.qk.trains.entity;

import java.util.Date;
import java.util.List;

/**
 * @program: trains
 * @description: 股道实体类
 * @author: Xiaotian
 * @create: 2018-08-31 10:22
 **/
public class Track {

	private Integer trainActivityId;

	private Integer stationId;

	private Integer track;

	private String train;
	
	private Date activityTime;
	
	private Integer activity;

	//private List<LightActivity> lightActivity;

	//private List<LightData> lightData;
	
	private String position;
	
	/**
	 * 对应数据库字段 direction
	 * 方位
	 */
	private Integer direction;
	
	/**分支修改
	 * 对应数据库字段 operator
	 * 作业者名
	 */
	private String operator;

	/**
	 * 对应数据库字段 card_id
	 * 作业员卡号ID
	 */
	public String cardId;
	
	/**分支修改
	 * 对应数据库字段 imei10
	 * 设备编号
	 */
	public String imei10;
	
	/**
	 * 对应数据库字段 machine_number
	 * 设备编号
	 */
	public String machineNumber;

	/**
	 * 对应数据库字段 in_place_time
	 * 到位时间
	 */
	private Date inPlaceTime;

	/**
	 * 对应数据库字段 up_signal_time
	 * 上信号时间
	 */
	private Date upSignalTime;

	/**
	 * 对应数据库字段 down_signal_time
	 * 下信号时间
	 */
	private Date downSignalTime;

	/**
	 * 对应数据库字段 end_time
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 对应数据库字段 image_files
	 * 图片文件集合字符串
	 */
	private String imageFiles;
	/**
	 * 对应数据库字段 signal_value
	 * 信号值
	 */
	public Integer signalValue;
	/**
	 * 对应数据库字段 save_time
	 * 刷新时间
	 */
	public Date saveTime;
	/**
	 * 对应数据库字段 acceleration
	 * 车动报警
	 */
	private Integer acceleration;
	/**
	 * 对应数据库字段 light_signal
	 * 灯闪
	 */
	private Integer lightSignal;
	/**
	 * 对应数据库字段image_files
	 * 图片
	 */
	
	
	public Integer getLightSignal() {
		return lightSignal;
	}

	public void setLightSignal(Integer lightSignal) {
		this.lightSignal = lightSignal;
	}

	public Integer getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Integer acceleration) {
		this.acceleration = acceleration;
	}

	public Date getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}

	public Date getInPlaceTime() {
		return inPlaceTime;
	}

	public void setInPlaceTime(Date inPlaceTime) {
		this.inPlaceTime = inPlaceTime;
	}

	public Date getUpSignalTime() {
		return upSignalTime;
	}

	public void setUpSignalTime(Date upSignalTime) {
		this.upSignalTime = upSignalTime;
	}

	public Date getDownSignalTime() {
		return downSignalTime;
	}

	public void setDownSignalTime(Date downSignalTime) {
		this.downSignalTime = downSignalTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getSignalValue() {
		return signalValue;
	}

	public void setSignalValue(Integer signalValue) {
		this.signalValue = signalValue;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getImei10() {
		return imei10;
	}

	public void setImei10(String imei10) {
		this.imei10 = imei10;
	}

	public String getMachineNumber() {
		return machineNumber;
	}

	public void setMachineNumber(String machineNumber) {
		this.machineNumber = machineNumber;
	}

	
	
	
	public String getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(String imageFiles) {
		this.imageFiles = imageFiles;
	}

	public Integer getTrainActivityId() {
		return trainActivityId;
	}

	public void setTrainActivityId(Integer trainActivityId) {
		this.trainActivityId = trainActivityId;
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

	public String getTrain() {
		return train;
	}

	public void setTrain(String train) {
		this.train = train;
	}

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}



	

	
	
}
