package com.qk.trains.service;

import com.qk.trains.entity.*;
import com.qk.trains.mapper.*;
import com.qk.trains.util.StaticStatus;
import com.qk.trains.util.SystemClock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: trains
 * @description: 列车活动服务
 * @author: Xiaotian
 * @create: 2018-09-07 11:46
 **/
@Service
public class TrainActivityService {

	@Autowired
	StationService stationService;

	@Autowired
	DeviceService deviceService;

	@Autowired
	WorkerService workerService;

	@Autowired
	DirectionService directionService;

	@Autowired
	TestModeService testModeService;

	@Autowired
	TrackMapper trackMapper;

	@Autowired
	TrainActivityMapper trainActivityMapper;

	@Autowired
	LightActivityMapper lightActivityMapper;

	private  List<TrainActivity> cacheTrainActivities = new ArrayList<>();

	/**
	 * 返回所有列车活动
	 *
	 * @return 列表
	 */
	public List<TrainActivity> getCacheTrainActivities() {
		return cacheTrainActivities;
	}
	@Cacheable(cacheNames = "light_activity")
	public    List<LightActivity> getAllLightActivity() {
		return lightActivityMapper.listAll();
		
	}	
	
	/**
	 * 根据时间查询
	 *
	 * @return 列表
	 */	
	@Cacheable(cacheNames = "light_activity")
	public    List<LightActivity> getlistTime(Date Startdate,Date Enddate) {
		return lightActivityMapper.listTime(Startdate,Enddate);
		
	}		
	/**
	 * 更新信号灯数据
	 *
	 * @param lightData 信号灯数据对象
	 */
	public void updateLight(LightData lightData) {
		//检查传参
		if (lightData == null) {
			return;
		}
		if (lightData.getVoltage() == StaticStatus.CHECK_TIME_VOLTAGE) {
			return;
		}
		//站点判定
		int stationId = deviceService.getStationIdByMachineNumber(lightData.getMachineNumber());
		TrainActivity ta = getTrainActivity(stationId, lightData.getTrack());
		if (ta != null) {
			LightActivity la = getLightActivity(ta.getId(), lightData.getDirection(), lightData.getMachineNumber());
			if (la == null) {
				la = buildLightActivity(ta.getId(), lightData);
				ta.getLightActivities().add(la);
			}
			double voltage = lightData.getVoltage();
			/*long saveTime = lightData.getSaveTime();
			if (voltage == StaticStatus.IN_PLACE_VOLTAGE) {
				la.setInPlaceTime(saveTime);
				lightActivityMapper.updateInPlace(la.getTrainActivityId(), la.getDirection(), saveTime);
			} else if (voltage == StaticStatus.UP_SIGNAL_VOLTAGE) {
				la.setUpSignalTime(saveTime);
				lightActivityMapper.updateUpSignal(la.getTrainActivityId(), la.getDirection(), saveTime);
			} else if (voltage == StaticStatus.DOWN_SIGNAL_VOLTAGE) {
				la.setDownSignalTime(saveTime);
				lightActivityMapper.updateDownSignal(la.getTrainActivityId(), la.getDirection(), saveTime);
			} else if (voltage == StaticStatus.END_VOLTAGE) {
				la.setEndTime(saveTime);
				lightActivityMapper.updateEnd(la.getTrainActivityId(), la.getDirection(), saveTime);
			}*/
			ta.setLastUpdateTime(SystemClock.now());
			checkTrainActivityEnd(ta);
		} else {
			//新建活动
			ta = buildTrainActivity(stationId, lightData.getTrack());
			cacheTrainActivities.add(ta);
			updateLight(lightData);
		}
	}

	/**
	 * 更新图片信息
	 *
	 * @param machineNumber 机器码
	 * @param track         股道
	 * @param direction     方位
	 * @param imageFile     文件名
	 */
	public void updateLightImage(String machineNumber, int track, int direction, String imageFile) {
		int stationId = deviceService.getStationIdByMachineNumber(machineNumber);
		LightActivity la = getLightActivity(getTrainActivity(stationId, track).getId(), direction, machineNumber);
		if (la != null) {
			StringBuilder files = new StringBuilder(la.getImageFiles());
			if (files.length() > 0) {
				files.append(",");
			}
			files.append(imageFile);
			la.setImageFiles(files.toString());
			//lightActivityMapper.updateImageFiles(la.getTrainActivityId(), direction, la.getImageFiles());
		}
	}

