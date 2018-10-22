package com.qk.trains.entity;

import java.util.Date;

/**
 * @program: trains
 * @description: 试风数据
 * @author: Xiaotian
 * @create: 2018-08-21 10:29
 **/
public class PressureData {
	/**
	 * 对应数据库字段 id
	 */
	public Long id;

	/**
	 * 对应数据库字段 raw
	 * 存储原始数据
	 */
	private String raw;

	/**
	 * 对应数据库字段 save_time
	 * 数据保存时的时间
	 */
	public Date saveTime;

	/**
	 * 对应数据库字段 machine_number
	 * 设备编号
	 */
	private String machineNumber;

	/**
	 * 对应数据库字段 send_time
	 * 数据发送时间
	 */
	private Date sendTime;

	/**
	 * 对应数据库字段 track
	 * 股道
	 */
	private Integer track;

	/**
	 * 对应数据库字段 pressure
	 * 风压值
	 */
	private Integer pressure;

	/**
	 * 对应数据库字段 voltage
	 * 设备电压
	 */
	private Double voltage;

	/**
	 * 对应数据库字段 crc
	 * 两位十六进制字符数据效验码，前面数据取异或
	 */
	private String crc;

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

	public String getMachineNumber() {
		return machineNumber;
	}

	public void setMachineNumber(String machineNumber) {
		this.machineNumber = machineNumber;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getTrack() {
		return track;
	}

	public void setTrack(Integer track) {
		this.track = track;
	}

	public Integer getPressure() {
		return pressure;
	}

	public void setPressure(Integer pressure) {
		this.pressure = pressure;
	}

	public Double getVoltage() {
		return voltage;
	}

	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}

	public String getCrc() {
		return crc;
	}

	public void setCrc(String crc) {
		this.crc = crc;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	@Override
	public String toString() {
		return "PressureData{" +
				"id=" + id +
				", raw='" + raw + '\'' +
				", saveTime=" + saveTime +
				", machineNumber='" + machineNumber + '\'' +
				", sendTime=" + sendTime +
				", track=" + track +
				", pressure=" + pressure +
				", voltage=" + voltage +
				", crc='" + crc + '\'' +
				'}';
	}
}
