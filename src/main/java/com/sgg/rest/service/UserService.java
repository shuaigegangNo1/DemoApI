package com.sgg.rest.service;

import java.util.List;


import com.baomidou.mybatisplus.service.IService;
import com.sgg.rest.dto.ChartDataDto;
import com.sgg.rest.dto.SignUpDataDto;
import com.sgg.rest.model.User;


public interface UserService extends IService<User> {
//	@Autowired
//	private UserRepository userRepository;
	
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
//    public ApplicationUser findOne(Integer id){
//        return userRepository.findOne(id);
//    }
//    Page<User> findUserNoCriteria(Integer page,Integer size);  
//    Page<User> findUserCriteria(Integer page,Integer size,UserQuery userQuery);  
	
	User findUserByName(String name);

	List<SignUpDataDto> getSignUpData();

	List<ChartDataDto> getSignUpDataByMonth( String year);
}
