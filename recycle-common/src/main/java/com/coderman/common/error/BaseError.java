package com.coderman.common.error;

/**
 * 自定义的錯誤描述枚举类需实现該接口
 * @Author zhangyukang
 * @Date 2020/3/1 14:49
 * @Version 1.0
 **/
public interface BaseError {

    /**
     * 获取錯誤碼
     * @return
     */
    int getErrorCode();

    /**
     * 获取錯誤信息
     * @return
     */
    String getErrorMsg();


    /**
     * 设置錯誤信息
     * @param message
     * @return
     */
    BaseError setErrorMsg(String message);
}
