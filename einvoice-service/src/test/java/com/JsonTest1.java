package com;

import com.core.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.util.MD5Util;
import com.service.util.MapUtil;
import com.service.vo.InvoiceOrder;
import com.service.vo.InvoiceOrderBody;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sdyang on 2016/10/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = com.SpringBootStart.class)
@WebAppConfiguration
public class JsonTest1 {

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationConnect;

    @Before
    public void setUp() throws JsonProcessingException {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();
    }

    @Test
    public void testjson() throws Exception {
        String uri = "/sendinvoice";
       InvoiceOrder invoiceOrder = init();
        String param = JsonUtil.toJSONString(invoiceOrder);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(param).accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        System.out.println(status+":"+content);
    }

    private static InvoiceOrder init(){
        InvoiceOrder order = new InvoiceOrder();
        order.setVersion("1.0");
        order.setMerchant_code("123'");
        order.setSign_type("MD5");
        order.setMerchant_name("测试商户");
        order.setApp_no("24");
        order.setNotify_url("http://www.baidu.com");
        order.setSubmitdate(new Date());
        order.setFpqqlsh("12323434");
        order.setKplx("1");
        order.setXsf_nsrsbh("sdfsdf");
        order.setXsf_mc("销售方名称");
        order.setXsf_dzdh("234s水电费");
        order.setXsf_yhzh("2342");
        order.setGmf_mc("消费者");
        order.setKpr("开票人");
        order.setSkr("收款人");
        order.setFhr("复核人");
        order.setJshj(23423);
        order.setHjje(23234);
        order.setHjse(23423);
        order.setBz("备注");


        List<InvoiceOrderBody> businessOrderBodyList = new ArrayList<InvoiceOrderBody>();
        InvoiceOrderBody invoiceOrderBody = new InvoiceOrderBody();
        invoiceOrderBody.setDw("单位");
        invoiceOrderBody.setGgxh("规格型号");
        invoiceOrderBody.setFpqqlsh("12");
        invoiceOrderBody.setRow_no(1);
        invoiceOrderBody.setXmsl(1);
        invoiceOrderBody.setXmmc("项目名称");
        invoiceOrderBody.setXmje(12);
        invoiceOrderBody.setXmdj(5);
        invoiceOrderBody.setSl(17);
        invoiceOrderBody.setSe(23);
        businessOrderBodyList.add(invoiceOrderBody);

        order.setInvoiceOrderBodyList(businessOrderBodyList);

        Map<String,String> mapData = MapUtil.beanToMap(order);
        String sign = MD5Util.createSign(mapData,"1233");
        order.setMerchant_sign(sign);

        return order;

    }

}
