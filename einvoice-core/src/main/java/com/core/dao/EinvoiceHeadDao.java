package com.core.dao;

import com.core.bean.EinvoiceHead;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sdyang on 2016/10/6.
 */
public interface EinvoiceHeadDao extends CrudRepository<EinvoiceHead, Long> {

    //根据主键查询
    @Query(nativeQuery = true,value = " select * from einvoice_head where dr=0 and pk_einvoicehead=:pk_einvoicehead ")
    public EinvoiceHead findOne(@Param("pk_einvoicehead") Long pk_einvoicehead);

    //查询所有
    @Query(nativeQuery = true,value = " select * from einvoice_head where dr=0 ")
    List<EinvoiceHead> findAll();

    //根据状态查询
    @Query(nativeQuery = true,value = " select * from einvoice_head where dr=0 and status=:status")
    List<EinvoiceHead> getByStatus(@Param("status") int status);

    //根据商户编码和订单号查找发票信息
    @Query(nativeQuery = true,value = " select * from einvoice_head where dr=0 and merchant_code=:merchant_code and order_no=:order_no ")
    EinvoiceHead getByMerchantCodeAndOrderNo(@Param("merchant_code") String merchant_code,@Param("order_no") String order_no);

    //根据当前用户查询
    @Query(nativeQuery = true,value = " select * from einvoice_head where dr=0 and pk_user=:pk_user ")
    List<EinvoiceHead> getByPk_user(@Param("pk_user") Long pk_user);

    //根据业务订单号和状态查询
    @Query(nativeQuery = true,value = " select * from einvoice_head where dr=0 and order_no=:orderno and status=:status")
    List<EinvoiceHead> getByOrdernoAndStatus(@Param("orderno") String orderno,@Param("status") int status);

    //根据发票代码和发票号码查询
    @Query(nativeQuery = true,value = " select * from einvoice_head where dr=0 and fp_dm=:yfpdm and fp_hm=:yfphm ")
    EinvoiceHead getByYfp(@Param("yfphm") String yfphm,@Param("yfpdm") String yfpdm);

}
