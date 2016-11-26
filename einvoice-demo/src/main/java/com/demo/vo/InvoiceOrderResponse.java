package com.demo.vo;

/**
 * Created by sdyang on 2016/10/10.
 */
public class InvoiceOrderResponse {

    private String charset;//字符集

    private String version;//接口版本

    private String server_cert;//服务器证书

    private String server_sign;//服务器签名

    private String sign_type;//签名方式

    private String return_code;//返回码

    private String return_msg;//返回信息

    private String merchant_code;//商户编码

    private String app_no;//商户应用号

    private String fpqqlsh;//发票请求流水号

    private String order_no;//订单号

    private String status;//发票状态

    private String attach;//附加数据

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

    public String getServer_cert() {
        return server_cert;
    }

    public void setServer_cert(String server_cert) {
        this.server_cert = server_cert;
    }

    public String getServer_sign() {
        return server_sign;
    }

    public void setServer_sign(String server_sign) {
        this.server_sign = server_sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getMerchant_code() {
        return merchant_code;
    }

    public void setMerchant_code(String merchant_code) {
        this.merchant_code = merchant_code;
    }

    public String getApp_no() {
        return app_no;
    }

    public void setApp_no(String app_no) {
        this.app_no = app_no;
    }

    public String getFpqqlsh() {
        return fpqqlsh;
    }

    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