	/**
	 * 更新试风压力数据
	 *
	 * @param pressureData 试风数据对象
	 */
	public void updatePressure(PressureData pressureData) {
		//检查传参
		if (pressureData == null) {
			return;
		}
		//站点判定
		int stationId = deviceService.getStationIdByMachineNumber(pressureData.getMachineNumber());
		TrainActivity ta = getTrainActivity(stationId, pressureData.getTrack());
		if (ta != null) {
			int pressure = pressureData.getPressure();
			Map<Long, Integer> map = ta.getRealtimePressure();
			//判断试风结束
			if (pressureData.getVoltage() == StaticStatus.PRESSURE_END_VOLTAGE && pressure == StaticStatus.PRESSURE_END_SYMBOL) {
				//ta.setPressureEndTime(pressureData.getSaveTime());
				//trainActivityMapper.updatePressureEndTime(ta.getId(), pressureData.getSaveTime());
			} else {
				//更新缓存中的实时数据表
				if (map == null) {
					map = new HashMap<>();
					ta.setRealtimePressure(map);
				}
				//map.put(pressureData.getSaveTime(), pressure);
				Integer peak = ta.getPeakPressure();
				if ((peak == null ? 0 : peak) < pressure) {
					ta.setPeakPressure(pressure);
					trainActivityMapper.updatePeakPressure(ta.getId(), pressure);
				}
			}
			ta.setLastUpdateTime(SystemClock.now());
			checkTrainActivityEnd(ta);
		} else {
			//新建活动
			ta = buildTrainActivity(stationId, pressureData.getTrack());
			trainActivityMapper.updatePressureMachineNumber(ta.getId(), pressureData.getMachineNumber());
			cacheTrainActivities.add(ta);
			updatePressure(pressureData);
		}
	}

	/**
	 * 更新设置
	 *
	 * @param track      股道
	 * @param testModeId 试风性质ID
	 * @param testType   试风方式数字标志
	 * @param signalType 信号灯性质数字标志
	 */
	public void updateSetting(int stationId, int track, String trainNumber, Integer testModeId, Integer testType, Integer signalType) {
		TrainActivity ta = getTrainActivity(stationId, track);
		if (ta != null) {
			if (trainNumber != null && !"".equals(trainNumber)) {
				ta.setTrainNumber(trainNumber);
			}
			if (testModeId != null) {
				ta.setTestModeId(testModeId);
			}
			if (testType != null) {
				ta.setTestType(testType);
			}
			if (signalType != null) {
				ta.setSignalType(signalType);
			}
			trainActivityMapper.update(ta);
		}
	}

