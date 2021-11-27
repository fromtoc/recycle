package com.coderman.business.service;


import com.coderman.common.model.business.InputElement;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
public interface InputElementService {

    /**
     * 新增單價
     * @param inputElements
     */
    int add(List<InputElement> inputElements);
}
