package com.sgg.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sgg.rest.model.Account;


@Mapper
public interface AccountDao extends BaseMapper<Account>{
    List<Account> selectAccountList();
    List<Account> selectAccountPage (Page<Account> page);
//    List<Account> selectPageExt(Page<Account> page);
    
    /**
     * 分页查询账号
     * @return
     */
    List<Account> selectPage();
	List<Account> selectPageExt(com.baomidou.mybatisplus.plugins.Page<Account> p);
    
}