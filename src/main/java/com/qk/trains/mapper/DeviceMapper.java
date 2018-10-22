package com.qk.trains.mapper;

import com.qk.trains.entity.Device;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: trains
 * @description: 设备Dao
 * @author: Xiaotian
 * @create: 2018-09-15 18:08
 **/
@Repository
@Mapper
public interface DeviceMapper {
	/**
	 * 懒人字段
	 */
	String fields = "type, machine_number, station_id, last_used_time, remarks";

	/**
	 * 新增试风试验
	 *
	 * @param device 试风试验对象
	 */
	@Insert("INSERT device(" + fields + ") VALUES(#{device.type}, #{device.machineNumber}, #{device.stationId}, #{device.lastUsedTime}, #{device.remarks})")
	void add(@Param("device") Device device);

	/**
	 * 删除试风试验
	 *
	 * @param id id
	 */
	@Delete("DELETE FROM device WHERE id=#{id}")
	void delete(@Param("id") int id);

	/**
	 * 更新试风试验
	 *
	 * @param device 设备对象
	 */
	@Update("UPDATE device SET type=#{device.type}, machine_number=#{device.machineNumber}, station_id=#{device.stationId}, last_used_time=#{device.lastUsedTime}, remarks=#{device.remarks} WHERE id=#{device.id}")
	void update(@Param("device") Device device);

	/**
	 *
	 * @param machineNumber 机器码
	 * @param lastUsedTime 最后使用时间
	 */
	@Update("UPDATE device SET last_used_time=#{lastUsedTime} WHERE machine_number=#{machineNumber}")
	void updateDeviceUsedTime(@Param("machineNumber") String machineNumber, @Param("lastUsedTime") long lastUsedTime);

	/**
	 * 获取所有试风试验
	 *
	 * @return 列表
	 */
	@Select("SELECT id, " + fields + " FROM device")
	@ResultType(Device.class)
	List<Device> listAll();

	/**
	 * 根据机器编号获取站点id
	 *
	 * @param machineNumber 机器码
	 * @return 站点id
	 */
	@Select("SELECT station_id FROM device WHERE machine_number=#{machineNumber}")
	Integer getStationIdByMachineNumber(@Param("machineNumber") String machineNumber);
}
