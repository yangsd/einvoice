package com.management.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.management.commons.result.Tree;
import com.management.mapper.OrganizationMapper;
import com.management.model.Organization;
import com.management.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * Organization 表数据服务层接口实现类
 *
 */
@Service
public class OrganizationServiceImpl extends SuperServiceImpl<OrganizationMapper, Organization> implements IOrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;
    
    @Override
    public List<Tree> selectTree() {
        List<Tree> trees = new ArrayList<Tree>();

        List<Organization> organizationFather = organizationMapper.selectByPIdNull();

        if (organizationFather != null) {
            for (Organization organizationOne : organizationFather) {
                Tree treeOne = new Tree();

                treeOne.setId(organizationOne.getId());
                treeOne.setText(organizationOne.getName());
                treeOne.setIconCls(organizationOne.getIcon());

                List<Organization> organizationSon = organizationMapper.selectAllByPId(organizationOne.getId());

                if (organizationSon != null) {
                    List<Tree> tree = new ArrayList<Tree>();
                    for (Organization organizationTwo : organizationSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(organizationTwo.getId());
                        treeTwo.setText(organizationTwo.getName());
                        treeTwo.setIconCls(organizationTwo.getIcon());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                } else {
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
        }
        return trees;
    }

    @Override
    public List<Organization> selectTreeGrid() {
        return organizationMapper.selectAll();
    }


}