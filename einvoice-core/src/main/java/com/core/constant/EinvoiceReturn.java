package com.core.constant;

/**
 * Created by sdyang on 2016/10/16.
 */
public enum EinvoiceReturn {

    SUCCESS("0000", "success"), FAIL("1111", "fail"),CHECK_SIGN_FAIL("2222","验签失败"),OTHER_ERROR("3333",""),MERCHANT_NOT_EXSIT("4444","该商户不存在"),ORDER_NO_NOT_EXSIT("5555","该订单不存在");

    private String code;
    private String message;

    private EinvoiceReturn(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 普通方法
    public static String getMessage(String code) {
        for (EinvoiceReturn c : EinvoiceReturn.values()) {
            if (c.getCode() == code) {
                return c.message;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
