package com.qk.trains.vo;

/**
 * @program: trains
 * @description: 信号灯实时数据实体
 * @author: Xiaotian
 * @create: 2018-09-05 10:52
 **/
public class LightRealtimeData {
	/**
	 * 股道
	 */
	private int track;

	/**
	 * 方位
	 */
	private int direction;

	/**
	 * 作业者
	 */
	private String workerName;

	/**
	 * 电压
	 */
	private double voltage;

	/**
	 * 信号灯状态
	 */
	private int lightStatus;

	/**
	 * 到位时间
	 */
	private String inPlaceTime;

	/**
	 * 上信号时间
	 */
	private String upSignalTime;

	/**
	 * 下信号时间
	 */
	private String downSignalTime;

	/**
	 * 信号值
	 */
	private int signalValue;

	/**
	 * 车状态
	 */
	private String trainStatus;

	public int getTrack() {
		return track;
	}

	public void setTrack(int track) {
		this.track = track;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public double getVoltage() {
		return voltage;
	}

	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}

	public int getLightStatus() {
		return lightStatus;
	}

	public void setLightStatus(int lightStatus) {
		this.lightStatus = lightStatus;
	}

	public String getInPlaceTime() {
		return inPlaceTime;
	}

	public void setInPlaceTime(String inPlaceTime) {
		this.inPlaceTime = inPlaceTime;
	}

	public String getUpSignalTime() {
		return upSignalTime;
	}

	public void setUpSignalTime(String upSignalTime) {
		this.upSignalTime = upSignalTime;
	}

	public String getDownSignalTime() {
		return downSignalTime;
	}

	public void setDownSignalTime(String downSignalTime) {
		this.downSignalTime = downSignalTime;
	}

	public int getSignalValue() {
		return signalValue;
	}

	public void setSignalValue(int signalValue) {
		this.signalValue = signalValue;
	}

	public String getTrainStatus() {
		return trainStatus;
	}

	public void setTrainStatus(String trainStatus) {
		this.trainStatus = trainStatus;
	}
}
