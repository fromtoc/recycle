package com.coderman.common.error;

import lombok.Getter;

/**
 *
 * 業務錯誤碼：返回结果的狀態碼
 *
 * 如果想要代碼更具维护性一點,可以定义不同种类的錯誤碼,都实现 BaseCodeInterface
 * @Author zhangyukang
 * @Date 2020/3/1 14:51
 * @Version 1.0
 **/
@Getter
public enum  BusinessCodeEnum implements BaseError {

    //通用的異常以0000开头
    PARAMETER_ERROR(00001,"参数不合法"),

    // 数據操作錯誤定义
    BODY_NOT_MATCH(400,"請求的数據格式不符!"),
    SIGNATURE_NOT_MATCH(401,"請求的数字签名不匹配!"),
    NOT_FOUND(404, "未找到該资源!"),
    INTERNAL_SERVER_ERROR(500, "服務器内部錯誤!"),
    SERVER_BUSY(503,"服務器正忙，請稍后再试!"),
    //用戶相关：10000**
    USER_ACCOUNT_NOT_FOUND(10001, "账號不存在!"),
    DoNotAllowToDisableTheCurrentUser(10002,"不允许禁用当前用戶"),
    //業務異常
    PRODUCT_IS_REMOVE(30001,"物资已移入回收站"),
    PRODUCT_NOT_FOUND(30002,"物资找不到"),
    PRODUCT_WAIT_PASS(30003,"物资等待审核"),
    PRODUCT_STATUS_ERROR(30004,"物资狀態錯誤"),
    PRODUCT_IN_STOCK_NUMBER_ERROR(30005,"物资入库数量非法"),
    PRODUCT_OUT_STOCK_NUMBER_ERROR(30008,"物资发放数量非法"),
    PRODUCT_IN_STOCK_EMPTY(30006,"物资入库不能为空"),
    PRODUCT_OUT_STOCK_EMPTY(30007,"物资发放不能为空"),
    PRODUCT_STOCK_ERROR(30009,"物资库存不足");
    /** 錯誤碼 */
    private int errorCode;

    /** 錯誤描述 */
    private String errorMsg;

    BusinessCodeEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public BaseError setErrorMsg(String errorMsg) {
        this.errorMsg=errorMsg;
        return this;
    }

}
