package com.management.service;

import com.baomidou.framework.service.ISuperService;
import com.management.commons.utils.PageInfo;
import com.management.model.SysLog;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ISysLogService extends ISuperService<SysLog> {

    void selectDataGrid(PageInfo pageInfo);


}