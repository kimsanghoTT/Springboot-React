package com.kh.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.dto.NaverUser;

@Mapper
public interface NaverUserMapper {

	void insertNaverUser(NaverUser naverUser);
}
