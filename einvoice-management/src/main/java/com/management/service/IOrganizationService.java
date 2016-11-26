package com.management.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.management.commons.result.Tree;
import com.management.model.Organization;

/**
 *
 * Organization 表数据服务层接口
 *
 */
public interface IOrganizationService extends ISuperService<Organization> {

    List<Tree> selectTree();

    List<Organization> selectTreeGrid();

}