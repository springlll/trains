package com.qk.trains.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qk.trains.entity.Direction;
import com.qk.trains.entity.User;

/**
 * @program: trains
 * @description: 用户操作Dao
 * @author: dk
 * @create: 2018-09-17 14:55
 **/
@Repository
@Mapper
public interface UserMapper {
	@Select("select u.username,u.`password` from `user` u where u.`username`=#{username} and u.`password`=#{password}")
	User toLogin(@Param("username") String username,@Param("password") String password);
}
