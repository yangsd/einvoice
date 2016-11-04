package com.service.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by sdyang on 2016/10/10.
 */
public class InvoiceOrder {

    private String charset;//字符集

    private String version;//接口版本

    private String merchant_cert;//商户证书

    private String merchant_sign;//商户签名

    private String sign_type;//签名方式

    private String merchant_code;//商户编码

    private String merchant_name;//商户名称

    private String app_no;//商户应用号

    private String notify_url;//后台通知url

    private Date submitdate;//提交时间  服务消费方请求时间，格式‘YYYY-MM-DD HH24:MI:SS’

    //-------------------------------------------------//

    private String fpqqlsh;//发票请求流水号

    private String kplx;//开票类型

    private String xsf_nsrsbh;//销售方纳税人识别号

    private String xsf_mc;//销售方名称

    private String xsf_dzdh;//销售方地址、电话

    private String xsf_yhzh;//销售方银行账号

    private String gmf_nsrsbh;//购买方纳税人识别号

    private String gmf_mc;//购买方名称

    private String gmf_dzdh;//购买方地址、电话

    private String gmf_yhzh;//购买方银行账号

    private String kpr;//开票人

    private String skr;//收款人

    private String fhr;//复核人

    private String yfp_dm;//原发票代码

    private String yfp_hm;//原发票号码

    private double jshj;//价税合计

    private double hjje;//合计金额

    private double hjse;//合计税额

    private String bz;//备注

    //----------------------------------------------------//

    private String order_no;//订单号

    private String attach;//附加数据

    private List<InvoiceOrderBody> invoiceOrderBodyList;

    //---------------------------------------------------//

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMerchant_cert() {
        return merchant_cert;
    }

    public void setMerchant_cert(String merchant_cert) {
        this.merchant_cert = merchant_cert;
    }

    public String getMerchant_sign() {
        return merchant_sign;
    }

    public void setMerchant_sign(String merchant_sign) {
        this.merchant_sign = merchant_sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getMerchant_code() {
        return merchant_code;
    }

    public void setMerchant_code(String merchant_code) {
        this.merchant_code = merchant_code;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getApp_no() {
        return app_no;
    }

    public void setApp_no(String app_no) {
        this.app_no = app_no;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    public String getFpqqlsh() {
        return fpqqlsh;
    }

    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh;
    }

    public String getKplx() {
        return kplx;
    }

    public void setKplx(String kplx) {
        this.kplx = kplx;
    }

    public String getXsf_nsrsbh() {
        return xsf_nsrsbh;
    }

    public void setXsf_nsrsbh(String xsf_nsrsbh) {
        this.xsf_nsrsbh = xsf_nsrsbh;
    }

    public String getXsf_mc() {
        return xsf_mc;
    }

    public void setXsf_mc(String xsf_mc) {
        this.xsf_mc = xsf_mc;
    }

    public String getXsf_dzdh() {
        return xsf_dzdh;
    }

    public void setXsf_dzdh(String xsf_dzdh) {
        this.xsf_dzdh = xsf_dzdh;
    }

    public String getXsf_yhzh() {
        return xsf_yhzh;
    }

    public void setXsf_yhzh(String xsf_yhzh) {
        this.xsf_yhzh = xsf_yhzh;
    }

    public String getGmf_nsrsbh() {
        return gmf_nsrsbh;
    }

    public void setGmf_nsrsbh(String gmf_nsrsbh) {
        this.gmf_nsrsbh = gmf_nsrsbh;
    }

    public String getGmf_mc() {
        return gmf_mc;
    }

    public void setGmf_mc(String gmf_mc) {
        this.gmf_mc = gmf_mc;
    }

    public String getGmf_dzdh() {
        return gmf_dzdh;
    }

    public void setGmf_dzdh(String gmf_dzdh) {
        this.gmf_dzdh = gmf_dzdh;
    }

    public String getGmf_yhzh() {
        return gmf_yhzh;
    }

    public void setGmf_yhzh(String gmf_yhzh) {
        this.gmf_yhzh = gmf_yhzh;
    }

    public String getKpr() {
        return kpr;
    }

    public void setKpr(String kpr) {
        this.kpr = kpr;
    }

    public String getSkr() {
        return skr;
    }

    public void setSkr(String skr) {
        this.skr = skr;
    }

    public String getFhr() {
        return fhr;
    }

    public void setFhr(String fhr) {
        this.fhr = fhr;
    }

    public String getYfp_dm() {
        return yfp_dm;
    }

    public void setYfp_dm(String yfp_dm) {
        this.yfp_dm = yfp_dm;
    }

    public String getYfp_hm() {
        return yfp_hm;
    }

    public void setYfp_hm(String yfp_hm) {
        this.yfp_hm = yfp_hm;
    }

    public double getJshj() {
        return jshj;
    }

    public void setJshj(double jshj) {
        this.jshj = jshj;
    }

    public double getHjje() {
        return hjje;
    }

    public void setHjje(double hjje) {
        this.hjje = hjje;
    }

    public double getHjse() {
        return hjse;
    }

    public void setHjse(double hjse) {
        this.hjse = hjse;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public List<InvoiceOrderBody> getInvoiceOrderBodyList() {
        return invoiceOrderBodyList;
    }

    public void setInvoiceOrderBodyList(List<InvoiceOrderBody> invoiceOrderBodyList) {
        this.invoiceOrderBodyList = invoiceOrderBodyList;
    }
}
