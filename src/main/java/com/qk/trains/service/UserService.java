package com.qk.trains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qk.trains.entity.User;
import com.qk.trains.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public User toLogin(String username,String password) {
		return userMapper.toLogin(username, password);
	}
}
