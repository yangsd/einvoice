package com.management.model;


import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;
import java.util.List;

/**
 * Created by sdyang on 2016/10/6.
 */
@TableName("einvoice_head")
public class EinvoiceHead {

    //------------------------------------------------发票订单信息-----------------------------//
    @TableId(type = IdType.AUTO)
    private Long pk_einvoicehead;

    private String fpqqlsh;//发票请求流水号

    private String kplx;//开票类型：0蓝字发票、1红字发票


    private String xsf_nsrsbh;//销售方纳税人识别号


    private String xsf_mc;//销售方名称


    private String xsf_dzdh;//销售方地址电话


    private String xsf_yhzh;//销售方银行帐号


    private String gmf_nsrsbh;//购买方纳税人识别号


    private String gmf_mc;//购买方名称


    private String gmf_dzdh;//购买方地址电话


    private String gmf_yhzh;//购买方银行帐号


    private String kpr;//开票人


    private String skr;//收款人


    private String fhr;//复核人


    private String yfp_dm;//原发票代码，红字发票时必须


    private String yfp_hm;//原发票号码，红字发票时必须

//    @Column(columnDefinition = "double(10,2)",nullable = false)

    private double jshj;//价税合计，单位元，2位小数

//    @Column(columnDefinition = "double(10,2)",nullable = false)

    private double hjje;//合计金额

//    @Column(columnDefinition = "double(10,2)",nullable = false)

    private double hjse;//合计税额


    private String bz;

    //--------------------------------------税控机返回开票结果---------------------------//

    private String jqbh;//税控设备编号


    private String fp_dm;//发票代码


    private String fp_hm;//发票号码


    private Date kprq;//开票日期，格式YYYYMMDDHHMMSS


    private String fpmw;//发票密文


    private String jym;//发票校验码


    private String ewm;//二维码


    private String bz_return;//开票结果备注信息


    private String returncode;//返回代码，0000成功


    private String returnmsg;//返回信息

    //-----------------------------------新增字段信息----------------------------------//

    private Long pk_user;


    private String merchant_code;//商户编码


    private String gmf_mobile;//购买方手机号


    private int status;//发票状态信息


    private Date createtime;//创建时间


    private Date submitdate;//外部系统请求时间


    private String order_no;//外部系统订单号


    private int dr = 0;//逻辑删除标识


    private List<EinvoiceBody> einvoiceBodies;

    //------------------------setter 、 getter -----------------------------//


    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
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

    public String getJqbh() {
        return jqbh;
    }

    public void setJqbh(String jqbh) {
        this.jqbh = jqbh;
    }

    public String getFp_dm() {
        return fp_dm;
    }

    public void setFp_dm(String fp_dm) {
        this.fp_dm = fp_dm;
    }

    public String getFp_hm() {
        return fp_hm;
    }

    public void setFp_hm(String fp_hm) {
        this.fp_hm = fp_hm;
    }

    public Date getKprq() {
        return kprq;
    }

    public void setKprq(Date kprq) {
        this.kprq = kprq;
    }

    public String getFpmw() {
        return fpmw;
    }

    public void setFpmw(String fpmw) {
        this.fpmw = fpmw;
    }

    public String getJym() {
        return jym;
    }

    public void setJym(String jym) {
        this.jym = jym;
    }

    public String getEwm() {
        return ewm;
    }

    public void setEwm(String ewm) {
        this.ewm = ewm;
    }

    public String getBz_return() {
        return bz_return;
    }

    public void setBz_return(String bz_return) {
        this.bz_return = bz_return;
    }

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getReturnmsg() {
        return returnmsg;
    }

    public void setReturnmsg(String returnmsg) {
        this.returnmsg = returnmsg;
    }

    public String getMerchant_code() {
        return merchant_code;
    }

    public void setMerchant_code(String merchant_code) {
        this.merchant_code = merchant_code;
    }

    public String getGmf_mobile() {
        return gmf_mobile;
    }

    public void setGmf_mobile(String gmf_mobile) {
        this.gmf_mobile = gmf_mobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    public Long getPk_einvoicehead() {
        return pk_einvoicehead;
    }

    public void setPk_einvoicehead(Long pk_einvoicehead) {
        this.pk_einvoicehead = pk_einvoicehead;
    }

    public Long getPk_user() {
        return pk_user;
    }

    public void setPk_user(Long pk_user) {
        this.pk_user = pk_user;
    }

    public List<EinvoiceBody> getEinvoiceBodies() {
        return einvoiceBodies;
    }

    public void setEinvoiceBodies(List<EinvoiceBody> einvoiceBodies) {
        this.einvoiceBodies = einvoiceBodies;
    }
}
