package com.core.constant;

/**
 * Created by sdyang on 2016/10/7.
 */
public enum EinvoiceStatus {

    RECEVICE_SUCCESS("开票数据接收成功", 1), RECEVICE_FAIL("开票数据接收失败", 2),
    GENXML_SUCCESS("生成开票XML成功", 3), GENXML_FAIL("生成开票XML失败", 4),
    BILLING_SUCCESS("开票成功",5),BILLING_FIAL("开票失败",6),
    GENPDF_SUCCESS("生成PDF成功",7),GENPDF_FIAL("生成PDF失败",8),
    SIGN_SUCCESS("电子签名成功",9),SIGN_FAIL("电子签名失败",10),
    RESULT_RETURN("结果已回传",11),CANCEL("蓝票已冲红",12);

    private String name;
    private int index;

    // 构造方法
    private EinvoiceStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (EinvoiceStatus c : EinvoiceStatus.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
