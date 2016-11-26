package com.demo;

import com.demo.util.JsonUtil;
import com.demo.util.MD5Util;
import com.demo.util.MapUtil;
import com.demo.vo.InvoiceOrder;
import com.demo.vo.InvoiceOrderBody;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sdyang on 2016/10/12.
 */
public class Test {


    public static void requestByGetMethod(){
        //创建默认的httpClient实例
        CloseableHttpClient httpClient = getHttpClient();
        try {
            //用get方法发送http请求
            HttpGet get = new HttpGet("http://127.0.0.1/demo");
            System.out.println("执行get请求:...."+get.getURI());
            CloseableHttpResponse httpResponse = null;
            //发送get请求
            httpResponse = httpClient.execute(get);
            try{
                //response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("响应状态码:"+ httpResponse.getStatusLine());
                    System.out.println("-------------------------------------------------");
                    System.out.println("响应内容:" + EntityUtils.toString(entity));
                    System.out.println("-------------------------------------------------");
                }
            }
            finally{
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }


    /**
     * POST方式发起http请求
     */
    public static void requestByPostMethod(){
        CloseableHttpClient httpClient = getHttpClient();
        try {
//            HttpPost post = new HttpPost("http://10.73.128.229/sendinvoice/");
            HttpPost post = new HttpPost("http://127.0.0.1:8980/sendinvoice/");
            //创建参数列表
//            List<NameValuePair> list = new ArrayList<NameValuePair>();
//            list.add(new BasicNameValuePair("j_username", "admin"));
//            list.add(new BasicNameValuePair("j_password", "admin"));
            //url格式编码
            InvoiceOrder order = init();
            String json = JsonUtil.toJSONString(order);
            System.out.println("发送的数据："+json);
//            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(json,"UTF-8");
            StringEntity stringEntity = new StringEntity(json,"UTF-8");
            post.setEntity(stringEntity);
            System.out.println("POST 请求...." + post.getURI());
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try{
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("-------------------------------------------------------");
                    System.out.println(EntityUtils.toString(entity));
                    System.out.println("-------------------------------------------------------");
                }
            } finally{
                httpResponse.close();
            }

        } catch( UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);
            } catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    private static CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }

    private static void closeHttpClient(CloseableHttpClient client) throws IOException {
        if (client != null){
            client.close();
        }
    }


    private static InvoiceOrder init(){
        InvoiceOrder order = new InvoiceOrder();
        order.setVersion("1.0");
        order.setMerchant_code("SDFL'");
        order.setSign_type("MD5");
        order.setMerchant_name("测试商户1022");
        order.setApp_no("24");
        order.setNotify_url("http://www.baidu.com");
        order.setSubmitdate(new Date());
        order.setFpqqlsh("DFSF343");
        order.setKplx("0");
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
        invoiceOrderBody.setFphxz("1");
        businessOrderBodyList.add(invoiceOrderBody);

        order.setInvoiceOrderBodyList(businessOrderBodyList);

        Map<String,String> mapData = MapUtil.beanToMap(order);
        String sign = MD5Util.createSign(mapData,"123");
        order.setMerchant_sign(sign);

        return order;

    }

    public static  void main(String[] args){
//        requestByGetMethod();
        requestByPostMethod();
    }
}
