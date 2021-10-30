package com.coderman.system.service;


import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Department;
import com.coderman.common.model.system.Dictionary;
import com.coderman.common.vo.system.DeanVO;
import com.coderman.common.vo.system.DepartmentVO;
import com.coderman.common.vo.system.PageVO;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/15 14:12
 * @Version 1.0
 **/
public interface DictionaryService {
    /**
     * 資料辭典列表
     * @param pageNum
     * @param pageSize
     * @param dictionary
     * @return
     */
    PageVO<Dictionary> findDictionaryList(Integer pageNum, Integer pageSize, Dictionary dictionary);

    /**
     * 添加資料辭典
     * @param dictionary
     */
    void add(Dictionary dictionary);

    /**
     * 查詢資料辭典
     * @param id
     * @return
     */
    Dictionary selectByPrimaryKey(Long id) throws SystemException;

    /**
     * 查詢資料辭典by類型
     * @param type
     * @return
     */
    List<Dictionary> selectByType(Integer type) throws SystemException;

    /**
     * 更新資料辭典
     * @param id
     * @param dictionary
     */
    void update(Long id, Dictionary dictionary) throws SystemException;

    /**
     * 删除資料辭典
     * @param id
     */
    void delete(Long id) throws SystemException;

    /**
     * 全部資料辭典
     * @return
     */
    List<Dictionary> findAll();

}
