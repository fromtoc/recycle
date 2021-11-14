package com.coderman.business.service;


import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemException;
import com.coderman.common.vo.business.ProductCategoryTreeNodeVO;
import com.coderman.common.vo.business.ProductCategoryVO;
import com.coderman.common.vo.system.PageVO;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
public interface ProductCategoryService {

    /**
     * 新增物資類别
     * @param ProductCategoryVO
     */
    void add(ProductCategoryVO ProductCategoryVO);


    /**
     * 部門列表
     * @param pageNum
     * @param pageSize
     * @param ProductCategoryVO
     * @return
     */
    PageVO<ProductCategoryVO> findProductCategoryList(Integer pageNum, Integer pageSize, ProductCategoryVO ProductCategoryVO);


    /**
     * 編辑物資類别
     * @param id
     * @return
     */
    ProductCategoryVO edit(Long id);

    /**
     * 查詢商品類别名稱
     * @param id
     * @return
     */
    String getName(Long id);

    /**
     * 更新物資類别
     * @param id
     * @param ProductCategoryVO
     */
    void update(Long id, ProductCategoryVO ProductCategoryVO);

    /**
     * 删除物資類别
     * @param id
     */
    void delete(Long id) throws BusinessException;

    /**
     * 查询所物資類别
     * @return
     */
    List<ProductCategoryVO> findAll();

    /**
     * 分類树形
     * @return
     */
    PageVO<ProductCategoryTreeNodeVO> categoryTree(Integer pageNum, Integer pageSize);

    /**
     * 获取父级分類（2级树）
     * @return
     */
    List<ProductCategoryTreeNodeVO> getParentCategoryTree();

    void updateStatus(Long id, Boolean status) throws SystemException;

}
