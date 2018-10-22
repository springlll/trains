package com.qk.trains.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
/**
 * 
 * @description 信号灯活动Service
 * @author DK  
 * @date  2018年9月29日 上午10:32:22
 */

import com.qk.trains.entity.Track;
import com.qk.trains.mapper.LightAMapper;
import com.qk.trains.vo.LightRealtimeData;
@Service
public class LightAService {
	@Resource
	LightAMapper lightAMapper; 
	
	/**
	 * 查询活动股道的灯
	 * @return
	 */
	public List<Track> getAll(){
		return lightAMapper.getAllRealtime();
		
	}
	
	/**
	 * 灯保存
	 */
	public Integer lightPreservation(Integer track,Integer trainActivityId) {
		return lightAMapper.lightPreservation(track,trainActivityId);
		
	}
	/**
	 * 车次保存
	 */
	public Integer trainPreservation(String train,Integer trainActivityId) {
		return lightAMapper.trainPreservation(train, trainActivityId);
	}
	/**
	 * 公共方法：南头北头result赋值
	 * @param flag:n-南头,b-北头     track：对象  result:Map strNull：是否赋值为null（no-否,yes-是 ）
	 * @return
	 */
	public Map<String, Object> returnResult(String flag,Track track,Map<String, Object> result,String strNull){
		
		if(flag!=null && ""!=flag && strNull.equals("no")){//flag等于n就是南头赋值，等于b就是北头赋值
			result.put("position_"+flag, track.getPosition());
			result.put("operator_"+flag, track.getOperator());
			result.put("inPlaceTime_"+flag, track.getInPlaceTime());
			result.put("upSignalTime_"+flag, track.getUpSignalTime());
			result.put("downSignalTime_"+flag, track.getDownSignalTime());
			result.put("endTime_"+flag, track.getEndTime());
			result.put("signalValue_"+flag, track.getSignalValue());
			//刷新时间
			result.put("saveTime_"+flag, track.getSaveTime());
			//灯状态
			result.put("lightSignal_"+flag, track.getLightSignal());
			//图片显示
			result.put("imageFiles_"+flag, track.getImageFiles());
		}
		if(strNull.equals("yes")){//flag等于n就是南头赋值为null，等于b就是北头赋值为null
			result.put("position_"+flag, null);
			result.put("operator_"+flag, null);
			result.put("inPlaceTime_"+flag,null);
			result.put("upSignalTime_"+flag,null);
			result.put("downSignalTime_"+flag,null);
			result.put("endTime_"+flag,null);
			result.put("signalValue_"+flag,null);
			//刷新时间
			result.put("saveTime_"+flag,null);
			//灯状态
			result.put("lightSignal_"+flag, null);
			//图片显示
			result.put("imageFiles_"+flag, null);
		}
		return result;
		
	}
}
