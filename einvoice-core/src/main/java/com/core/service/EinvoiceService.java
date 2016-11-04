package com.core.service;

import com.core.bean.EinvoiceBody;
import com.core.bean.EinvoiceHead;
import com.core.constant.EinvoiceStatus;
import com.core.dao.EinvoiceBodyDao;
import com.core.dao.EinvoiceHeadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdyang on 2016/10/6.
 */
@Service
@Transactional
public class EinvoiceService {

    @Autowired
    private EinvoiceHeadDao einvoiceHeadDao;

    @Autowired
    private EinvoiceBodyDao einvoiceBodyDao;

    //新增保存
    public EinvoiceHead save(EinvoiceHead einvoiceHead){
        Long pk_einvoicehead = null;
        EinvoiceHead result = new EinvoiceHead();
        List<EinvoiceBody> bodyList = new ArrayList<EinvoiceBody>();

        List<EinvoiceBody> einvoiceBodyList = einvoiceHead.getEinvoiceBodies();
        result = einvoiceHeadDao.save(einvoiceHead);
        pk_einvoicehead = result.getPk_einvoicehead();
        for(EinvoiceBody body:einvoiceBodyList){
            EinvoiceBody einvoiceBody = new EinvoiceBody();
            body.setPk_einvoicehead(pk_einvoicehead);
            einvoiceBody = einvoiceBodyDao.save(body);
            bodyList.add(einvoiceBody);
        }
        result.setEinvoiceBodies(bodyList);

        return  result;
    }

    //根据主键查询
    public EinvoiceHead findOne(Long pk_einvoicehead){
        EinvoiceHead einvoiceHead = einvoiceHeadDao.findOne(pk_einvoicehead);
        List<EinvoiceBody> einvoiceBodies = einvoiceBodyDao.getByPk_einvoicehead(pk_einvoicehead);
        einvoiceHead.setEinvoiceBodies(einvoiceBodies);
        return  einvoiceHead;
    }

    //逻辑删除
    public void delete(Long pk_einvoicehead){
        EinvoiceHead einvoiceHead = einvoiceHeadDao.findOne(pk_einvoicehead);
        einvoiceHead.setDr(1);
        einvoiceHeadDao.save(einvoiceHead);

        List<EinvoiceBody> einvoiceBodies = einvoiceBodyDao.getByPk_einvoicehead(pk_einvoicehead);
        for(EinvoiceBody body:einvoiceBodies){
            body.setDr(1);
            einvoiceBodyDao.save(body);
        }
    }

    //根据状态获取发票数据
    public List<EinvoiceHead> getByStatus(EinvoiceStatus status){
        List<EinvoiceHead> einvoiceHeads = einvoiceHeadDao.getByStatus(status.getIndex());
        if(einvoiceHeads == null || einvoiceHeads.size()==0) return null;
        for(EinvoiceHead head:einvoiceHeads) {
            List<EinvoiceBody> einvoiceBodies = einvoiceBodyDao.getByPk_einvoicehead(head.getPk_einvoicehead());
            head.setEinvoiceBodies(einvoiceBodies);
        }
        return  einvoiceHeads;
    }

    //更新发票头信息
    public void updateEinvoiceHead(EinvoiceHead einvoiceHead){
        einvoiceHeadDao.save(einvoiceHead);
    }

    //更新发票状态
    public void updateEinvoiceStatus(Long pk_einvoicehead,EinvoiceStatus status){
        EinvoiceHead einvoiceHead = einvoiceHeadDao.findOne(pk_einvoicehead);
        einvoiceHead.setStatus(status.getIndex());
        einvoiceHeadDao.save(einvoiceHead);
    }

    //根据商户编码和订单号查询发票信息
    public EinvoiceHead getEinvoiceInfo(String merchant_code,String order_no){
        EinvoiceHead einvoiceHead = einvoiceHeadDao.getByMerchantCodeAndOrderNo(merchant_code,order_no);
        if(einvoiceHead!=null) {
            List<EinvoiceBody> einvoiceBodies = einvoiceBodyDao.getByPk_einvoicehead(einvoiceHead.getPk_einvoicehead());
            einvoiceHead.setEinvoiceBodies(einvoiceBodies);
        }
        return einvoiceHead;
    }

    //根据用户主键查找
    public List<EinvoiceHead> getByUserid(Long userid){
        List<EinvoiceHead> einvoiceHeads = einvoiceHeadDao.getByPk_user(userid);
        return einvoiceHeads;
    }

    //根据订单号查询
    public List<EinvoiceHead> getByOrdernoAndStatus(String orderno,EinvoiceStatus status){
        List<EinvoiceHead> einvoiceHeads = einvoiceHeadDao.getByOrdernoAndStatus(orderno,status.getIndex());
        if(einvoiceHeads !=null && einvoiceHeads.size()>0) {
            for (EinvoiceHead head : einvoiceHeads) {
                List<EinvoiceBody> einvoiceBodies = einvoiceBodyDao.getByPk_einvoicehead(head.getPk_einvoicehead());
                head.setEinvoiceBodies(einvoiceBodies);
            }
        }
        return  einvoiceHeads;
    }

    //根据发票代码和发票号码查找发票信息
    public EinvoiceHead getByYfp(String yfpdm,String yfphm){
        EinvoiceHead einvoiceHead = einvoiceHeadDao.getByYfp(yfphm,yfpdm);
        return einvoiceHead;
    }


}
