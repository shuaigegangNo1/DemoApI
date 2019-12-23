package com.sgg.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sgg.rest.dao.SysMenuDao;
import com.sgg.rest.dto.BaseMenuDto;
import com.sgg.rest.dto.FirstMenuDto;
import com.sgg.rest.dto.SecondMenuDto;
import com.sgg.rest.dto.ThirdMenuDto;
import com.sgg.rest.model.SysMenu;
import com.sgg.rest.service.SysMenuService;
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuDao sysMenuDao;
	@Override
	public List<FirstMenuDto> selectFirstMenuList(Integer roleId) {
	    List<FirstMenuDto> firstMenuDtoList = sysMenuDao.selectfirstMenuListByRoleId(roleId);
		return firstMenuDtoList;
	}
	
	@Override
	public List<SecondMenuDto> selectScondMenuList(Integer menuId) {
	    List<SecondMenuDto> secondMenuDtoList = sysMenuDao.selectScondMenuList(menuId);
		return secondMenuDtoList;
	}

	@Override
	public Page<FirstMenuDto> selectFirstMenuPage(int pageNum, int pageSize) {
        Page<FirstMenuDto> p = new Page<>(pageNum, pageSize);
        p.setRecords(sysMenuDao.selectfirstMenuList(p));

        return p;
	}

	@Override
	public List<ThirdMenuDto> selectThirdMenuList(Integer menuId) {
	    List<ThirdMenuDto> secondMenuDtoList = sysMenuDao.selectThirdMenuList(menuId);
		return secondMenuDtoList;
	}

	@Override
	public List<BaseMenuDto> selectFirstMenuDtoList() {
		List<BaseMenuDto> firstMenuList = sysMenuDao.selectFirstMenuDtoList();
		return firstMenuList;
	}

}
