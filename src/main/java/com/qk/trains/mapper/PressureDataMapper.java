package com.qk.trains.mapper;

import com.qk.trains.entity.PressureData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: trains
 * @description: 试风数据Dao
 * @author: Xiaotian
 * @create: 2018-08-28 10:38
 **/
@Repository
@Mapper
public interface PressureDataMapper {
	/**
	 * 懒人字段
	 */
	String fields = "raw, save_time, machine_number, send_time, track, pressure, voltage, crc, data_type";

	/**
	 * 添加数据
	 *
	 * @param data 试风数据实体
	 */
	@Insert("INSERT pressure_data(" + fields + ") VALUES(" +
			"#{data.raw}, #{data.saveTime}, #{data.machineNumber}, #{data.sendTime}, #{data.track}, #{data.pressure}, #{data.voltage}, #{data.crc}, #{data.dataType})")
	void add(@Param("data") PressureData data);

	/**
	 * 根据id删除记录
	 *
	 * @param id id
	 */
	@Delete("DELETE FROM pressure_data WHERE id=#{id}")
	void delete(@Param("id") int id);

	//不提供修改
	/*public void update(@Param("data") LightData data);*/

	/**
	 * 查询所有数据
	 *
	 * @return 返回集合
	 */
	@Select("SELECT id, " + fields + " FROM pressure_data")
	@ResultType(PressureData.class)
	List<PressureData> listAll();

	/**
	 * 查询一条股道的最新一次试风数据
	 *
	 * @param track 股道编号
	 * @return 返回集合
	 */
	@Select("SELECT pressure FROM " +
			"(SELECT id, " + fields + " FROM pressure_data WHERE track=#{track} ORDER BY send_time DESC LIMIT 0, 1)temp " +
			"WHERE pressure!=30 AND voltage!=0;")
	Integer getActivityRealtimeByTrack(@Param("track") int track);

	/**
	 * 查询一条股道的最新一次试风峰值
	 * 语句请检查sql存储过程
	 *
	 * @param track 股道编号
	 * @return 返回集合
	 */
	@Select("CALL getPressureDataActivePeak(#{track})")
	Integer getActivityPeakByTrack(@Param("track") int track);

	/**
	 * 查询一条股道的正在进行的试风数据
	 * 语句请检查sql存储过程
	 *
	 * @param track 股道编号
	 * @return 返回集合
	 */
	@Select("CALL getPressureDataActive(#{track})")
	@ResultType(PressureData.class)
	List<PressureData> getActivityByTrack(@Param("track") int track);

	/**
	 * 查询一条股道的所有的试风数据
	 *
	 * @param track 股道编号
	 * @return 返回集合
	 */
	@Select("SELECT id, " + fields + " " +
			"FROM pressure_data WHERE track=#{track} ORDER BY send_time DESC")
	@ResultType(PressureData.class)
	List<PressureData> getListByTrack(@Param("track") int track);
}
