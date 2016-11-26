package com.management.service.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.management.commons.utils.PageInfo;
import com.management.mapper.EinvoiceHeadMapper;
import com.management.model.EinvoiceHead;
import com.management.service.IEinvoiceHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sdyang on 2016/10/23.
 */
@Service
public class EinvoiceHeadServiceImpl extends SuperServiceImpl<EinvoiceHeadMapper, EinvoiceHead> implements IEinvoiceHeadService {

    @Autowired
    private EinvoiceHeadMapper einvoiceHeadMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<EinvoiceHead> page = new Page<EinvoiceHead>(pageInfo.getNowpage(), pageInfo.getSize());
        List<EinvoiceHead> list = einvoiceHeadMapper.selectHeadList(page, pageInfo.getCondition());
        pageInfo.setRows(list);
    }
}
