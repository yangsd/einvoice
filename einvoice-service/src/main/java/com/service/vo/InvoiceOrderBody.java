package com.service.vo;

/**
 * Created by sdyang on 2016/10/10.
 */
public class InvoiceOrderBody {

    private int row_no;//项目行号

    private String fpqqlsh;//发票请求流水号

    private String fphxz;//发票行性质

    private String xmmc;//项目名称

    private String dw;//计量单位

    private String ggxh;//规格型号

    private int xmsl;//项目数量

    private double xmdj;//项目单价

    private double xmje;//项目金额

    private double sl;//税率

    private double se;//税额

    //-------------------------------------------------//

    public int getRow_no() {
        return row_no;
    }

    public void setRow_no(int row_no) {
        this.row_no = row_no;
    }

    public String getFpqqlsh() {
        return fpqqlsh;
    }

    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh;
    }

    public String getFphxz() {
        return fphxz;
    }

    public void setFphxz(String fphxz) {
        this.fphxz = fphxz;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh;
    }

    public int getXmsl() {
        return xmsl;
    }

    public void setXmsl(int xmsl) {
        this.xmsl = xmsl;
    }

    public double getXmdj() {
        return xmdj;
    }

    public void setXmdj(double xmdj) {
        this.xmdj = xmdj;
    }

    public double getXmje() {
        return xmje;
    }

    public void setXmje(double xmje) {
        this.xmje = xmje;
    }

    public double getSl() {
        return sl;
    }

    public void setSl(double sl) {
        this.sl = sl;
    }

    public double getSe() {
        return se;
    }

    public void setSe(double se) {
        this.se = se;
    }
}
