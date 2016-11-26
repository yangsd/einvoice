package com.management.service.impl;

import com.management.mapper.RoleResourceMapper;
import com.management.model.Resource;
import com.management.model.RoleResource;
import com.management.service.IRoleResourceService;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;

import java.util.Date;

/**
 *
 * RoleResource 表数据服务层接口实现类
 *
 */
@Service
public class RoleResourceServiceImpl extends SuperServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService {

}