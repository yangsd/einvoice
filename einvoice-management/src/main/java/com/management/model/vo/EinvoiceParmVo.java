package com.management.model.vo;

import java.util.Date;

/**
 * Created by sdyang on 2016/10/24.
 */
public class EinvoiceParmVo {

    private String fpqqlsh;

    private String status;

    private String xsf_mc;

    private String order_no;

    private Date submit_begin;

    private Date submit_end;

    public String getXsf_mc() {
        return xsf_mc;
    }

    public void setXsf_mc(String xsf_mc) {
        this.xsf_mc = xsf_mc;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSubmit_begin() {
        return submit_begin;
    }

    public void setSubmit_begin(Date submit_begin) {
        this.submit_begin = submit_begin;
    }

    public Date getSubmit_end() {
        return submit_end;
    }

    public void setSubmit_end(Date submit_end) {
        this.submit_end = submit_end;
    }
}
