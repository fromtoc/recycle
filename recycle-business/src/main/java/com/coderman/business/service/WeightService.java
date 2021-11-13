package com.coderman.business.service;


import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.business.Weight;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.business.WeightVO;
import com.coderman.common.vo.system.PageVO;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
public interface WeightService {

    /**
     * 新增秤重單
     * @param weight
     */
    int add(Weight weight);

    /**
     * 新增秤重單
     * @param weightVO
     */
    int addVO(WeightVO weightVO) throws SystemException;


    /**
     * 商品列表
     * @param pageNum
     * @param pageSize
     * @param weightVO
     * @return
     */
    PageVO<WeightVO> findWeightList(Integer pageNum, Integer pageSize, WeightVO weightVO);


    /**
     * 編辑秤重單
     * @param id
     * @return
     */
    WeightVO edit(Long id);

    /**
     * 更新秤重單
     * @param id
     * @param weightVO
     */
    void update(Long id, WeightVO weightVO) throws SystemException;

    /**
     * 删除秤重單
     * @param id
     */
    void delete(Long id) throws BusinessException;

    /**
     * 更新狀態
     *
     * @param id
     * @param status
     */
    void updateStatus(Long id, Boolean status) throws SystemException;

}
