package com.management.service;

import com.baomidou.framework.service.ISuperService;
import com.management.commons.utils.PageInfo;
import com.management.model.EinvoiceHead;

/**
 * Created by sdyang on 2016/10/23.
 */
public interface IEinvoiceHeadService extends ISuperService<EinvoiceHead> {

    void selectDataGrid(PageInfo pageInfo);
}
