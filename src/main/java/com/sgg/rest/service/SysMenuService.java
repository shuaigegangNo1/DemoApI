package com.sgg.rest.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sgg.rest.dto.BaseMenuDto;
import com.sgg.rest.dto.FirstMenuDto;
import com.sgg.rest.dto.SecondMenuDto;
import com.sgg.rest.dto.ThirdMenuDto;
import com.sgg.rest.model.SysMenu;

/**
 * author name: syg
 * create time: 2019-10-12 09:39:51
 */ 
public interface SysMenuService extends IService<SysMenu>{

	List<FirstMenuDto> selectFirstMenuList(Integer roleId);

	List<SecondMenuDto> selectScondMenuList(Integer menuId);

	Page<FirstMenuDto> selectFirstMenuPage(int pageNum, int pageSize);

	List<ThirdMenuDto> selectThirdMenuList(Integer menuId);

	List<BaseMenuDto> selectFirstMenuDtoList();


}

