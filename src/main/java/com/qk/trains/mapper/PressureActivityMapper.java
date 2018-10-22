package com.qk.trains.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qk.trains.entity.LightActivity;
import com.qk.trains.entity.PressureActivity;

public interface PressureActivityMapper {

	/**
	 * 查询活动中
	 *
	 * @return 返回集合
	 */
	@Select("SELECT * FROM pressure_activity WHERE machine_number = #{machineNumber} AND activity=1 ORDER BY in_place_time DESC LIMIT 1")
	@ResultType(LightActivity.class)
	PressureActivity getActivity(@Param("machineNumber")String machineNumber);

	/**
	 * 添加数据
	 *
	 * @param pressureAct 试风实体
	 */
	@Insert("INSERT pressure_activity(train_activity_id,machine_number,in_place_time, end_time,activity) " +
			"VALUES(#{pressureAct.trainActivityId}, #{pressureAct.machineNumber}, #{pressureAct.inPlaceTime}, #{pressureAct.endTime}, #{pressureAct.activity})")
	void addpressureActivity(@Param("pressureAct")PressureActivity pressureAct);
	/**
	 * 结束
	 *
	 * @param pressureAct 试风实体
	 */
	@Update("UPDATE pressure_activity SET end_time=#{pressureAct.endTime},activity=0 WHERE (train_activity_id=#{pressureAct.trainActivityId} AND machine_number=#{pressureAct.machineNumber}) AND activity=1")
	void updateEnd(@Param("pressureAct")PressureActivity pressureAct);
	
	/**
	 * 结束
	 *
	 * @param pressureAct 试风实体
	 */
	@Update("UPDATE pressure_activity SET peak_pressure=#{pressureAct.peakPressure} WHERE (train_activity_id=#{pressureAct.trainActivityId} AND machine_number=#{pressureAct.machineNumber}) AND activity=1")
	void updatepeakPressure(@Param("pressureAct")PressureActivity pressureAct);

	/**
	 * 查询活动中
	 *
	 * @return 返回集合
	 */
	@Select("SELECT train_activity_id FROM light_activity WHERE train_activity_id = #{pressureAct.trainActivityId} AND activity=1 UNION ALL SELECT train_activity_id FROM pressure_activity WHERE train_activity_id = #{pressureAct.trainActivityId} AND activity=1")
	@ResultType(Long.class)
	List<Long> getAllActivity(@Param("pressureAct")PressureActivity pressureAct);
}
