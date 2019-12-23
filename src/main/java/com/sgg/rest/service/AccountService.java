package com.sgg.rest.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sgg.rest.model.Account;
import com.sgg.rest.util.PageRequest;


public interface AccountService extends IService<Account> {
	List<Account> selectAccountList();
	Page<Account> selectAccountPageList();
	
//    PageResult queryPage(Page<Account> page);
//    PageResult findPage(PageRequest pageRequest);
    Integer createNewAccount(Account account);
    Integer updateAccount(Account account);
	Page<Account> selectPageExt(int page, int pageSize);
}
