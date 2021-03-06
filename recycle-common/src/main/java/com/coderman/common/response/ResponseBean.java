package com.coderman.common.response;

import lombok.Data;

@Data
public class ResponseBean<T> {

    /** 200:操作成功  -1：操作失敗**/

    // http 狀態碼
    private boolean success;

    // 返回的数據
    private T data;

    public static <T> ResponseBean<T> success(T data) {
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setSuccess(true);
        responseBean.setData(data);
        return responseBean;
    }


    public static <T> ResponseBean<T> error(T errorData) {
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setSuccess(false);
        responseBean.setData(errorData);
        return responseBean;
    }

    public static <T> ResponseBean<T> success() {
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setSuccess(true);
        return responseBean;
    }
}
