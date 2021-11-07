package com.coderman.common.enums.buisiness;

/**
 * 業務用戶类型
 * @Author zhangyukang
 * @Date 2020/3/15 18:37
 * @Version 1.0
 **/
public enum BizUserTypeEnum {

    DEAN("部門主任");
    private String val;

    BizUserTypeEnum(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
