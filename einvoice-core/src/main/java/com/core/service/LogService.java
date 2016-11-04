package com.core.service;

import com.core.bean.EinvoiceLog;
import com.core.dao.EinvoiceLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sdyang on 2016/10/11.
 */
@Service
public class LogService {

    @Autowired
    private EinvoiceLogDao einvoiceLogDao;


    public void insert(EinvoiceLog einvoiceLog){
        einvoiceLogDao.save(einvoiceLog);
    }
}
