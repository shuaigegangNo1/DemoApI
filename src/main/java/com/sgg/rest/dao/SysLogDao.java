package com.sgg.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sgg.rest.model.SysLog;
/**
 * author name: syg
 * create time: 2019-12-13 16:18:52
 */ 
@Mapper
public interface SysLogDao extends BaseMapper<SysLog>{
	List<SysLog> selectLogList(@Param("fromTime") String fromTime, @Param("toTime") String toTime);
}

