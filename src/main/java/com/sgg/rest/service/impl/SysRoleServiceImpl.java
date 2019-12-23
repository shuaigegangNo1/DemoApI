package com.sgg.rest.service.impl;

/**
 * author name: syg
 * create time: 2019-10-15 16:03:37
 */ 
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sgg.rest.dao.SysRoleDao;
import com.sgg.rest.service.SysRoleService;
import com.sgg.rest.model.SysRole;
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole>implements SysRoleService{


}
