package com.management.controller;

import javax.servlet.http.HttpServletRequest;

import com.management.commons.base.BaseController;
import com.management.model.Organization;
import com.management.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * @description：部门管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {

    @Autowired
    private IOrganizationService organizationService;

    /**
     * 部门管理主页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "admin/organization";
    }

    /**
     * 部门资源树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public Object tree() {
        return organizationService.selectTree();
    }

    /**
     * 部门列表
     *
     * @return
     */
    @RequestMapping("/treeGrid")
    @ResponseBody
    public Object treeGrid() {
        return organizationService.selectTreeGrid();
    }

    /**
     * 添加部门页
     *
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "admin/organizationAdd";
    }

    /**
     * 添加部门
     *
     * @param organization
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(Organization organization) {
        organization.setCreateTime(new Date());
        organization.setIcon("icon-company");
        organizationService.insert(organization);
        return renderSuccess("添加成功！");
    }

    /**
     * 编辑部门页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        Organization organization = organizationService.selectById(id);
        request.setAttribute("organization", organization);
        return "admin/organizationEdit";
    }

    /**
     * 编辑部门
     *
     * @param organization
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(Organization organization) {
        organizationService.updateSelectiveById(organization);
        return renderSuccess("编辑成功！");
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        organizationService.deleteById(id);
        return renderSuccess("删除成功！");
    }
}
