package com.qk.trains.mapper;

import com.qk.trains.entity.Worker;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: trains
 * @description: 作业者Dao
 * @author: Xiaotian
 * @create: 2018-09-06 10:34
 **/
@Repository
@Mapper
public interface WorkerMapper {
	/**
	 * 新增作业者
	 *
	 * @param worker 作业者对象
	 */
	@Insert("INSERT worker(station_id, operator, card_id, remarks) VALUES(#{worker.stationId}, #{worker.operator}, #{worker.cardId}, #{worker.remarks})")
	void add(@Param("worker") Worker worker);

	/**
	 * 删除股道
	 *
	 * @param id id
	 */
	@Delete("DELETE FROM direction WHERE id=#{id}")
	void delete(@Param("id") int id);

	/**
	 * 更新作业者信息
	 *
	 * @param worker 作业者对象
	 */
	@Update("UPDATE worker SET station_id=#{worker.stationId}, name=#{worker.name}, card_id=#{worker.cardId}, remarks={worker.remarks} WHERE id=#{worker.id}")
	void update(@Param("worker") Worker worker);

	/**
	 * 列出所有作业者
	 *
	 * @return 返回集合
	 */
	@Select("SELECT id,station_id, operator, card_id, remarks FROM worker")
	@ResultType(Worker.class)
	
	List<Worker> listAll() ;
	
	/**
	 * 获取对应卡号的作业者
	 *
	 * @return 作业者实体
	 */
	@Select("SELECT id, station_id, operator, card_id, remarks FROM worker WHERE card_id =#{cardId} LIMIT 1")
	@ResultType(Worker.class)
	Worker getWorker(@Param("cardId") String cardId);
}
