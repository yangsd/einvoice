package com.demo.action;

import com.demo.util.EinvoiceUtil;
import com.demo.util.HttpUtil;
import com.demo.vo.InvoiceOrder;
import com.demo.vo.InvoiceVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sdyang on 2016/10/25.
 */
@Controller
public class EinvoiceAction {

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(Model model){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        model.addAttribute("fpqqlsh",df.format(new Date()));
        model.addAttribute("submitdate",new Date());
        return "index";
    }

    @RequestMapping(value = "send",method = RequestMethod.POST)
    public String sendData(Model model,InvoiceVo invoiceVo) throws ParseException {
        InvoiceOrder order = EinvoiceUtil.build(invoiceVo);
        String result = HttpUtil.post(order,"http://127.0.0.1:8981/sendinvoice");
        model.addAttribute("msg",result);
        return "result";
    }

    @RequestMapping(value = "query",method = RequestMethod.GET)
    public String queryPage(Model model){
        return "query";
    }

    @RequestMapping(value = "query",method = RequestMethod.POST)
    public String query(Model model, @RequestParam("merchant_code") String merchant_code,@RequestParam("merchant_name")String merchant_name,
                        @RequestParam("key")String key,@RequestParam("order_no")String order_no) throws ParseException {
        InvoiceOrder order = EinvoiceUtil.buildQuery(merchant_code,merchant_name,order_no,key);
        String result = HttpUtil.post(order,"http://127.0.0.1:8981/queryinvoice");
        model.addAttribute("msg",result);
        return "result";
    }
}
