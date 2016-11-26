package com.management.controller;

import com.management.commons.base.BaseController;
import com.management.commons.utils.PageInfo;
import com.management.commons.utils.StringUtils;
import com.management.model.EinvoiceHead;
import com.management.model.Role;
import com.management.model.User;
import com.management.model.vo.EinvoiceParmVo;
import com.management.model.vo.UserVo;
import com.management.service.IEinvoiceBodyService;
import com.management.service.IEinvoiceHeadService;
import com.management.service.IRoleService;
import com.management.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sdyang on 2016/10/24.
 */
@Controller
@RequestMapping("/einvoice")
public class EinvoiceController extends BaseController {

    @Autowired
    private IEinvoiceHeadService einvoiceHeadService;
    @Autowired
    private IEinvoiceBodyService einvoiceBodyService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String  manager() {
        return "einvoice/head";
    }

    @RequestMapping(value = "/dataGrid",method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(EinvoiceParmVo parmVo, Integer page, Integer rows) {
        boolean isAdmin = false;
        Subject subject = SecurityUtils.getSubject();
        User user = userService.selectByLoginName(subject.getPrincipal().toString());
        List<Long> idlist = roleService.selectRoleIdListByUserId(user.getId());
        List<Role> roles = roleService.selectBatchIds(idlist);
        for(Role role:roles){
            if(role.getName().equals("管理员")){
                isAdmin = true;
            }
        }

        PageInfo pageInfo = new PageInfo(page, rows,"pk_einvoicehead","desc");
        Map<String, Object> condition = new HashMap<String, Object>();

        if(!isAdmin){
            condition.put("pk_user", user.getId());
        }

        if (StringUtils.isNotEmpty(parmVo.getFpqqlsh())) {
            condition.put("fpqqlsh", parmVo.getFpqqlsh());
        }

        condition.put("status", parmVo.getStatus());

        if(StringUtils.isNotEmpty(parmVo.getOrder_no())){
            condition.put("order_no",parmVo.getOrder_no());
        }

        if(StringUtils.isNotEmpty(parmVo.getXsf_mc())){
            condition.put("xsf_mc",parmVo.getXsf_mc());
        }
        if (parmVo.getSubmit_begin()!=null) {
            condition.put("submit_begin", parmVo.getSubmit_begin());
        }

        if(parmVo.getSubmit_end()!=null){
            condition.put("submit_end",parmVo.getSubmit_end());
        }
        pageInfo.setCondition(condition);
        einvoiceHeadService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    @RequestMapping(value = "/body/{pk_einvoicehead}", method = RequestMethod.GET)
    public String  body(Model model,@PathVariable("pk_einvoicehead") Long id) {
        model.addAttribute("id",id);
        return "einvoice/body";
    }

    @RequestMapping(value = "/detail/{pk_einvoicehead}",method = RequestMethod.POST)
    @ResponseBody
    public Object detail(@PathVariable("pk_einvoicehead") Long id) {
        PageInfo pageInfo = new PageInfo(0, 20,"pk_einvoicebody","desc");
        Map<String, Object> condition = new HashMap<String, Object>();

        condition.put("pk_einvoicehead", id);

        pageInfo.setCondition(condition);
        einvoiceBodyService.selectDataGrid(pageInfo);
        return pageInfo;
    }
}
