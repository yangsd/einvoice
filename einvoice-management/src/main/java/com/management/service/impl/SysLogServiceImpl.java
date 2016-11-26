package com.management.service.impl;

import java.util.List;

import com.management.commons.utils.PageInfo;
import com.management.mapper.SysLogMapper;
import com.management.model.Role;
import com.management.model.SysLog;
import com.management.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * SysLog 表数据服务层接口实现类
 *
 */
@Service
public class SysLogServiceImpl extends SuperServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;
    
    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<SysLog> page = new Page<SysLog>(pageInfo.getNowpage(), pageInfo.getSize());
        List<Role> list = sysLogMapper.selectSysLogList(page);
        pageInfo.setRows(list);
    }

}