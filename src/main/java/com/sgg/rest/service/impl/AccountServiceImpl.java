package com.sgg.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sgg.rest.dao.AccountDao;
import com.sgg.rest.model.Account;
import com.sgg.rest.service.AccountService;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account>implements AccountService {
    @Autowired
    AccountDao accountDao;
	@Override
	public List<Account> selectAccountList() {
        return accountDao.selectAccountList();
	}
//	@Override
//	public Page<Account> selectAccountPageList() {
//		// TODO Auto-generated method stub
////		return accountDao.selectAccountPage(page);
//		return null;
//	}
//	@Override
//	public PageResult queryPage(Page<Account> page) {
//
//        return null;
//	}
//	@Override
//	public PageResult findPage(PageRequest pageRequest) {
//        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
//        List<Account> sysMenus = accountDao.selectPage();
//        PageInfo<Account> pageInfo = new PageInfo<Account>(sysMenus);
//		return new PageResult(pageRequest,pageInfo);
//	}
	
	@Override
	public Integer createNewAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDao.insert(account);
	}
	@Override
	public Integer updateAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDao.updateById(account);
	}
	@Override
	public Page<Account> selectPageExt(int page, int pageSize) {
	        Page<Account> p = new Page<>(page, pageSize);
	        p.setRecords(accountDao.selectPageExt(p));
	        return p;
	}
	@Override
	public Page<Account> selectAccountPageList() {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
//	public PageResult queryPage(Page<Account> page) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
//	private PageInfo<Account> getPageInfo(PageRequest pageRequest) {
//        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
//        List<Account> sysMenus = accountDao.selectPage();
//        return new PageInfo<Account>(sysMenus);
//    }
}
