package com.coderman.business.service;


import com.coderman.common.model.business.InputElement;
import com.coderman.common.vo.business.ProductPriceUploadVO;
import com.coderman.common.vo.business.ProductPriceVO;

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
    int batchAdd(List<InputElement> inputElements);

    List<InputElement> checkSame(List<InputElement> inputElementList);

    int recover(List<InputElement> inputElementList);


}
