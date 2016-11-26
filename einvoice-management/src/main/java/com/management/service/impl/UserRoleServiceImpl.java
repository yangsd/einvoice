package com.management.service.impl;

import com.management.mapper.UserRoleMapper;
import com.management.model.UserRole;
import com.management.service.IUserRoleService;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * UserRole 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl extends SuperServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}