package com.qk.trains.service;

import com.qk.trains.entity.Track;
import com.qk.trains.mapper.TrackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: trains
 * @description: 股道服务类
 * @author: Xiaotian
 * @create: 2018-08-31 10:47
 **/
@Service
public class TrackService {
	@Autowired
	TrackMapper trackMapper;

	/**
	 * 获取排序过的股道列表
	 *
	 * @return 股道排序集合
	 */
	public List<Integer> getTrackNumberList() {
		List<Integer> trackNumber = new ArrayList<>();
		List<Track> tracks = trackMapper.listAllByPriority();
		for (Track t : tracks) {
			//trackNumber.add(t.getTrackNumber());
		}
		return trackNumber;
	}
}
