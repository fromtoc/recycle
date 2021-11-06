package com.coderman.business.service;


import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.business.ProductPrice;
import com.coderman.common.vo.business.ProductPriceVO;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.system.PageVO;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
public interface ProductPriceService {

    /**
     * 添加單價
     * @param productPrice
     */
    void add(ProductPrice productPrice);


    /**
     * 單價列表
     * @param pageNum
     * @param pageSize
     * @param productPrice
     * @return
     */
    PageVO<ProductPriceVO> findProductPriceList(Integer pageNum, Integer pageSize, ProductPrice productPrice);


    /**
     * 編辑商品
     * @param id
     * @return
     */
    ProductPrice edit(Long id);

    /**
     * 更新單價
     * @param id
     * @param productPriceVO
     */
    void update(Long id, ProductPriceVO productPriceVO);


}
