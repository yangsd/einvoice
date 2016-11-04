package com.service.service;

import com.core.bean.EinvoiceBody;
import com.core.bean.EinvoiceHead;
import com.core.bean.User;
import com.core.constant.EinvoiceReturn;
import com.core.constant.EinvoiceStatus;
import com.core.constant.EinvoiceType;
import com.core.dao.IUserDao;
import com.core.service.EinvoiceService;
import com.service.util.MapUtil;
import com.service.util.ResponseUtil;
import com.service.vo.InvoiceOrder;
import com.service.vo.InvoiceOrderBody;
import com.service.vo.InvoiceOrderResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * Created by sdyang on 2016/10/3.
 */
@Service
@Transactional
public class InvoiceService {

    private static Logger logger = Logger.getLogger(InvoiceService.class);

    @Autowired
    private SignVerifyService signVerifyService;
    @Autowired
    private EinvoiceService einvoiceService;
    @Autowired
    private IUserDao userDao;


    public InvoiceOrderResponse saveInvoiceInfo(InvoiceOrder order) {

        InvoiceOrderResponse response = ResponseUtil.buildResponse();
        response.setOrder_no(order.getOrder_no());
        response.setFpqqlsh(order.getFpqqlsh());

        Map<String, String> mapData = MapUtil.beanToMap(order);

        boolean flag = signVerifyService.verify(mapData);

        try {
            if(flag) {//验签通过
                if(EinvoiceType.RED.getIndex() == Integer.parseInt(order.getKplx())){//红票
                    EinvoiceHead head = einvoiceService.getByYfp(order.getYfp_dm(),order.getYfp_hm());
                    head.setStatus(EinvoiceStatus.CANCEL.getIndex());//蓝票红冲
                    einvoiceService.updateEinvoiceHead(head);
                }
                EinvoiceHead einvoiceHead = transform(order);
                if(einvoiceHead != null) {
                    einvoiceHead.setStatus(EinvoiceStatus.RECEVICE_SUCCESS.getIndex());
                    einvoiceService.save(einvoiceHead);
                    response.setReturn_code(EinvoiceReturn.SUCCESS.getCode());
                    response.setReturn_msg(EinvoiceReturn.SUCCESS.getMessage());
                }else{//商户不存在
                    response.setReturn_code(EinvoiceReturn.MERCHANT_NOT_EXSIT.getCode());
                    response.setReturn_msg(EinvoiceReturn.MERCHANT_NOT_EXSIT.getMessage());
                }
            }else{//验签失败
                response.setReturn_code(EinvoiceReturn.CHECK_SIGN_FAIL.getCode());
                response.setReturn_msg(EinvoiceReturn.CHECK_SIGN_FAIL.getMessage());
            }
        } catch (Exception e) {
            response.setReturn_code(EinvoiceReturn.OTHER_ERROR.getCode());
            response.setReturn_msg(e.toString());
        }
        Map<String,String> responseMap = MapUtil.beanToMap(response);
        String responseSign = signVerifyService.sign(responseMap);
        response.setServer_sign(responseSign);

        return  response;
    }


    public InvoiceOrderResponse query(InvoiceOrder order) {
        InvoiceOrderResponse response = ResponseUtil.buildResponse();

        Map<String, String> mapData = MapUtil.beanToMap(order);

        boolean flag = signVerifyService.verify(mapData);

        EinvoiceHead einvoiceInfo = null;

        if(flag){
            einvoiceInfo = einvoiceService.getEinvoiceInfo(order.getMerchant_code(),order.getOrder_no());
            if(einvoiceInfo!=null) {
                response.setStatus(einvoiceInfo.getStatus() + "");
                response.setReturn_code(EinvoiceReturn.SUCCESS.getCode());
                response.setReturn_msg(EinvoiceStatus.getName(einvoiceInfo.getStatus()));
            }else{
                response.setStatus("-1");
                response.setReturn_code(EinvoiceReturn.ORDER_NO_NOT_EXSIT.getCode());
                response.setReturn_msg(EinvoiceReturn.ORDER_NO_NOT_EXSIT.getMessage());
            }
        }else{
            response.setReturn_code(EinvoiceReturn.CHECK_SIGN_FAIL.getCode());
            response.setReturn_msg(EinvoiceReturn.CHECK_SIGN_FAIL.getMessage());
        }

        Map<String,String> responseMap = MapUtil.beanToMap(response);
        String responseSign = signVerifyService.sign(responseMap);
        response.setServer_sign(responseSign);

        return response;
    }

