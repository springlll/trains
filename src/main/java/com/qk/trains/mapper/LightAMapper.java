package com.qk.trains.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.qk.trains.entity.Track;
/**
 * 
 * @description 信号灯活动Mapper
 * @author DK  
 * @date  2018年9月29日 上午10:27:35
 */

@Repository
@Mapper
public interface LightAMapper {
	/**
	 * 灯查询
	 */

	@Select("select a.train_activity_id,a.track as track,a.train as train,b.position as position,a.train_activity_id as trainActivityId,b.operator as operator,\r\n" + 
			"			b.in_place_time as inPlaceTime,b.up_signal_time as upSignalTime,b.down_signal_time as downSignalTime,b.activity as bactivity,\r\n" + 
			"			b.end_time as endTime,c.signal_value as signalValue,MAX(c.save_time) as saveTime,(select light_signal from light_data c where c.imei_10=b.imei_10 order by save_time desc  limit  1) as lightSignal,\r\n" + 
			"			(select acceleration from light_data c where c.imei_10=b.imei_10 order by save_time desc  limit  1) as acceleration,b.image_files as imageFiles\r\n" + 
			"			FROM (SELECT * FROM train_activity where activity = 1) a\r\n" + 
			"			inner JOIN light_activity b\r\n" + 
			"			ON a.train_activity_id=b.train_activity_id\r\n" + 
			"			inner join light_data c \r\n" + 
			"			on b.imei_10 = c.imei_10 \r\n" + 
			"			where b.activity=1\r\n" + 
			"			GROUP BY a.track,b.imei_10\r\n" + 
			"			order by track asc")
	List<Track> getAllRealtime();
	/**
	 * 信号灯保存
	 */
	@Update("UPDATE train_activity ta,light_activity la SET ta.activity = 0,la.activity=0 where track=#{track} and ta.train_activity_id=#{trainActivityId} and la.train_activity_id=#{trainActivityId}")
	Integer lightPreservation(@Param("track")Integer track,@Param("trainActivityId")Integer trainActivityId);
	/**
	 * 车次保存
	 */
	@Update("update train_activity set train=#{train} where train_activity_id=#{trainActivityId}")
	Integer trainPreservation(@Param("train")String train,@Param("trainActivityId")Integer trainActivityId);
	
}
