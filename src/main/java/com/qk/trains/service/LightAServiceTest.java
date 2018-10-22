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
import com.qk.trains.util.Format;
import com.qk.trains.vo.LightRealtimeData;
@Service
public class LightAServiceTest {
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
		
		
		
		/*result.put("inPlaceTimen",Format.dateFormat(list.get(0).getInPlaceTime(),"HH:mm:ss"));
		result.put("inPlaceTimen",Format.dateFormat(list.get(0).getUpSignalTime(),"HH:mm:ss"));
		result.put("inPlaceTimen",Format.dateFormat(list.get(0).getDownSignalTime(),"HH:mm:ss"));
		result.put("inPlaceTimen",Format.dateFormat(list.get(0).getSaveTime(),"HH:mm:ss"));
		
		result.put("inPlaceTimeb",Format.dateFormat(list.get(0).getInPlaceTime(),"HH:mm:ss"));
		result.put("inPlaceTimeb",Format.dateFormat(list.get(0).getUpSignalTime(),"HH:mm:ss"));
		result.put("inPlaceTimeb",Format.dateFormat(list.get(0).getDownSignalTime(),"HH:mm:ss"));
		result.put("inPlaceTimeb",Format.dateFormat(list.get(0).getSaveTime(),"HH:mm:ss"));
		*/
		
		
		
		if(flag!=null && ""!=flag && strNull.equals("no")){//flag等于n就是南头赋值，等于b就是北头赋值
			result.put("position"+flag, track.getPosition());
			result.put("operator"+flag, track.getOperator());
			result.put("inPlaceTime"+flag, Format.dateFormat(track.getInPlaceTime(),"HH:mm:ss"));
			result.put("upSignalTime"+flag, Format.dateFormat(track.getUpSignalTime(),"HH:mm:ss"));
			result.put("downSignalTime"+flag, Format.dateFormat(track.getDownSignalTime(),"HH:mm:ss"));
			result.put("endTime"+flag, Format.dateFormat(track.getEndTime(),"HH:mm:ss"));
			result.put("signalValue"+flag, track.getSignalValue());
			//刷新时间
			result.put("saveTime"+flag,Format.dateFormat(track.getSaveTime(),"yyyy-MM-dd HH:mm:ss"));
			//灯状态
			result.put("lightSignal"+flag, track.getLightSignal());
			//图片显示
			result.put("imageFiles"+flag, track.getImageFiles());
		}
		if(strNull.equals("yes")){//flag等于n就是南头赋值为null，等于b就是北头赋值为null
			result.put("position"+flag, "无");
			result.put("operator"+flag, "无");
			result.put("inPlaceTime"+flag,"无");
			result.put("upSignalTime"+flag,"无");
			result.put("downSignalTime"+flag,"无");
			result.put("endTime"+flag,"无");
			result.put("signalValue"+flag,"无");
			//刷新时间
			result.put("saveTime"+flag,"无");
			//灯状态
			result.put("lightSignal"+flag, "无");
			//图片显示
			result.put("imageFiles"+flag, "无");
			
		
		}
		return result;
		
	}
}
