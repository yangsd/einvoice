package com.management.service;

import com.baomidou.framework.service.ISuperService;
import com.management.commons.utils.PageInfo;
import com.management.model.EinvoiceBody;

/**
 * Created by sdyang on 2016/10/23.
 */
public interface IEinvoiceBodyService extends ISuperService<EinvoiceBody> {

    void selectDataGrid(PageInfo pageInfo);
}
