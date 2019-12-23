package com.sgg.rest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sgg.rest.dto.BaseMenuDto;
import com.sgg.rest.dto.FirstMenuDto;
import com.sgg.rest.dto.SecondMenuDto;
import com.sgg.rest.dto.ThirdMenuDto;
import com.sgg.rest.model.SysMenu;
/**
 * author name: syg
 * create time: 2019-10-12 09:39:51
 */ 
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenu>{

	List<FirstMenuDto> selectfirstMenuList();

	List<SecondMenuDto> selectScondMenuList( @Param("menuId")  Integer menuId);

	List<FirstMenuDto> selectfirstMenuList(com.baomidou.mybatisplus.plugins.Page<FirstMenuDto> p);

	List<FirstMenuDto> selectfirstMenuListByRoleId(@Param("roleId") Integer roleId);

	List<ThirdMenuDto> selectThirdMenuList(@Param("menuId") Integer menuId);

	List<BaseMenuDto> selectFirstMenuDtoList();
}

