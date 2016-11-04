package com.core.dao;


import com.core.bean.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 
 * 
 * @author sdyang
 * @date 2016年1月23日 下午5:42:27
 */
public interface IUserDao extends CrudRepository<User, Long> {

	// 根据用户登录帐号查询用户信息
	 User findByLoginName(String loginName);

	//根据商户编码查找用户
	@Query(nativeQuery = true,value = "select * from user where merchant_code = :merchant_code ")
	 User getByMerchant_code(@Param("merchant_code") String merchant_code);
}
