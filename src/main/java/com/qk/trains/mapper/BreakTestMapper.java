package com.qk.trains.mapper;

import com.qk.trains.entity.BreakTest;
import com.qk.trains.entity.Direction;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: trains
 * @description: 试风试验Dao
 * @author: Xiaotian
 * @create: 2018-09-15 18:08
 **/
@Repository
@Mapper
public interface BreakTestMapper {
	/**
	 * 懒人字段
	 */
	String fields = "name, min, max, test_time, status, fill_color";

	/**
	 * 新增试风试验
	 *
	 * @param breakTest 试风试验对象
	 */
	@Insert("INSERT break_test(" + fields + ") VALUES(#{breakTest.name}, #{breakTest.min}, #{breakTest.max}, #{breakTest.testTime}, #{breakTest.status}, #{breakTest.fillColor})")
	void add(@Param("breakTest") BreakTest breakTest);

	/**
	 * 删除试风试验
	 *
	 * @param id id
	 */
	@Delete("DELETE FROM break_test WHERE id=#{id}")
	void delete(@Param("id") int id);

	/**
	 * 更新试风试验
	 *
	 * @param breakTest 试风试验对象
	 */
	@Update("UPDATE break_test SET name=#{breakTest.name}, min=#{breakTest.min}, max=#{breakTest.max}, test_time=#{breakTest.testTime}, status=#{breakTest.status}, fill_color=#{breakTest.fillColor} WHERE id=#{breakTest.id}")
	void update(@Param("breakTest") BreakTest breakTest);

	/**
	 * 获取所有试风试验
	 *
	 * @return 列表
	 */
	@Select("SELECT id, " + fields + " FROM break_test")
	@ResultType(BreakTest.class)
	List<BreakTest> listAll();

}
