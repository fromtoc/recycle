package com.coderman.business.service;


import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.business.ProductPrice;
import com.coderman.common.model.business.Weight;
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
     * 新增單價
     * @param productPriceVO
     */
    int add(ProductPriceVO productPriceVO);


    /**
     * 單價列表
     * @param pageNum
     * @param pageSize
     * @param productPriceVO
     * @return
     */
    PageVO<ProductPriceVO> findProductPriceList(Integer pageNum, Integer pageSize, ProductPriceVO productPriceVO);


    /**
     * 編辑商品
     * @param id
     * @return
     */
    ProductPriceVO edit(Long id);

    /**
     * 更新單價
     * @param id
     * @param productPriceVO
     */
    void update(Long id, ProductPriceVO productPriceVO);
    /**
     * 全部單價
     * @return
     */
    List<ProductPrice> findAll();

}
