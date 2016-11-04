package com.core.dao;

import com.core.bean.EinvoiceLog;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sdyang on 2016/10/11.
 */
public interface EinvoiceLogDao extends CrudRepository<EinvoiceLog, Long> {
}
