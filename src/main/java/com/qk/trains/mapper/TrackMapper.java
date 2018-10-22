package com.qk.trains.mapper;

import com.qk.trains.entity.Track;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: trains
 * @description: 股道Dao
 * @author: Xiaotian
 * @create: 2018-08-31 10:30
 **/
@Repository
@Mapper
public interface TrackMapper {
	/**
	 * 新增股道
	 *
	 * @param track 股道对象
	 */
	@Insert("INSERT track_show(track_number, priority, remarks) VALUES(#{track.trackNumber}, #{track.priority}, #{track.remarks})")
	void add(@Param("track") Track track);

	/**
	 * 删除股道
	 *
	 * @param id id
	 */
	@Delete("DELETE FROM track WHERE id=#{id}")
	void delete(@Param("id") int id);

	/**
	 * 更新股道信息
	 *
	 * @param track 股道编号
	 */
	@Update("UPDATE track_show SET track_number=#{track.trackNumber}, priority=#{track.priority}, remarks={track.remarks} WHERE id=#{track.id}")
	void update(@Param("track") Track track);

	@Select("SELECT id, track_number, priority, remarks FROM track")
	@ResultType(Track.class)
	List<Track> listAll();

	/**
	 * 获取排序过的股道列表
	 *
	 * @return 返回集合
	 */
	@Select("SELECT id, track_number, priority, remarks FROM track_show ORDER BY priority ASC")
	@ResultType(Track.class)
	List<Track> listAllByPriority();
}
