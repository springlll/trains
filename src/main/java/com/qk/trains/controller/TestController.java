package com.qk.trains.controller;

import com.alibaba.fastjson.JSON;
import com.qk.trains.entity.Direction;
import com.qk.trains.entity.LightActivity;
import com.qk.trains.entity.LightData;
import com.qk.trains.entity.PressureData;
import com.qk.trains.entity.Track;
import com.qk.trains.entity.TrainActivity;
import com.qk.trains.entity.Worker;
import com.qk.trains.mapper.WorkerMapper;
import com.qk.trains.service.*;
import com.qk.trains.util.ContextInstances;
import com.qk.trains.vo.LightRealtimeData;
import com.qk.trains.vo.PressurePageData;
import com.qk.trains.vo.PressurePageRealtimeData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.netty.handler.codec.http.HttpRequest;

import java.beans.AppletInitializer;
import java.util.*;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: trains
 * @description: 临时测试Controller
 * @author: Xiaotian
 * @create: 2018-08-24 11:20
 **/
@RequestMapping("/admin")
@Controller
public class TestController {

	@Autowired
	SocketDataService socketDataService;

	@Autowired
	PressureDataService pressureDataService;

	@Autowired
	LightDataService lightDataService;

	@Autowired
	TrackService trackService;

	@Autowired
	DirectionService directionService;

	@Autowired
	BreakTestService breakTestService;
	@Autowired
	LightAService lightAService;
	@Autowired
	TestModeService testModeService;
	@Autowired
	WorkerService workerService;
	@Autowired
	TrainActivityService trainActivityService;

	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	
	
	
	@RequestMapping("/realtime-pressure")
	public String realtimePressure(Model model) {
		PressurePageData pressurePageData = new PressurePageData();
		List<Integer> trackNumberList = trackService.getTrackNumberList();
		pressurePageData.setTracks(trackNumberList); //股道列表
		pressurePageData.setTestTypes(Arrays.asList("全部", "简略")); //试验方式
		pressurePageData.setTestModes(testModeService.getTestModes()); //试验性质
		pressurePageData.setSignalTypes(Arrays.asList("直通", "换挂")); // 信号性质
		pressurePageData.setBreakTests(breakTestService.getBreakTests()); //各种试验
		Map<Integer, String> directions = new HashMap<>();
		List<Direction> directionActive = directionService.getDirectionList();
		for (Direction d : directionActive) {
			directions.put(d.getDirectionInt(), d.getName());
		}
		pressurePageData.setDirections(directions); //信号灯使用方位
		model.addAttribute("pressurePageData", pressurePageData);
		return "realtime-pressure";
	}

	/*@RequestMapping("/realtime-light-signal")
	public String realtimeLightSignal() {
		return "realtime-light-signal";
	}*/
/*	@RequestMapping("/data_s")
	public String LightAService(Model model ){
		List<Integer> Integer = trackService.getTrackNumberList();
		model.addAttribute(Integer);
		System.out.println("a"+Integer);
		return "data_s";	
	}*/
/*	@RequestMapping("/history-data")
	public String historyData(Model model ,HttpServletRequest request) {
		List<LightData> lightdata = lightDataService.getList();
		request.setAttribute("light", lightdata);
		model.addAttribute(lightdata);
		//System.out.println("a"+lightdata);
		return "history-data";
	}*/

	@RequestMapping("/data_s")
	public String dataTime(Model model,HttpServletRequest request){

		try{
		List<Worker> worker = workerService.getAllDevice();
			
		model.addAttribute("worker",worker);
		//System.out.println("worker"+worker);	
		}
		catch(Exception e){
			System.out.println("没找到");
		}
		List<LightActivity> lightActivity = trainActivityService.getAllLightActivity();
		request.setAttribute("lightActivity", lightActivity);
		model.addAttribute(lightActivity);
		//System.out.println(lightActivity);		
		return "data_s";
	}
	
	
	
