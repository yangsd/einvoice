package com.management.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.management.commons.result.Tree;
import com.management.model.Resource;
import com.management.model.User;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IResourceService extends ISuperService<Resource> {

    List<Resource> selectAll();

    List<Tree> selectAllTree();

    List<Tree> selectAllTrees();

    List<Tree> selectTree(User currentUser);

}