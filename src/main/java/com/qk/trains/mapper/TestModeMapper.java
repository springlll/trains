package com.qk.trains.mapper;

import com.qk.trains.entity.BreakTest;
import com.qk.trains.entity.TestMode;
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
public interface TestModeMapper {
	/**
	 * 懒人字段
	 */
	String fields = "name, break_test_ids, priority, remarks";

	/**
	 * 新增试风试验
	 *
	 * @param testMode 试风试验对象
	 */
	@Insert("INSERT test_mode(" + fields + ") VALUES(#{testMode.name}, #{testMode.breakTestIds}, #{testMode.priority}, #{testMode.remarks})")
	void add(@Param("testMode") TestMode testMode);

	/**
	 * 删除试风试验
	 *
	 * @param id id
	 */
	@Delete("DELETE FROM test_mode WHERE id=#{id}")
	void delete(@Param("id") int id);

	/**
	 * 更新试风试验
	 *
	 * @param testMode 试风试验对象
	 */
	@Update("UPDATE test_mode SET name=#{testMode.name}, break_test_ids=#{testMode.breakTestIds}, priority=#{testMode.priority}, remarks=#{testMode.remarks} WHERE id=#{testMode.id}")
	void update(@Param("testMode") TestMode testMode);

	/**
	 * 获取所有试风试验
	 *
	 * @return 列表
	 */
	@Select("SELECT id, " + fields + " FROM test_mode")
	@ResultType(TestMode.class)
	List<TestMode> listAll();

	/**
	 * 获取默认试风试验
	 *
	 * @return 列表
	 */
	@Select("SELECT id, " + fields + " FROM test_mode ORDER BY priority ASC LIMIT 1")
	@ResultType(TestMode.class)
	TestMode getDefaultTestMode();

}
