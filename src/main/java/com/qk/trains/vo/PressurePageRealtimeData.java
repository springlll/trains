package com.qk.trains.vo;

import com.qk.trains.entity.PressureData;

import java.util.List;
import java.util.Map;

/**
 * @program: trains
 * @description: 试风数据页实时数据实体类
 * @author: Xiaotian
 * @create: 2018-08-30 16:53
 **/
public class PressurePageRealtimeData {
	/**
	 * 各股道实时试风数据
	 */
	private Map<Integer, Integer> realtimePressureMap;

	/**
	 * 各股道试风峰值
	 */
	private Map<Integer, Integer> peakPressureMap;

	/**
	 * 指定股道最新一条数据
	 */
	private PressureData realtimePressureData;

	/**
	 * 试风图表数据中风压数据与时间轴
	 */
	private List<long[]> pressuresAndTime;

	/**
	 * 信号灯最新数据
	 */
	private List<LightRealtimeData> lightData;

	public Map<Integer, Integer> getRealtimePressureMap() {
		return realtimePressureMap;
	}

	public void setRealtimePressureMap(Map<Integer, Integer> realtimePressureMap) {
		this.realtimePressureMap = realtimePressureMap;
	}

	public Map<Integer, Integer> getPeakPressureMap() {
		return peakPressureMap;
	}

	public void setPeakPressureMap(Map<Integer, Integer> peakPressureMap) {
		this.peakPressureMap = peakPressureMap;
	}

	public PressureData getRealtimePressureData() {
		return realtimePressureData;
	}

	public void setRealtimePressureData(PressureData realtimePressureData) {
		this.realtimePressureData = realtimePressureData;
	}

	public List<long[]> getPressuresAndTime() {
		return pressuresAndTime;
	}

	public void setPressuresAndTime(List<long[]> pressuresAndTime) {
		this.pressuresAndTime = pressuresAndTime;
	}

	public List<LightRealtimeData> getLightData() {
		return lightData;
	}

	public void setLightData(List<LightRealtimeData> lightData) {
		this.lightData = lightData;
	}
}
