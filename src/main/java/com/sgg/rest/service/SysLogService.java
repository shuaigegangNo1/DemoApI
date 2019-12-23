package com.sgg.rest.service;

import java.util.List;

/**
 * author name: syg
 * create time: 2019-12-13 16:18:52
 */ 
import com.baomidou.mybatisplus.service.IService;
import com.sgg.rest.model.SysLog;
public interface SysLogService extends IService<SysLog>{
	List<SysLog> selectLogList(String fromTime, String toTime);

}

