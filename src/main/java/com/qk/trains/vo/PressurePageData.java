package com.qk.trains.vo;

import com.qk.trains.entity.BreakTest;
import com.qk.trains.entity.TestMode;

import java.util.List;
import java.util.Map;

/**
 * @program: trains
 * @description: 试风数据页实体类
 * @author: Xiaotian
 * @create: 2018-08-30 16:53
 **/
public class PressurePageData {
	/**
	 * 传到前端的股道，已排序
	 */
	private List<Integer> tracks;

	/**
	 * 试验类型
	 * 目前仅简略与全部
	 */
	private List<String> testTypes;

	/**
	 * 试验性质
	 * 不同性质试验项目不相同
	 */
	private List<TestMode> testModes;

	/**
	 * 信号性质
	 * 不明
	 */
	private List<String> signalTypes;

	/**
	 * 试验种类集合
	 */
	private List<BreakTest> breakTests;

	/**
	 * 前台显示要用到的方位
	 * 南 北 东 西 中
	 */
	private Map<Integer, String> directions;

	public List<Integer> getTracks() {
		return tracks;
	}

	public void setTracks(List<Integer> tracks) {
		this.tracks = tracks;
	}

	public List<String> getTestTypes() {
		return testTypes;
	}

	public void setTestTypes(List<String> testTypes) {
		this.testTypes = testTypes;
	}

	public List<TestMode> getTestModes() {
		return testModes;
	}

	public void setTestModes(List<TestMode> testModes) {
		this.testModes = testModes;
	}

	public List<String> getSignalTypes() {
		return signalTypes;
	}

	public void setSignalTypes(List<String> signalTypes) {
		this.signalTypes = signalTypes;
	}

	public List<BreakTest> getBreakTests() {
		return breakTests;
	}

	public void setBreakTests(List<BreakTest> breakTests) {
		this.breakTests = breakTests;
	}

	public Map<Integer, String> getDirections() {
		return directions;
	}

	public void setDirections(Map<Integer, String> directions) {
		this.directions = directions;
	}
}
