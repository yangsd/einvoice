package com.demo.util;

import com.demo.vo.InvoiceOrder;
import com.demo.vo.InvoiceOrderBody;
import com.demo.vo.InvoiceVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sdyang on 2016/10/25.
 */
public class EinvoiceUtil {

    public static InvoiceOrder build(InvoiceVo vo) throws ParseException {
        InvoiceOrder order = new InvoiceOrder();
        order.setVersion("1.0");
        order.setMerchant_code(vo.getMerchant_code());
        order.setSign_type("MD5");
        order.setMerchant_name(vo.getMerchant_name());
        order.setApp_no("24");
        order.setNotify_url("http://www.baidu.com");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setSubmitdate(sdf.parse(vo.getSubmitdate()));
        order.setFpqqlsh(vo.getFpqqlsh());
        order.setKplx(vo.getKplx());
        order.setXsf_nsrsbh(vo.getXsf_nsrsbh());
        order.setXsf_mc(vo.getXsf_mc());
        order.setXsf_dzdh(vo.getXsf_dzdh());
        order.setXsf_yhzh(vo.getXsf_yhzh());
        order.setGmf_mc(vo.getGmf_mc());
        order.setKpr(vo.getKpr());
        order.setSkr(vo.getSkr());
        order.setFhr(vo.getFhr());
        order.setJshj(vo.getJshj());
        order.setHjje(vo.getHjje());
        order.setHjse(vo.getHjse());
        order.setBz(vo.getBz());
        order.setOrder_no(vo.getOrder_no());
        order.setYfp_hm(vo.getYfp_hm());
        order.setYfp_dm(vo.getYfp_dm());


        List<InvoiceOrderBody> businessOrderBodyList = new ArrayList<InvoiceOrderBody>();
        InvoiceOrderBody invoiceOrderBody = new InvoiceOrderBody();
        invoiceOrderBody.setDw("台");
        invoiceOrderBody.setGgxh("TCL-L48A71");
        invoiceOrderBody.setFpqqlsh(vo.getFpqqlsh());
        invoiceOrderBody.setRow_no(1);
        invoiceOrderBody.setXmsl(1);
        invoiceOrderBody.setXmmc("彩电");
        invoiceOrderBody.setXmje(2);
        invoiceOrderBody.setXmdj(1.77);
        invoiceOrderBody.setSl(0.13);
        invoiceOrderBody.setSe(0.23);
        invoiceOrderBody.setFphxz("1");
        businessOrderBodyList.add(invoiceOrderBody);

        order.setInvoiceOrderBodyList(businessOrderBodyList);

        Map<String,String> mapData = MapUtil.beanToMap(order);
        String sign = MD5Util.createSign(mapData,vo.getKey());
        order.setMerchant_sign(sign);

        return order;

    }

    public static InvoiceOrder buildQuery(String merchant_code,String merchant_name,String order_no,String key){
        InvoiceOrder order = new InvoiceOrder();
        order.setVersion("1.0");
        order.setMerchant_code(merchant_code);
        order.setSign_type("MD5");
        order.setMerchant_name(merchant_name);
        order.setOrder_no(order_no);
        Map<String,String> mapData = MapUtil.beanToMap(order);
        String sign = MD5Util.createSign(mapData,key);
        order.setMerchant_sign(sign);
        return order;
    }

}
