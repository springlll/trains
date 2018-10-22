package com.qk.trains.service;

import com.qk.trains.entity.PressureData;
import com.qk.trains.mapper.PressureDataMapper;
import com.qk.trains.util.SystemClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: trains
 * @description: 试风压力数据服务
 * @author: Xiaotian
 * @create: 2018-08-29 18:39
 **/
@Service
public class PressureDataService {

	@Autowired
	PressureDataMapper pressureDataMapper;

	/**
	 * 获取一条股道的正在进行的试风数据列
	 *
	 * @param track 股道
	 * @return 数据列表
	 */
	public List<PressureData> getActivityByTrack(int track) {
		return pressureDataMapper.getActivityByTrack(track);
	}

	/**
	 * 获取多条股道的正在进行的试风数据的最新值
	 *
	 * @param tracks 股道数组
	 * @return 返回Map，key为股道，value为数据值
	 */
	public Map<Integer, Integer> getAllActivityRealtimeData(List<Integer> tracks) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int t : tracks) {
			Integer p = pressureDataMapper.getActivityRealtimeByTrack(t);
			if (p != null) {
				map.put(t, p);
			}
		}
		return map;
	}

	/**
	 * 获取多条股道的正在进行的试风数据的最高峰值
	 *
	 * @param tracks 股道数组
	 * @return 返回Map，key为股道，value为数据值
	 */
	public Map<Integer, Integer> getAllActivityPeakByTrack(List<Integer> tracks) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int t : tracks) {
			Integer p = pressureDataMapper.getActivityPeakByTrack(t);
			if (p != null) {
				map.put(t, p);
			}
		}
		return map;
	}

	/**
	 * 获取该股道的所有数据   （暂时没用）
	 *
	 * @param track 股道
	 * @return 数据列表
	 */
	public List<PressureData> getListByTrack(int track) {
		return pressureDataMapper.getListByTrack(track);
	}
}
