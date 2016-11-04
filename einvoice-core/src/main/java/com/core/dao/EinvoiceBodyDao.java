package com.core.dao;

import com.core.bean.EinvoiceBody;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sdyang on 2016/10/6.
 */
public interface EinvoiceBodyDao extends CrudRepository<EinvoiceBody, Long>{

    //根据主表主键查询
    @Query(nativeQuery = true,value = " select * from einvoice_body where dr=0 and pk_einvoicehead=:pk_einvoicehead ")
    public List<EinvoiceBody> getByPk_einvoicehead(@Param("pk_einvoicehead")Long pk_einvoicehead);

}
