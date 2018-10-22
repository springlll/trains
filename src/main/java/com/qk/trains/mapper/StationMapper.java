package com.qk.trains.mapper;

import com.qk.trains.entity.Station;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: trains
 * @description: 站点Dao
 * @author: Xiaotian
 * @create: 2018-09-15 18:08
 **/
@Repository
@Mapper
public interface StationMapper {

	/**
	 * 新增试风试验
	 *
	 * @param station 试风试验对象
	 */
	@Insert("INSERT station(name, remarks) VALUES(#{station.name}, #{station.remarks})")
	void add(@Param("station") Station station);

	/**
	 * 删除试风试验
	 *
	 * @param id id
	 */
	@Delete("DELETE FROM station WHERE id=#{id}")
	void delete(@Param("id") int id);

	/**
	 * 更新试风试验
	 *
	 * @param station 试风试验对象
	 */
	@Update("UPDATE station SET name=#{station.name}, min=#{station.remarks} WHERE id=#{station.id}")
	void update(@Param("station") Station station);

	/**
	 * 获取所有试风试验
	 *
	 * @return 列表
	 */
	@Select("SELECT id, name, remarks FROM station")
	@ResultType(Station.class)
	List<Station> listAll();

}
