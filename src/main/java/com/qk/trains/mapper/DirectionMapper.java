package com.qk.trains.mapper;

import com.qk.trains.entity.Direction;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: trains
 * @description: 股道方位Dao
 * @author: Xiaotian
 * @create: 2018-09-05 17:29
 **/
@Repository
@Mapper
public interface DirectionMapper {
	/**
	 * 新增方位
	 *
	 * @param direction 方位对象
	 */
	@Insert("INSERT direction(direction_int, name, active) VALUES(#{direction.direction_int}, #{direction.name}, #{direction.active})")
	void add(@Param("direction") Direction direction);

	/**
	 * 删除方位
	 *
	 * @param id id
	 */
	@Delete("DELETE FROM direction WHERE id=#{id}")
	void delete(@Param("id") int id);

	/**
	 * 更新方位信息
	 *
	 * @param direction 方位对象
	 */
	@Update("UPDATE direction SET direction_int=#{direction.direction_int}, name=#{direction.name}, active={direction.active} WHERE id=#{direction.id}")
	void update(@Param("direction") Direction direction);

	/**
	 * 所有
	 *
	 * @return 列表
	 */
	@Select("SELECT id, direction_int, name, active FROM direction")
	@ResultType(Direction.class)
	List<Direction> listAll();

	/**
	 * 获取激活状态的方位列表
	 *
	 * @return 返回集合
	 */
	@Select("SELECT id, direction_int, name, active FROM direction WHERE active=1")
	@ResultType(Direction.class)
	List<Direction> listAllActive();
	
	/**
	 * 获取激活状态的方位列表
	 *
	 * @return 返回集合
	 */
	@Select("SELECT * FROM direction WHERE directionInt=#{directionInt} LIMIT 1")
	@ResultType(Direction.class)
	Direction getDirection(@Param("directionInt") Integer directionInt);
	
}
