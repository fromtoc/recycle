package com.coderman.business.service;


import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.system.Department;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.system.PageVO;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
public interface ProductService {

    /**
     * 新增商品
     * @param productVO
     */
    void add(ProductVO productVO);


    /**
     * 商品列表
     * @param pageNum
     * @param pageSize
     * @param productVO
     * @return
     */
    PageVO<ProductVO> findProductList(Integer pageNum, Integer pageSize, ProductVO productVO);


    /**
     * 編辑商品
     * @param id
     * @return
     */
    ProductVO edit(Long id);

    /**
     * 更新商品
     * @param id
     * @param productVO
     */
    void update(Long id, ProductVO productVO);

    /**
     * 删除商品
     * @param id
     */
    void delete(Long id) throws BusinessException;

    /**
     * 库存列表
     * @param pageNum
     * @param pageSize
     * @param productVO
     * @return
     */
    PageVO<ProductStockVO> findProductStocks(Integer pageNum, Integer pageSize, ProductVO productVO);

    /**
     * 所有库存信息
     * @return
     */
    List<ProductStockVO> findAllStocks(Integer pageNum, Integer pageSize, ProductVO productVO);

    /**
     * 移入回收站
     * @param id
     */
    void remove(Long id) throws BusinessException;

    /**
     * 从回收站恢复数據
     * @param id
     */
    void back(Long id) throws BusinessException;

    /**
     * 物資新增审核
     * @param id
     */
    void publish(Long id) throws BusinessException;

    /**
     * 更新狀態
     *
     * @param id
     * @param status
     */
    void updateStatus(Long id, Boolean status) throws SystemException;

    /**
     * 更新狀態
     *
     * @param ids
     * @param costCenterId
     */
    void updateCostCenter(List<Long> ids, Long costCenterId) throws SystemException;

    List<Product> findProductByProductName(String name);

    /**
     * 全部廢棄物
     * @return
     */
    List<Product> findAll();


}
