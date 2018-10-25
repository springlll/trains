package com.qk.trains.mapper;

import com.qk.trains.entity.LightActivity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @program: trains
 * @description: 信号灯活动Dao
 * @author: Xiaotian
 * @create: 2018-09-07 09:46
 **/
@Repository
@Mapper
public interface LightActivityMapper {
	/**
	 * 懒人字段
	 */
	String fields = ""
 			+ "id,train_activity_id, position,"
			+ " operator, in_place_time,up_signal_time, down_signal_time, end_time";
			//+ "	id, train_activity_id,track, position,operator, imei_10, machine_number, in_place_time, in_signal_time, up_signal_time, down_signal_time, end_time, image_files, activity=null, fos, file, lightData";

	/**
	 * 添加数据
	 *
	 * @param lightActivity 信号灯实体
	 */
	@Insert("INSERT light_activity(" + fields + ") " +
			"VALUES(#{lightActivity.trainActivityId}, #{lightActivity.direction}, #{lightActivity.cardId}, #{lightActivity.machineNumber}, #{lightActivity.inPlaceTime}, #{lightActivity.upSignalTime}, #{lightActivity.downSignalTime}, #{lightActivity.endTime}, #{lightActivity.imageFiles})")
	@Options(useGeneratedKeys = true, keyProperty = "lightActivity.id", keyColumn = "id")
	void add(@Param("lightActivity") LightActivity lightActivity);

	/**
	 * 创建信号灯活动，并返回主键
	 *
	 * @param lightActivity 信号灯实体
	 */
	@Insert("INSERT light_activity(train_activity_id, direction, card_id, machine_number) VALUES(#{lightActivity.trainActivityId}, #{lightActivity.direction}, #{lightActivity.cardId}, #{lightActivity.machineNumber})")
	@Options(useGeneratedKeys = true, keyProperty = "lightActivity.id", keyColumn = "id")
	void createLightActivity(@Param("lightActivity") LightActivity lightActivity);

	/**
	 * 删除数据
	 *
	 * @param id id
	 */
	@Delete("DELETE FROM light_activity WHERE id=#{id}")
	void delete(@Param("id") long id);

	/**
	 * 更新数据
	 *
	 * @param lightActivity 信号灯实体
	 */
	@Update("UPDATE light_activity SET " +
			"train_activity_id=#{lightActivity.trainActivityId}, direction=#{lightActivity.direction}, card_id=#{lightActivity.cardId}, machine_number=#{lightActivity.machineNumber}, in_place_time=#{lightActivity.inPlaceTime}, up_signal_time=#{lightActivity.upSignalTime}, down_signal_time=#{lightActivity.downSignalTime}, image_files=#{lightActivity.imageFiles} " +
			"WHERE id=#{lightActivity.id}")
	void update(@Param("lightActivity") LightActivity lightActivity);

	/**
	 * 更新到位时间
	 *
	 * @param trainActivityId 列车活动ID
	 * @param direction       方位
	 * @param inPlaceTime     到位时间时间戳
	 */
//	@Update("UPDATE light_activity SET in_place_time=#{inPlaceTime} WHERE train_activity_id=#{trainActivityId} AND direction=#{direction}")
//	void updateInPlace(@Param("trainActivityId") long trainActivityId, @Param("direction") int direction, @Param("inPlaceTime") long inPlaceTime);

	/**
	 * 更新上信号时间
	 *
	 * @param trainActivityId 列车活动ID
	 * @param direction       方位
	 * @param upSignalTime    上信号时间戳
	 */
//	@Update("UPDATE light_activity SET up_signal_time=#{upSignalTime} WHERE train_activity_id=#{trainActivityId} AND direction=#{direction}")
//	void updateUpSignal(@Param("trainActivityId") long trainActivityId, @Param("direction") int direction, @Param("upSignalTime") long upSignalTime);

	/**
	 * 更新下信号时间
	 *
	 * @param trainActivityId 列车活动ID
	 * @param direction       方位
	 * @param downSignalTime  下信号时间戳
	 */
//	@Update("UPDATE light_activity SET down_signal_time=#{downSignalTime} WHERE train_activity_id=#{trainActivityId} AND direction=#{direction}")
//	void updateDownSignal(@Param("trainActivityId") long trainActivityId, @Param("direction") int direction, @Param("downSignalTime") long downSignalTime);

	/**
	 * 更新结束时间
	 *
	 * @param trainActivityId 列车活动ID
	 * @param direction       方位
	 * @param endTime         结束时间戳
	 */
//	@Update("UPDATE light_activity SET end_time=#{endTime} WHERE train_activity_id=#{trainActivityId} AND direction=#{direction}")
//	void updateEnd(@Param("trainActivityId") long trainActivityId, @Param("direction") int direction, @Param("endTime") long endTime);

	/**
	 * 更新结束时间
	 *
	 * @param trainActivityId 列车活动ID
	 * @param direction       方位
	 * @param imageFiles      图片文件名
	 */
	//@Update("UPDATE light_activity SET image_files=#{imageFiles} WHERE train_activity_id=#{trainActivityId} AND direction=#{direction}")
	//void updateImageFiles(@Param("trainActivityId") long trainActivityId, @Param("direction") int direction, @Param("imageFiles") String imageFiles);

