package com.qk.trains.mapper;

import com.qk.trains.entity.LightData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @program: trains
 * @description: 信号灯数据Dao
 * @author: Xiaotian
 * @create: 2018-08-20 10:08
 **/
@Repository
@Mapper
public interface LightDataMapper {
	/**
	 * 懒人字段
	 */
	String fields = "raw, save_time, track, imei_10, send_time, direction, card_id, light_signal, voltage, signal_value, acceleration, machine_number, data_type";

	/**
	 * 添加数据
	 *
	 * @param data 信号灯数据实体
	 */
	@Insert("INSERT light_data(" + fields + ") " +
			"VALUES(#{data.raw}, #{data.saveTime}, #{data.track}, #{data.imei10}, #{data.sendTime}, #{data.direction}, #{data.cardId}, #{data.lightSignal}, #{data.voltage}, #{data.signalValue}, #{data.acceleration}, #{data.machineNumber}, #{data.dataType})")
	void add(@Param("data") LightData data);

	/**
	 * 根据id删除记录
	 *
	 * @param id id
	 */
	@Delete("DELETE FROM light_data WHERE id=#{id}")
	void delete(@Param("id") int id);

	//不提供修改
	/*public void update(@Param("data") LightData data);*/

	/**
	 * 查询所有数据
	 *
	 * @return 返回集合
	 */
	@Select("SELECT id, " + fields + " " +"FROM light_data")
	@ResultType(LightData.class)
	List<LightData> listAll();

	/**
	 * 查询一条股道中某个方位的最新信号灯数据
	 *
	 * @param track     股道编号
	 * @param direction 方位
	 * @return 返回集合
	 */
	@Select("SELECT id, " + fields + " " +
			"FROM light_data WHERE track=#{track} ORDER BY send_time DESC LIMIT 0, 1")
	@ResultType(LightData.class)
	LightData getOneRecentByTrack(@Param("track") int track, @Param("direction") int direction);

	/**
	 * 查询一条股道中某个方位的实时信号灯数据
	 * 不在运行则返回空
	 *
	 * @param track     股道编号
	 * @param direction 方位
	 * @return 返回集合
	 */
	@Select("CALL getLightDataActiveRecent(${track}, #{direction})")
	@ResultType(LightData.class)
	LightData getActivityRecentByTrack(@Param("track") int track, @Param("direction") int direction);

	/**
	 * 查询一条股道中某个方位的最新到位数据
	 * 语句请检查sql存储过程
	 *
	 * @param track     股道编号
	 * @param direction 方位
	 * @return 返回集合
	 */
	@Select("CALL getLightDataActiveInPlaceTime(#{track}, #{direction})")
	Date getActivityInPlaceTime(@Param("track") int track, @Param("direction") int direction);

	/**
	 * 查询一条股道中某个方位的最新到位数据
	 * 语句请检查sql存储过程
	 *
	 * @param track     股道编号
	 * @param direction 方位
	 * @return 返回集合
	 */
	@Select("CALL getLightDataActiveUpSignalTime(#{track}, #{direction})")
	Date getActivityUpSignalTime(@Param("track") int track, @Param("direction") int direction);

	/**
	 * 查询一条股道中某个方位的最新到位数据
	 * 语句请检查sql存储过程
	 *
	 * @param track     股道编号
	 * @param direction 方位
	 * @return 返回集合
	 */
	@Select("CALL getLightDataActiveDownSignalTime(#{track}, #{direction})")
	Date getActivityDownSignalTime(@Param("track") int track, @Param("direction") int direction);

	/**
	 * 查询一条股道的最近的信号灯数据  not used yet
	 *
	 * @param track 股道编号
	 * @return 返回集合
	 */
	@Select("SELECT id, " + fields + " " +
			"FROM light_data WHERE track=#{track} ORDER BY send_time DESC")
	@ResultType(LightData.class)
	List<LightData> getListRecentByTrack(@Param("track") int track);

	/**
	 * 查询某个操作员操作过的信号灯的数据 not used yet
	 *
	 * @param cardId 作业者卡号
	 * @return 返回集合
	 */
	@Select("SELECT id, " + fields + " " +
			"FROM light_data WHERE card_id=#{cardId} ORDER BY send_time DESC")
	@ResultType(LightData.class)
	List<LightData> getListRecentByCardId(@Param("cardId") String cardId);

	/**
	 * 查询目标时间到现在的所有信号灯数据  not used yet
	 *
	 * @param time 时间戳
	 * @return 返回集合
	 */
	@Select("SELECT id, " + fields + " " +
			"FROM light_data WHERE save_time > ${time} ORDER BY save_time DESC")
	@ResultType(LightData.class)
	List<LightData> getListRecentByTime(@Param("time") long time);
}
