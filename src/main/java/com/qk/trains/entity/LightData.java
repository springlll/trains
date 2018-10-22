package com.qk.trains.entity;

import java.util.Date;

/**
 * @program: trains
 * @description: 信号灯数据
 * @author: Xiaotian
 * @create: 2018-08-20 10:08
 **/
public class LightData {
	/**
	 * 对应数据库字段 id
	 */
	public Long id;

	/**
	 * 对应数据库字段 raw
	 * 存储原始数据
	 */
	public String raw;

	/**
	 * 对应数据库字段 save_time
	 * 数据保存时的时间
	 */
	public Date saveTime;

	/**
	 * 对应数据库字段 track
	 * 股道
	 */
	public Integer track;

	/**
	 * 对应数据库字段 imei10
	 * 设备imei编号后十位
	 */
	public String imei10;

	/**
	 * 对应数据库字段 send_time
	 * 数据发送时间
	 */
	public Date sendTime;

	/**
	 * 对应数据库字段 direction
	 * 方位数据
	 */
	public Integer direction;

	/**
	 * 对应数据库字段 card_id
	 * 操作员卡号ID
	 */
	public String cardId;

	/**
	 * 对应数据库字段 light_signal
	 * 信号灯状态
	 */
	public Integer lightSignal;

	/**
	 * 对应数据库字段 voltage
	 * 设备电压
	 */
	public Double voltage;

	/**
	 * 对应数据库字段 signal_value
	 * 信号值
	 */
	public Integer signalValue;

	/**
	 * 对应数据库字段 acceleration
	 * 加速度
	 */
	public Integer acceleration;

	/**
	 * 对应数据库字段 machine_number
	 * 设备编号
	 */
	public String machineNumber;

	/**
	 * 对应数据库字段 data_type
	 * 设备编号
	 */
	public int dataType;

	//以下为getter setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public Date getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}

	public Integer getTrack() {
		return track;
	}

	public void setTrack(Integer track) {
		this.track = track;
	}

	public String getImei10() {
		return imei10;
	}

	public void setImei10(String imei10) {
		this.imei10 = imei10;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Integer getLightSignal() {
		return lightSignal;
	}

	public void setLightSignal(Integer lightSignal) {
		this.lightSignal = lightSignal;
	}

	public Double getVoltage() {
		return voltage;
	}

	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}

	public Integer getSignalValue() {
		return signalValue;
	}

	public void setSignalValue(Integer signalValue) {
		this.signalValue = signalValue;
	}

	public Integer getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Integer acceleration) {
		this.acceleration = acceleration;
	}

	public String getMachineNumber() {
		return machineNumber;
	}

	public void setMachineNumber(String machineNumber) {
		this.machineNumber = machineNumber;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	@Override
	public String toString() {
		return "LightData{" +
				"id=" + id +
				", raw='" + raw + '\'' +
				", track=" + track +
				", imei10='" + imei10 + '\'' +
				", sendTime=" + sendTime +
				", direction=" + direction +
				", cardId='" + cardId + '\'' +
				", lightSignal=" + lightSignal +
				", voltage=" + voltage +
				", signalValue=" + signalValue +
				", acceleration=" + acceleration +
				", machineNumber='" + machineNumber + '\'' +
				'}';
	}
}
