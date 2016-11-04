package com.core.constant;

/**
 * Created by sdyang on 2016/10/16.
 */
public enum EinvoiceType {

    BLUE("蓝票", 0), RED("红票", 1);

    private String name;
    private int index;

    // 构造方法
    private EinvoiceType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (EinvoiceType c : EinvoiceType.values()) {
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
