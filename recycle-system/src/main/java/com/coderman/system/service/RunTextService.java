package com.coderman.system.service;


import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Dictionary;
import com.coderman.common.model.system.RunText;
import com.coderman.common.vo.system.PageVO;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/15 14:12
 * @Version 1.0
 **/
public interface RunTextService {
    /**
     * 跑馬燈列表
     * @param pageNum
     * @param pageSize
     * @param runText
     * @return
     */
    PageVO<RunText> findRunTextList(Integer pageNum, Integer pageSize, RunText runText);

    /**
     * 添加跑馬燈
     * @param runText
     */
    void add(RunText runText);

    /**
     * 查詢跑馬燈
     * @param id
     * @return
     */
    RunText selectByPrimaryKey(Long id) throws SystemException;

    /**
     * 更新跑馬燈
     * @param id
     * @param runText
     */
    void update(Long id, RunText runText) throws SystemException;

    /**
     * 全部跑馬燈
     * @return
     */
    List<RunText> findAll();

    void updateStatus(Long id, Boolean status) throws SystemException;

}
