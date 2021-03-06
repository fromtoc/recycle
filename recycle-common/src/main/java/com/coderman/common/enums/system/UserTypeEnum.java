package com.coderman.common.enums.system;

/**
 * 用戶類型
 * @Author zhangyukang
 * @Date 2020/5/29 12:25
 * @Version 1.0
 **/
public enum UserTypeEnum {

    SYSTEM_ADMIN(0),//系统管理員admin

    SYSTEM_USER(1);//系统的普通用戶

    private int typeCode;

    UserTypeEnum(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
}
