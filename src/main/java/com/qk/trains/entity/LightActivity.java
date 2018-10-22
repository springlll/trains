package com.qk.trains.entity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.util.Date;

/**
 * @program: trains
 * @description: 信号灯活动实体
 * @author: Xiaotian
 * @create: 2018-09-06 18:42
 **/
public class LightActivity {
	/**
	 * 对应数据库字段 id
	 */
	private Long id;

	/**
	 * 对应数据库字段 train_activity_id
	 * 列车活动ID，需要先存储列车活动，才能获取到活动ID
	 */
	private Long trainActivityId;

	/**分支修改
	 * 对应数据库字段 position
	 * 方位名
	 */
	private String position;
	
	/**
	 * 对应数据库字段 direction
	 * 方位
	 */
	//private Integer direction;
	
	/**分支修改
	 * 对应数据库字段 operator
	 * 作业者名
	 */
	private String operator;

	/**
	 * 对应数据库字段 card_id
	 * 作业员卡号ID
	 */
	private String cardId;
	
	/**分支修改
	 * 对应数据库字段 imei_10
	 * 设备编号
	 */
	private String imei10;
	
	/**
	 * 对应数据库字段 machine_number
	 * 设备编号
	 */
	public String machineNumber;

	/**
	 * 对应数据库字段 inplacetime
	 * 到位时间
	 */
	private Date inPlaceTime;

	/**
	 * 对应数据库字段 up_signal_time
	 * 上信号时间
	 */
	private Date inSignalTime;
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
	 * 对应数据库字段 activity
	 * 是否活动
	 */
	private Boolean activity;
	
	/**
	 * 图片文件保存相关
	 */
	public BufferedOutputStream fos = null;
	public File file = null;
	/**
	 * 灯数据
	 */
	private LightData lightData;
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public Date getInPlaceTime() {
		return inPlaceTime;
	}
	public void setInPlaceTime(Date inPlaceTime) {
		this.inPlaceTime = inPlaceTime;
	}
	public Date getInSignalTime() {
		return inSignalTime;
	}
	public void setInSignalTime(Date inSignalTime) {
		this.inSignalTime = inSignalTime;
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
	public String getImageFiles() {
		return imageFiles;
	}
	public void setImageFiles(String imageFiles) {
		this.imageFiles = imageFiles;
	}
	public Boolean getActivity() {
		return activity;
	}
	public void setActivity(Boolean activity) {
		this.activity = activity;
	}
	public BufferedOutputStream getFos() {
		return fos;
	}
	public void setFos(BufferedOutputStream fos) {
		this.fos = fos;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public LightData getLightData() {
		return lightData;
	}
	public void setLightData(LightData lightData) {
		this.lightData = lightData;
	}
	@Override
	public String toString() {
		return "LightActivity [id=" + id + ", trainActivityId="
				+ trainActivityId + ", position=" + position + ", operator="
				+ operator + ", cardId=" + cardId + ", imei10=" + imei10
				+ ", machineNumber=" + machineNumber + ", inPlaceTime="
				+ inPlaceTime + ", inSignalTime=" + inSignalTime
				+ ", upSignalTime=" + upSignalTime + ", downSignalTime="
				+ downSignalTime + ", endTime=" + endTime + ", imageFiles="
				+ imageFiles + ", activity=" + activity + ", fos=" + fos
				+ ", file=" + file + ", lightData=" + lightData + "]";
	}
	
	
	

}