	/**
	 * 查询所有
	 *
	 * @return 返回集合
	 */
	@Select("SELECT " + fields + " FROM light_activity")
	@ResultType(LightActivity.class)
	List<LightActivity> listAll();

	/**
	 * 查询活动中
	 *
	 * @return 返回集合
	 */
//	@Select("<script><foreach item='ids' index='index' collection='trainActivityIds' separator='UNION'>" +
//			"SELECT " + fields + " FROM light_activity WHERE trainActivityId = #{ids}</foreach></script>")
//	@ResultType(LightActivity.class)
//	List<LightActivity> getAllActivity(@Param("trainActivityIds") List<Integer> trainActivityIds);
	
	/******分支修改******/
	/**
	 * 添加数据
	 *
	 * @param lightActivity 信号灯实体
	 */
	@Insert("INSERT light_activity(train_activity_id, position, operator, imei_10, in_place_time, up_signal_time, down_signal_time, end_time, image_files,activity) " +
			"VALUES(#{lightActivity.trainActivityId}, #{lightActivity.position}, #{lightActivity.operator}, #{lightActivity.imei10}, #{lightActivity.inPlaceTime}, #{lightActivity.upSignalTime}, #{lightActivity.downSignalTime}, #{lightActivity.endTime}, #{lightActivity.imageFiles}, #{lightActivity.activity})")
	void addlightActivity(@Param("lightActivity") LightActivity lightActivity);
	
	
	/**
	 * 更新到位时间
	 *
	 * @param lightActivity 信号灯实体
	 */
	@Update("UPDATE light_activity SET in_place_time=#{lightActivity.inPlaceTime} WHERE (train_activity_id=#{lightActivity.trainActivityId} AND imei_10=#{lightActivity.imei10}) AND activity=1")
	void updateInPlace(@Param("lightActivity") LightActivity lightActivity);
	/**
	 * 上信号
	 *
	 * @param lightActivity 信号灯实体
	 */
	@Update("UPDATE light_activity SET up_signal_time=#{lightActivity.upSignalTime} WHERE (train_activity_id=#{lightActivity.trainActivityId} AND imei_10=#{lightActivity.imei10}) AND activity=1")
	void updateUpSignal(@Param("lightActivity") LightActivity lightActivity);
	/**
	 * 下信号
	 *
	 * @param lightActivity 信号灯实体
	 */
	@Update("UPDATE light_activity SET down_signal_time=#{lightActivity.downSignalTime} WHERE (train_activity_id=#{lightActivity.trainActivityId} AND imei_10=#{lightActivity.imei10}) AND activity=1")
	void updateDownSignal(@Param("lightActivity") LightActivity lightActivity);
	/**
	 * 结束
	 *
	 * @param lightActivity 信号灯实体
	 */
	@Update("UPDATE light_activity SET end_time=#{lightActivity.endTime},activity=0 WHERE (train_activity_id=#{lightActivity.trainActivityId} AND imei_10=#{lightActivity.imei10}) AND activity=1")
	void updateEnd(@Param("lightActivity") LightActivity lightActivity);
	
	
	/**
	 * 更新文件
	 *
	 * @param trainActivityId 列车活动ID
	 * @param direction       方位
	 * @param imageFiles      图片文件名
	 */
	@Update("UPDATE light_activity SET image_files=#{lightActivity.imageFiles} WHERE (train_activity_id=#{lightActivity.trainActivityId} AND imei_10=#{lightActivity.imei10}) AND activity=1")
	void updateImageFiles(@Param("lightActivity") LightActivity lightActivity);
	
	/**
	 * 查询活动中
	 *
	 * @return 返回集合
	 */
	@Select("SELECT train_activity_id FROM light_activity WHERE train_activity_id = #{lightActivity.trainActivityId} AND activity=1 UNION ALL SELECT train_activity_id FROM pressure_activity WHERE train_activity_id = #{lightActivity.trainActivityId} AND activity=1")
	@ResultType(Long.class)
	List<Long> getAllActivity(@Param("lightActivity") LightActivity lightActivity);
	
	/**
	 * 查询活动中
	 *
	 * @return 返回集合
	 */
	@Select("SELECT * FROM light_activity WHERE imei_10 = #{imei10} AND activity=1 ORDER BY in_place_time DESC LIMIT 1")
	@ResultType(LightActivity.class)
	LightActivity getActivity(@Param("imei10") String imei10);
	/*
	 * 根据时间查询
	 */
	@Select("SELECT * FROM light_activity  WHERE in_place_time BETWEEN #{Startdate} AND #{Enddate} ")
	@ResultType(LightActivity.class)
	List<LightActivity> listTime(@Param("Startdate") Date startdate,@Param("Enddate")Date enddate);
	
	
	
	@Select("SELECT * FROM light_activity  WHERE in_place_time BETWEEN #{worker} ")
	@ResultType(LightActivity.class)
	List<LightActivity> listWorker(@Param("Worker") String worker);
}
