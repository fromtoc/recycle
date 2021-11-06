package com.coderman.system.service;


import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Department;
import com.coderman.common.vo.system.DeanVO;
import com.coderman.common.vo.system.DepartmentVO;
import com.coderman.common.vo.system.PageVO;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/15 14:12
 * @Version 1.0
 **/
public interface DepartmentService {
    /**
     * 部門列表
     * @param pageNum
     * @param pageSize
     * @param departmentVO
     * @return
     */
    PageVO<DepartmentVO> findDepartmentList(Integer pageNum, Integer pageSize, DepartmentVO departmentVO);

    /**
     * 查询所有部門主任
     * @return
     */
    List<DeanVO> findDeanList();

    /**
     * 添加院部門
     * @param departmentVO
     */
    void add(DepartmentVO departmentVO);

    /**
     * 編辑院部門
     * @param id
     * @return
     */
    DepartmentVO edit(Long id) throws SystemException;

    /**
     * 更新院部門
     * @param id
     * @param departmentVO
     */
    void update(Long id, DepartmentVO departmentVO) throws SystemException;

    /**
     * 删除院部門
     * @param id
     */
    void delete(Long id) throws SystemException;

    /**
     * 所有部門
     * @return
     */
    List<DepartmentVO> findAllVO();


    /**
     * 全部部門
     * @return
     */
    List<Department> findAll();

}
