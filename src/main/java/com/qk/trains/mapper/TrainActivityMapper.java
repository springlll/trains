package com.qk.trains.mapper;

import com.qk.trains.entity.LightActivity;
import com.qk.trains.entity.TrainActivity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: trains
 * @description: 列车活动Dao
 * @author: Xiaotian
 * @create: 2018-09-07 09:46
 **/
@Repository
@Mapper
public interface TrainActivityMapper {
	/**
	 * 懒人字段
	 */
	String fields = ""
//			+ "start_time,in_signal_time, finish_time, station_id, track, train_number,"
//			+ " test_mode_id, test_type, signal_type, pressure_machine_number, peak_pressure, "
//			+ "pressure_end_time"
			+ "	id, trainActivityId, position,  operator, card_id, imei_10, in_place_time, "
			+ "in_signal_time, up_signal_time, down_signal_time, end_time,";

	/**
	 * 添加数据
	 *
	 * @param trainActivity 列车活动实体
	 */
	@Insert("INSERT train_activity(" + fields + ") " +
			"VALUES(#{trainActivity.startTime}, #{trainActivity.finishTime}, #{trainActivity.stationId}, #{trainActivity.track}, #{trainActivity.trainNumber}, #{trainActivity.testModeId}, #{trainActivity.testType}, #{trainActivity.signalType}, #{trainActivity.pressureMachineNumber}, #{trainActivity.peakPressure}, ${trainActivity.pressureEndTime})")
	@Options(useGeneratedKeys = true, keyProperty = "trainActivity.id", keyColumn = "id")
	void add(@Param("trainActivity") TrainActivity trainActivity);

	/**
	 * 创建列车活动，并返回主键
	 *
	 * @param trainActivity 列车活动实体
	 */
	@Insert("INSERT train_activity(start_time, station_id, track, test_mode_id, test_type, signal_type) VALUES(#{trainActivity.startTime}, #{trainActivity.stationId}, #{trainActivity.track}, #{trainActivity.testModeId}, #{trainActivity.testType}, #{trainActivity.signalType})")
	@Options(useGeneratedKeys = true, keyProperty = "trainActivity.id", keyColumn = "id")
	void createTrainActivity(@Param("trainActivity") TrainActivity trainActivity);

	/**
	 * 删除数据
	 *
	 * @param id id
	 */
	@Delete("DELETE FROM train_activity WHERE id=#{id}")
	void delete(@Param("id") long id);

	/**
	 * 更新数据
	 *
	 * @param trainActivity 列车活动实体
	 */
	@Update("UPDATE train_activity SET " +
			"start_time=#{trainActivity.startTime}, finish_time=#{trainActivity.finishTime}, station_id=#{trainActivity.stationId}, track=#{trainActivity.track}, train_number=#{trainActivity.trackNumber}, test_mode_id=#{trainActivity.testModeId}, test_type=#{trainActivity.testType}, signal_type=#{trainActivity.signalType}, pressure_machine_number=#{trainActivity.pressureMachineNumber}, peak_pressure=#{trainActivity.peakPressure}, pressure_end_time=#{trainActivity.pressureEndTime} " +
			"WHERE id=#{trainActivity.id}")
	void update(@Param("trainActivity") TrainActivity trainActivity);

	/**
	 * 更新试风仪机器编号
	 *
	 * @param id                    id
	 * @param pressureMachineNumber 机器编号
	 */
	@Update("UPDATE train_activity SET pressure_machine_number=#{pressureMachineNumber} WHERE id=#{id}")
	void updatePressureMachineNumber(@Param("id") long id, @Param("pressureMachineNumber") String pressureMachineNumber);

	/**
	 * 更新试风峰值
	 *
	 * @param id           id
	 * @param peakPressure 峰值
	 */
	@Update("UPDATE train_activity SET peak_pressure=#{peakPressure} WHERE id=#{id}")
	void updatePeakPressure(@Param("id") long id, @Param("peakPressure") int peakPressure);


	/**
	 * 更新试风峰值
	 *
	 * @param id           id
	 * @param pressureEndTime 峰值
	 */
	@Update("UPDATE train_activity SET pressure_end_time=#{pressureEndTime} WHERE id=#{id}")
	void updatePressureEndTime(@Param("id") long id, @Param("pressureEndTime") long pressureEndTime);

	/**
	 * 结束列车活动
	 *
	 * @param id id
	 */
	@Update("UPDATE train_activity SET finish_time=#{finishTime} WHERE id=#{id}")
	void finishTrainActivity(@Param("id") long id, @Param("finishTime") long finishTime);

	/**
	 * 查询所有数据
	 *
	 * @return 返回集合
	 */
	@Select("SELECT " + fields + " FROM light_activity")
	@ResultType(LightActivity.class)
	List<LightActivity> listAll();

	/**
	 * 查询所有
	 *
	 * @return 返回集合
	 */
	@Select("SELECT id, " + fields + " FROM train_activity WHERE finish_time=0")
	@ResultType(LightActivity.class)
	List<LightActivity> getTestingTrainActivities();
	
	
	/******分支修改******/
	/**查询当前活动ID
	 * @param stationId
	 * @param track
	 * @return
	 */
	@Select("SELECT * FROM train_activity WHERE (stationId=#{stationId} AND track=#{track}) AND activity=1 ORDER BY activity_time DESC LIMIT 1")
	@ResultType(TrainActivity.class)
	TrainActivity getTrainActivity(@Param("stationId") long stationId, @Param("track") long track);
	
	
	/**
	 * 添加数据
	 *
	 * @param trainActivity 列车活动实体
	 */
	@Insert("INSERT train_activity(stationId,track,activity_time,activity) " +
			"VALUES(#{trainActivity.stationId}, #{trainActivity.track}, #{trainActivity.activitytime}, #{trainActivity.activity})")
	@Options(useGeneratedKeys = true, keyProperty = "trainActivity.trainactivityid", keyColumn = "trainactivityid")
	void addActivity(@Param("trainActivity") TrainActivity trainActivity);

	
	/**
	 * 更新数据
	 *
	 * @param trainActivity 列车活动实体
	 */
	@Update("UPDATE train_activity SET activity=0 WHERE train_activity_id=#{trainActivityId}")
	void updateActivity(@Param("trainActivityId") Long trainActivityId);
}
