package com.sgg.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * author name: syg
 * create time: 2019-10-24 10:04:51
 */ 
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sgg.rest.dao.UserDao;
import com.sgg.rest.dto.ChartDataDto;
import com.sgg.rest.dto.SignUpDataDto;
import com.sgg.rest.service.UserService;
import com.sgg.rest.model.User;
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User>implements UserService{
    @Autowired
    private UserDao userDao;
	@Override
	public User findUserByName(String name) {
    	EntityWrapper<User> wrapper = new EntityWrapper<>();
    	User user = this.selectOne(wrapper.eq("name",name));
		return user;

	}

	@Override
	public List<SignUpDataDto> getSignUpData() {
		List<SignUpDataDto> list = userDao.getSignUpDataByDay();
		return list;
	}

	@Override
	public List<ChartDataDto> getSignUpDataByMonth(String year) {
		List<ChartDataDto> list = userDao.getSignUpDataByMonth(year);
		return list;
	}

}