	@RequestMapping("/data_s2")
/*	public String dataAll(Model model ,HttpServletRequest request){

			model.addAttribute(attributeValue)
		//	System.out.println("获取异常");
			return TrainActivityService.getlistTime();*/
			
			@ResponseBody
			public String getallTime(Date Startdate,Date Enddate) {
				List<LightActivity> dates = trainActivityService.getlistTime( Startdate, Enddate);
				Map map = new HashMap();
				if(map!=null&&map.size()>0){
				map.put("Startdate", dates.get(0).getInPlaceTime());
				map.put("Enddate", dates.get(0).getInPlaceTime());
				
				}else{
					System.out.println("无数据");
				}
				System.out.println(dates);
				return "data_s";
			
			}

	

	/*@RequestMapping("/data_s")
public String Worker1(Worker  worker){
	Map<String,Object> param = new HashMap<>();
	String sql="SELECT id,station_id, operator, card_id, remarks FROM worker";
    param.put("Id", worker.getId());
    param.put("Station_id", worker.getStation_id());
    param.put("Operator", worker.getOperator());
    param.put("Card_id", worker.getCard_id());
    param.put("Remarks", worker.getRemarks());

	System.out.println(worker);
	return "data_s";

}*/
	@RequestMapping("/system-setting")
	public String systemSetting() {
		return "system-setting";
	}

	@RequestMapping("/device-regist")
	public String deviceRegist() {
		return "device-regist";
	}

	@RequestMapping("/worker-management")
	public String workerManagement() {
		return "worker-management";
	}

	@RequestMapping("/user-management")
	public String userManagement() {
		return "user-management";
	}

	@RequestMapping("/auth-management")
	public String authManagement() {
		return "auth-management";
	}

	@RequestMapping("/s")
	@ResponseBody
	public String shutdown() {
		socketDataService.stopServer();
		SpringApplication.exit(ContextInstances.getInstance().getApplicationContext());
		return "SpringBoot shutdown";
	}

	/**
	 * 获取试风页面更新的数据
	 *
	 * @param track 图表显示的股道
	 * @return 返回封装实体对象PressurePageRealtimeData
	 */
	@RequestMapping("/get-realtime-pressure-data/{track}")
	@ResponseBody
	public PressurePageRealtimeData getRecentPressureData(@PathVariable("track") String track) {
		PressurePageRealtimeData realtimeData = new PressurePageRealtimeData();
		List<Integer> tracks = trackService.getTrackNumberList();
		List<long[]> pressuresAndTime = new ArrayList<>();
		//检出传入的股道数字可能出现的错误
		try {
			int trackNumber = Integer.valueOf(track);
			//将数据列表拆出细化，适应前端显示，减少数据体积
			List<PressureData> pressureData = pressureDataService.getActivityByTrack(trackNumber);
			for (PressureData pd : pressureData) {
				long[] longs = new long[2];
				longs[0] = pd.getSendTime().getTime();
				longs[1] = pd.getPressure();
				pressuresAndTime.add(longs);
			}
			if (!pressureData.isEmpty()) {
				realtimeData.setRealtimePressureData(pressureData.get(pressureData.size() - 1)); //最新一条数据
			}
		} catch (NumberFormatException e) {
			System.out.println("请求参数错误");
		}
		realtimeData.setPressuresAndTime(pressuresAndTime); //图表数据
		realtimeData.setRealtimePressureMap(pressureDataService.getAllActivityRealtimeData(tracks)); //实时值
		realtimeData.setPeakPressureMap(pressureDataService.getAllActivityPeakByTrack(tracks)); //峰值
		List<LightRealtimeData> lightRealtimeData = new ArrayList<>();
		List<Direction> directionActive = directionService.getDirectionList();
		for (Integer t : tracks) {
			for (Direction d : directionActive) {
				LightRealtimeData lrd = lightDataService.getLightRealtimeData(t, d.getDirectionInt());
				if (lrd != null) {
					lightRealtimeData.add(lrd);
				}
			}
		}
		realtimeData.setLightData(lightRealtimeData); //实时信号灯
		return realtimeData;
	}
	
	

}
