package com.sgg.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sgg.rest.dto.ChartDataDto;
import com.sgg.rest.dto.SignUpDataDto;
import com.sgg.rest.model.User;
/**
 * author name: syg
 * create time: 2019-10-24 10:04:51
 */ 
@Mapper
public interface UserDao extends BaseMapper<User>{

	List<SignUpDataDto> getSignUpDataByDay();
	List<SignUpDataDto> getSignUpDataByMonth();
	List<ChartDataDto> getSignUpDataByMonth(@Param("year") String year);

}