    private EinvoiceHead transform(InvoiceOrder order){

        Long pk_user = null;
        User user =userDao.getByMerchant_code(order.getMerchant_code());

        if(user == null) return null;

        pk_user = user.getId();

        EinvoiceHead einvoiceHead = new EinvoiceHead();

        einvoiceHead.setPk_user(pk_user);//用户主键
        einvoiceHead.setSubmitdate(order.getSubmitdate());//提交时间
        einvoiceHead.setMerchant_code(order.getMerchant_code());//客户编号

        einvoiceHead.setFpqqlsh(order.getFpqqlsh());//发票请求流水号
        einvoiceHead.setKplx(order.getKplx());//开票类型
        einvoiceHead.setXsf_nsrsbh(order.getXsf_nsrsbh());//销售方纳税识别号
        einvoiceHead.setXsf_mc(order.getXsf_mc());//销售方名称
        einvoiceHead.setXsf_dzdh(order.getXsf_dzdh());//销售方地址电话
        einvoiceHead.setXsf_yhzh(order.getXsf_yhzh());//销售方银行帐号

        einvoiceHead.setGmf_mc(order.getGmf_mc());//购买方名称
        einvoiceHead.setGmf_dzdh(order.getGmf_dzdh());//购买方地址电话
        einvoiceHead.setGmf_nsrsbh(order.getGmf_nsrsbh());//购买方纳税人识别号
        einvoiceHead.setGmf_yhzh(order.getGmf_yhzh());//购买方银行帐号

        einvoiceHead.setKpr(order.getKpr());//开票人
        einvoiceHead.setFhr(order.getFhr());//复核人
        einvoiceHead.setSkr(order.getSkr());//收款人

        einvoiceHead.setYfp_dm(order.getYfp_dm());//原发票代码
        einvoiceHead.setYfp_hm(order.getYfp_hm());//原发票号码

        einvoiceHead.setJshj(order.getJshj());//价税合计
        einvoiceHead.setHjje(order.getHjje());//合计金额
        einvoiceHead.setHjse(order.getHjse());//合计税额

        einvoiceHead.setBz(order.getBz());//备注

        einvoiceHead.setOrder_no(order.getOrder_no());//外部订单号

        //---------------------------------------------------------------------//
        List<EinvoiceBody> einvoiceBodyList = new ArrayList<EinvoiceBody>();

        List<InvoiceOrderBody> invoiceOrderBodies = order.getInvoiceOrderBodyList();
        for(InvoiceOrderBody body:invoiceOrderBodies){
            EinvoiceBody einvoiceBody = new EinvoiceBody();

            einvoiceBody.setRow_no(body.getRow_no());
            einvoiceBody.setFpqqlsh(body.getFpqqlsh());//发票请求流水号
            einvoiceBody.setFphxz(body.getFphxz());//发票行性质
            einvoiceBody.setXmmc(body.getXmmc());//项目名称
            einvoiceBody.setDw(body.getDw());//计量单位
            einvoiceBody.setGgxh(body.getGgxh());//规格型号
            einvoiceBody.setXmsl(body.getXmsl());//项目数量
            einvoiceBody.setXmdj(body.getXmdj());//项目单价
            einvoiceBody.setXmje(body.getXmje());//项目金额
            einvoiceBody.setSl(body.getSl());//税率
            einvoiceBody.setSe(body.getSe());//税额

            einvoiceBodyList.add(einvoiceBody);
        }
        einvoiceHead.setEinvoiceBodies(einvoiceBodyList);

        return  einvoiceHead;
    }


}