	/**
	 * 检查活动是否结束
	 *
	 * @param trainActivity
	 */
	public boolean checkTrainActivityEnd(TrainActivity trainActivity) {
		//检查传参
		if (trainActivity == null) {
			return false;
		}
		int signalType = trainActivity.getSignalType();
		List<Direction> directions = directionService.getDirectionList();
		List<LightActivity> lightActivities = trainActivity.getLightActivities();
		if (signalType == StaticStatus.SIGNAL_TYPE_THROUGH) {
			int directionFinishFlag = directions.size();
			for (LightActivity la : lightActivities) {
				for (Direction d : directions) {
					if (la.getDirection().equals(d.getDirectionInt()) && la.getEndTime() != null) {
						directionFinishFlag--;
					}
				}
			}
			if (directionFinishFlag <= 0 && trainActivity.getPressureEndTime() != null) {
				finishTrainActivity(trainActivity);
			}
		} else if (signalType == StaticStatus.SIGNAL_TYPE_CHANGE) {
			for (LightActivity la : lightActivities) {
				if (la.getEndTime() != null && trainActivity.getPressureEndTime() != null) {
					finishTrainActivity(trainActivity);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 定时检查超时
	 */
	@Scheduled(fixedRate = 30000)
	public void checkTimeOutActivity() {
		for (TrainActivity ta : cacheTrainActivities) {
			if (SystemClock.now() - ta.getLastUpdateTime() > 5 * 60 * 1000) {
				finishTrainActivity(ta);
			}
			System.out.println(ta);
		}
	}

	/**
	 * 创建新的列车活动
	 *
	 * @param track 股道
	 * @return 列车活动对象
	 */
	public TrainActivity buildTrainActivity(int stationId, int track) {
		TrainActivity ta = new TrainActivity();
		ta.setStationId(stationId);
		ta.setTrack(track);
		ta.setStartTime(SystemClock.now());
		ta.setLightActivities(new ArrayList<>());
		ta.setTestModeId(testModeService.getDefaultTestMode().getId());
		ta.setTestType(StaticStatus.TEST_TYPE_SIMPLE);
		ta.setSignalType(StaticStatus.SIGNAL_TYPE_CHANGE);
		trainActivityMapper.createTrainActivity(ta);
		System.out.println("Create TrainActivity: " + ta.getId());
		return ta;
	}

	/**
	 * 创建新的信号灯活动
	 *
	 * @param ld 信号灯数据
	 * @return 信号灯活动对象
	 */
	public LightActivity buildLightActivity(long trainActivityId, LightData ld) {
		LightActivity la = new LightActivity();
		la.setTrainActivityId(trainActivityId);
		la.setDirection(ld.getDirection());
		la.setCardId(ld.getCardId());
		la.setMachineNumber(ld.getMachineNumber());
		la.setImageFiles("");
		lightActivityMapper.createLightActivity(la);
		System.out.println("Create LightActivity: " + la.getId());
		return la;
	}

	public void finishTrainActivity(TrainActivity trainActivity) {
		//空值填补
		if (trainActivity.getPressureEndTime() == null) {
			trainActivity.setPressureEndTime(SystemClock.now());
		}
		trainActivity.setFinishTime(SystemClock.now());
		trainActivityMapper.finishTrainActivity(trainActivity.getId(), trainActivity.getFinishTime());
		for (LightActivity la : trainActivity.getLightActivities()) {
			/*if (la.getInPlaceTime() == null) {
				la.setInPlaceTime(SystemClock.now());
			}
			if (la.getUpSignalTime() == null) {
				la.setUpSignalTime(SystemClock.now());
			}
			if (la.getDownSignalTime() == null) {
				la.setDownSignalTime(SystemClock.now());
			}
			if (la.getEndTime() == null) {
				la.setEndTime(SystemClock.now());
			}*/
			lightActivityMapper.update(la);
		}
		cacheTrainActivities.remove(trainActivity);
	}

	/**
	 * 根据股道获取列车活动
	 *
	 * @param track 股道
	 * @return 列车活动对象
	 */
	public TrainActivity getTrainActivity(int stationId, int track) {
		for (TrainActivity ta : cacheTrainActivities) {
			if (ta.getTrack() == track && stationId == ta.getStationId()) {
				return ta;
			}
		}
		return null;
	}

	/**
	 * 根据活动ID获取列车活动
	 *
	 * @param trainActivityId 列车活动ID
	 * @return 列车活动对象
	 */
	public TrainActivity getTrainActivity(long trainActivityId) {
		for (TrainActivity ta : cacheTrainActivities) {
			if (ta.getId() == trainActivityId) {
				return ta;
			}
		}
		return null;
	}

	/**
	 * 根据列车活动和方位获取信号灯活动
	 *
	 * @param trainActivityId 列车活动ID
	 * @param direction       方位
	 * @param machineNumber   机器编号
	 * @return 信号灯活动对象
	 */
	public LightActivity getLightActivity(long trainActivityId, int direction, String machineNumber) {
		List<LightActivity> las = getTrainActivity(trainActivityId).getLightActivities();
		for (LightActivity la : las) {
			if (la.getDirection() == direction && la.getMachineNumber().equals(machineNumber)) {
				return la;
			}
		}
		return null;
	}

}
