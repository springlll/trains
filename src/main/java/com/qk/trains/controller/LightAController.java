package com.qk.trains.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qk.trains.entity.Track;
import com.qk.trains.service.LightAService;
import com.qk.trains.service.LightAServiceTest;
import com.qk.trains.util.Format;

/**
 * 
 * @description 信号灯活动Controller
 * @author DK
 * @date 2018年9月29日 上午10:33:40
 */
@Controller
public class LightAController {
	@Autowired
	LightAService lightAService;
	@Autowired
	LightAServiceTest lightAServicetest;
	/**
	 * 列表显示
	 * @param model
	 * @return
	 */
	@RequestMapping("realtime-light-signal")
	public String getLightRealtimeData(Model model) {
		List<Track> list = lightAService.getAll(); 				// 查询获取所有数据
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();//集合
		
		if (list != null) {
			
			Map<String, Object> result = null;
			int temp = 0;
			
			for (int i = 0; i < list.size(); i++) {
				
				Track track = list.get(i);						//拿到循环到的当前股道
				
				int trackValue = track.getTrack();    			//当前股道值
				
				String position =  track.getPosition();			//当前方位：南头  and 北头
				//第一次循环开始
				if (temp == 0) {
					
					result = new HashMap<String, Object>();
					
					temp = trackValue; // 将股道值赋值给temp变量
					
					result.put("track", track.getTrack());
					result.put("train", track.getTrain());
					result.put("trainActivityId", track.getTrainActivityId());
					//车动报警
					result.put("acceleration", track.getAcceleration());
					
					if("南头".equals(position)){//南头
						
						lightAService.returnResult("n", track, result,"no");
					}else{//北头
						lightAService.returnResult("b", track, result,"no");
					}
					System.out.println("加入"+position+"数据");
					
					//当集合只有一条数据时，执行如下操作
					if (list.size() == 1) {
						//如果南头key值存在，就把北头赋值为null
						if(result.containsKey("position_n")){
							
							lightAService.returnResult("b", track, result,"yes");
							
						}else{//如果南头key值不存在，就把南头赋值为null
							
							lightAService.returnResult("n", track, result,"yes");
						}
						resultList.add(result);
					}

				} else if (trackValue != temp) {
					
					if(result.containsKey("position_n") && result.containsKey("position_b")) {
						resultList.add(result);
					}else if(result.containsKey("position_n") && !result.containsKey("position_b")){
						lightAService.returnResult("b", track, result,"yes");
						resultList.add(result);
					}else if(result.containsKey("position_b") && !result.containsKey("position_n")){
						lightAService.returnResult("n", track, result,"yes");
						resultList.add(result);
					}
					result = new HashMap<String, Object>();
					temp = trackValue; 							// 将股道值赋值给temp变量
					
					result.put("track", track.getTrack());
					result.put("train", track.getTrain());
					result.put("trainActivityId", track.getTrainActivityId());
					//车动报警
					result.put("acceleration", track.getAcceleration());
					if("南头".equals(position)){//南头
						lightAService.returnResult("n", track, result,"no");
					}else{//北头
						lightAService.returnResult("b", track, result,"no");
					}
				} else {
					if("南头".equals(position)){//南头
						lightAService.returnResult("n", track, result,"no");
					}else{//北头
						lightAService.returnResult("b", track, result,"no");
					}
				}
			}
			  	if(list.size() != 1 && result!=null){
			  		Track track = new Track();
			  		if(result.containsKey("position_n") && result.containsKey("position_b")) {
						resultList.add(result);
					}else if(result.containsKey("position_n") && !result.containsKey("position_b")){
						lightAService.returnResult("b", track, result,"yes");
						resultList.add(result);
					}else if(result.containsKey("position_b") && !result.containsKey("position_n")){
						lightAService.returnResult("n", track, result,"yes");
						resultList.add(result);
					}
			  	}
				
		}
		model.addAttribute("list", resultList);
		return "realtime-light-signal";
		
	}
	
	
	/**
	 * 手动保存
	 */
	@RequestMapping("light_Preservation")
	public String lightPreservation(
			@RequestParam(value="track",required=true) Integer track,
			@RequestParam(value="trainActivityId",required=true) Integer trainActivityId) {
		
		try {
			lightAService.lightPreservation(track, trainActivityId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:realtime-light-signal";
		
	}
	/**
	 * 车次保存
	 */
	@RequestMapping("train_Preservation")
	public String trainPreservation(
			@RequestParam(value="train",required=false) String train,
			@RequestParam(value="trainActivityId",required=true) Integer trainActivityId) {
		try {
			System.out.println("保存成功");
			lightAService.trainPreservation(train, trainActivityId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:realtime-light-signal";
		
	}
	@RequestMapping("refresh-light-signal")
	@ResponseBody
	public JSONArray getLightRealtime(Model model) {
		List<Track> list = lightAServicetest.getAll(); 				// 查询获取所有数据
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();//集合
		
		if (list != null) {
			
			Map<String, Object> result = null;
			int temp = 0;
			
			for (int i = 0; i < list.size(); i++) {
			
				Track track = list.get(i);						//拿到循环到的当前股道
				int trackValue = track.getTrack();    			//当前股道值
				String position =  track.getPosition();			//当前方位：南头  and 北头
				//第一次循环开始
				if (temp == 0) {
					result = new HashMap<String, Object>();
					temp = trackValue; // 将股道值赋值给temp变量
					result.put("track", track.getTrack());
					result.put("train", track.getTrain());
					result.put("trainActivityId", track.getTrainActivityId());
					
					
					//车动报警
					result.put("acceleration", track.getAcceleration());
					if("南头".equals(position)){//南头
						lightAServicetest.returnResult("n", track, result,"no");
					}else{//北头
						lightAServicetest.returnResult("b", track, result,"no");
					}
					System.out.println("加入"+position+"数据");
					
					//当集合只有一条数据时，执行如下操作
					if (list.size() == 1) {
						//如果南头key值存在，就把北头赋值为null
						if(result.containsKey("positionn")){
							
							lightAServicetest.returnResult("b", track, result,"yes");
							
						}else{//如果南头key值不存在，就把南头赋值为null
							
							lightAServicetest.returnResult("n", track, result,"yes");
						}
						resultList.add(result);
					}

				} else if (trackValue != temp) {
					
					if(result.containsKey("positionn") && result.containsKey("positionb")) {
						resultList.add(result);
					}else if(result.containsKey("positionn") && !result.containsKey("positionb")){
						lightAServicetest.returnResult("b", track, result,"yes");
						resultList.add(result);
					}else if(result.containsKey("positionb") && !result.containsKey("positionn")){
						lightAServicetest.returnResult("n", track, result,"yes");
						resultList.add(result);
					}
					result = new HashMap<String, Object>();
					temp = trackValue; 							// 将股道值赋值给temp变量
					
					result.put("track", track.getTrack());
					result.put("train", track.getTrain());
					result.put("trainActivityId", track.getTrainActivityId());
					//车动报警
					result.put("acceleration", track.getAcceleration());
					if("南头".equals(position)){//南头
						lightAServicetest.returnResult("n", track, result,"no");
					}else{//北头
						lightAServicetest.returnResult("b", track, result,"no");
					}
				} else {
					if("南头".equals(position)){//南头
						lightAServicetest.returnResult("n", track, result,"no");
					}else{//北头
						lightAServicetest.returnResult("b", track, result,"no");
					}
				}
			}
			
		
			
			
			  	if(list.size() != 1 && result!=null){
			  		Track track = new Track();
			  		if(result.containsKey("positionn") && result.containsKey("positionb")) {
						resultList.add(result);
					}else if(result.containsKey("positionn") && !result.containsKey("positionb")){
						lightAService.returnResult("b", track, result,"yes");
						resultList.add(result);
					}else if(result.containsKey("positionb") && !result.containsKey("positionn")){
						lightAService.returnResult("n", track, result,"yes");
						resultList.add(result);
					}
			  	}
				
		}
		model.addAttribute("list", resultList);
		return JSON.parseArray(JSON.toJSONString(resultList));
	}
	
}
