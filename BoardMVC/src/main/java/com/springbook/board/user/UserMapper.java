package com.springbook.board.user;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	public int join(UserVO param);
	public UserVO selUser(UserVO param);
	public int updUser(UserVO param);
	
}
