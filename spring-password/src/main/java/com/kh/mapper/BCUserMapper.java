package com.kh.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.dto.BCUser;

@Mapper
public interface BCUserMapper {

	void saveUser(BCUser user);
}
