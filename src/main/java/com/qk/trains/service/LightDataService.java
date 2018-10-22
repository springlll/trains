package com.qk.trains.service;

import com.qk.trains.entity.LightData;
import com.qk.trains.mapper.LightDataMapper;
import com.qk.trains.mapper.WorkerMapper;
import com.qk.trains.util.Format;
import com.qk.trains.vo.LightRealtimeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: trains
 * @description: 信号灯数据服务
 * @author: Xiaotian
 * @create: 2018-08-22 16:42
 **/
@Service
public class LightDataService {

	@Autowired
	LightDataMapper lightDataMapper;

	@Autowired
	WorkerMapper workerMapper;

	/**
	 * 全部数据
	 *
	 * @return 列表
	 */
	public List<LightData> getList() {
		return lightDataMapper.listAll();
	}

	/**
	 * 获取实时信号灯vo对象
	 *
	 * @param track     g股道
	 * @param direction 方位
	 * @return
	 */
	public LightRealtimeData getLightRealtimeData(int track, int direction) {
		LightData ld = (lightDataMapper.getActivityRecentByTrack(track, direction));
		if (ld == null) {
			return null;
		}
		LightRealtimeData lrd = new LightRealtimeData();
		lrd.setTrack(track);
		lrd.setDirection(direction);
		//lrd.setWorkerName(workerMapper.getWorker(ld.getCardId()).getName());
		lrd.setVoltage(ld.getVoltage());
		lrd.setLightStatus(ld.getLightSignal());
		Date in = lightDataMapper.getActivityInPlaceTime(track, direction);
		if (in == null) {
			lrd.setInPlaceTime("-");
		} else {
			lrd.setInPlaceTime(Format.date("HH:mm", in));
		}
		Date up = lightDataMapper.getActivityUpSignalTime(track, direction);
		if (up == null) {
			lrd.setUpSignalTime("-");
		} else {
			lrd.setUpSignalTime(Format.date("HH:mm", up));
		}
		Date down = lightDataMapper.getActivityDownSignalTime(track, direction);
		if (down == null) {
			lrd.setDownSignalTime("-");
		} else {
			lrd.setDownSignalTime(Format.date("HH:mm", down));
		}
		lrd.setSignalValue(ld.getSignalValue());
		if (ld.getAcceleration() > 5) {
			lrd.setTrainStatus("车动");
		} else {
			lrd.setTrainStatus("静止");
		}
		return lrd;
	}

	/*public List<LightRealtimeData> getLightRealtimeData2(List<Integer> tracks, List<Integer> directions) {
		List<LightData> data = lightDataMapper.getListRecentByTime(SystemClock.now() - 1296000);
		List<LightDataActivity> list = new ArrayList<>();
		int size = data.size();
		for (Integer t : tracks) {
			for (Integer d : directions) {
				for (int i = 0; i < size; i++) {
					List<LightData> lds = new ArrayList<>();
					for (LightData ld : data) {
						if (ld.getTrack() == t && ld.getDirection() == d) {
							lds.add(ld);
						}
					}
					LightDataActivity lda = new LightDataActivity();
					lda.setTrack(t);
					lda.setDirection(d);
					lda.setLightData(lds);
					list.add(lda);
				}
			}
		}

		return;
	}*/
}
