package com.management.service.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.management.commons.utils.PageInfo;
import com.management.mapper.EinvoiceBodyMapper;
import com.management.model.EinvoiceBody;
import com.management.model.EinvoiceHead;
import com.management.service.IEinvoiceBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sdyang on 2016/10/23.
 */
@Service
public class EinvoiceBodyServiceImpl extends SuperServiceImpl<EinvoiceBodyMapper, EinvoiceBody> implements IEinvoiceBodyService{

    @Autowired
    private EinvoiceBodyMapper einvoiceBodyMapper;
    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<EinvoiceBody> page = new Page<EinvoiceBody>(pageInfo.getNowpage(), pageInfo.getSize());
        List<EinvoiceBody> list = einvoiceBodyMapper.selectListByHeadId(page, pageInfo.getCondition());
        pageInfo.setRows(list);
    }
}
