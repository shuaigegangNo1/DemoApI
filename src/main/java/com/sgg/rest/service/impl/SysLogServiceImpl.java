package com.sgg.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * author name: syg
 * create time: 2019-12-13 16:18:52
 */ 
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sgg.rest.dao.SysLogDao;
import com.sgg.rest.service.SysLogService;
import com.sgg.rest.model.SysLog;
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLog>implements SysLogService{
	@Autowired
	private SysLogDao sysLogDao;
	@Override
	public List<SysLog> selectLogList(String fromTime, String toTime) {
		List<SysLog> sysLogList = sysLogDao.selectLogList( fromTime,  toTime);
		return sysLogList;
	}


}

