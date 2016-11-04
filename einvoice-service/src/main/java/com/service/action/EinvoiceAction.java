package com.service.action;

import com.core.util.JsonUtil;
import com.service.service.InvoiceService;
import com.service.vo.InvoiceOrder;
import com.service.vo.InvoiceOrderResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sdyang on 2016/10/7.
 */
@Controller
public class EinvoiceAction {

    private static Logger logger = Logger.getLogger(EinvoiceAction.class);

    @Autowired
    private InvoiceService invoiceService;

    //保存发票信息
    @RequestMapping(value = "/sendinvoice", method = { RequestMethod.POST })
    @ResponseBody
    public String getInvoiceInfo(@RequestBody String  orderjson){
        InvoiceOrder invoiceOrder = JsonUtil.parseObject(orderjson,InvoiceOrder.class);
        InvoiceOrderResponse response =invoiceService.saveInvoiceInfo(invoiceOrder);
        return JsonUtil.toJSONString(response);
    }

    //查询
    @RequestMapping(value = "/queryinvoice", method = { RequestMethod.POST })
    @ResponseBody
    public String query(@RequestBody String  orderjson){
        InvoiceOrder invoiceOrder = JsonUtil.parseObject(orderjson,InvoiceOrder.class);
        InvoiceOrderResponse response =invoiceService.query(invoiceOrder);
        return JsonUtil.toJSONString(response);
    }

    @RequestMapping(value = "test",method = {RequestMethod.GET})
    @ResponseBody
    public String test(){
        return "success";
    }
}
