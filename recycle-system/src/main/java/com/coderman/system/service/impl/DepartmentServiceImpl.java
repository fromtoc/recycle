package com.coderman.system.service.impl;


import com.coderman.common.enums.buisiness.BizUserTypeEnum;
import com.coderman.common.enums.system.UserStatusEnum;
import com.coderman.common.enums.system.UserTypeEnum;
import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.error.SystemException;
import com.coderman.common.mapper.DictionaryMapper;
import com.coderman.common.model.business.Supplier;
import com.coderman.common.model.system.*;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.vo.system.DeanVO;
import com.coderman.common.vo.system.DepartmentVO;
import com.coderman.common.vo.system.PageVO;
import com.coderman.system.converter.DepartmentConverter;
import com.coderman.system.mapper.DepartmentMapper;
import com.coderman.system.mapper.RoleMapper;
import com.coderman.system.mapper.UserMapper;
import com.coderman.system.mapper.UserRoleMapper;
import com.coderman.system.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/15 14:15
 * @Version 1.0
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private DictionaryMapper dictionaryMapper;

    /**
     * 系别列表
     *
     * @param pageNum
     * @param pageSize
     * @param departmentVO
     * @return
     */
    @Override
    public PageVO<DepartmentVO> findDepartmentList(Integer pageNum, Integer pageSize, DepartmentVO departmentVO) {
        PageHelper.startPage(pageNum, pageSize);
        Example o = new Example(Department.class);
        Example.Criteria criteria = o.createCriteria();
        if (departmentVO.getName() != null && !"".equals(departmentVO.getName())) {
            criteria.orLike("nickname", "%" + departmentVO.getName() + "%");
            criteria.orLike("name", "%" + departmentVO.getName() + "%");
        }
        o.and(criteria);
        List<Department> departments = departmentMapper.selectByExample(o);
        List<DepartmentVO> departmentVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(departments)) {
            for (Department department : departments) {
                DepartmentVO d = new DepartmentVO();
                BeanUtils.copyProperties(department, d);
                Example o1 = new Example(User.class);
                o1.createCriteria().andEqualTo("departmentId", department.getId())
                        .andNotEqualTo("type", UserTypeEnum.SYSTEM_ADMIN.getTypeCode());
                String typeName = dictionaryMapper.selectByPrimaryKey(d.getTypeId()).getValue();
                d.setTypeCodeName(typeName);
                d.setTotal(userMapper.selectCountByExample(o1));
                d.setStatus(department.getStatus() == 0 ? true : false);
                d.setFood(department.getFood() == 1 ? true : false);
                departmentVOS.add(d);
            }
        }
        PageInfo<Department> info = new PageInfo<>(departments);
        return new PageVO<>(info.getTotal(), departmentVOS);
    }

    /**
     * 查找所有系主任
     *
     * @return
     */
    @Override
    public List<DeanVO> findDeanList() {
        Example o = new Example(Role.class);
        o.createCriteria().andEqualTo("roleName", BizUserTypeEnum.DEAN.getVal());
        List<Role> roles = roleMapper.selectByExample(o);
        List<DeanVO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roles)) {
            Role role = roles.get(0);
            Example o1 = new Example(UserRole.class);
            o1.createCriteria().andEqualTo("roleId", role.getId());
            List<UserRole> userRoleList = userRoleMapper.selectByExample(o1);
            if (!CollectionUtils.isEmpty(userRoleList)) {
                //存放所有系主任的id
                List<Long> userIds = new ArrayList<>();
                for (UserRole userRole : userRoleList) {
                    userIds.add(userRole.getUserId());
                }
                if (userIds.size() > 0) {
                    for (Long userId : userIds) {
                        User user = userMapper.selectByPrimaryKey(userId);
                        //所有可用的
                        if (user != null && user.getStatus() == UserStatusEnum.AVAILABLE.getStatusCode()) {
                            DeanVO deanVO = new DeanVO();
                            deanVO.setName(user.getUsername());
                            deanVO.setId(user.getId());
                            list.add(deanVO);
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * 新增院系
     *
     * @param departmentVO
     */
    @Override
    public void add(DepartmentVO departmentVO) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentVO, department);
        Long typeId = departmentVO.getTypeId();
        String code = dictionaryMapper.selectByPrimaryKey(typeId).getCode();
        Long newNumber = 1L;
        Example o = new Example(Department.class);
        Example.Criteria criteria = o.createCriteria();
        criteria.andEqualTo("typeId", typeId);
        o.setOrderByClause("number desc");
        List<Department> departments = departmentMapper.selectByExample(o);
        if (!CollectionUtils.isEmpty(departments)) {
            Long number = departments.get(0).getNumber();
            newNumber = number + 1;
        }
        String pattern = "%03d";
        String str = String.format(pattern, newNumber);

        department.setTypeCode(code);
        department.setNumber(newNumber);
        department.setTypeNumber(code + str);
        department.setCreateTime(new Date());
        department.setModifiedTime(new Date());
        department.setStatus(1);
        department.setFood(0);
        department.setLoadTime(new Date());
        departmentMapper.insert(department);
    }

    /**
     * 編辑院系
     *
     * @param id
     * @return
     */
    @Override
    public DepartmentVO edit(Long id) throws SystemException {
        Department department = departmentMapper.selectByPrimaryKey(id);
        if (department == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "編辑的部門不存在");
        }
        return DepartmentConverter.converterToDepartmentVO(department);
    }

    /**
     * 更新部門
     *
     * @param id
     * @param departmentVO
     */
    @Override
    public void update(Long id, DepartmentVO departmentVO) throws SystemException {
        Department dbDepartment = departmentMapper.selectByPrimaryKey(id);
        if (dbDepartment == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "要更新的部門不存在");
        }
        Department department = new Department();
        BeanUtils.copyProperties(departmentVO, department);
        department.setId(id);
        department.setModifiedTime(new Date());
        department.setLoadTime(new Date());
        departmentMapper.updateByPrimaryKeySelective(department);
    }

    /**
     * 删除部門信息
     *
     * @param id
     */
    @Override
    public void delete(Long id) throws SystemException {
        Department department = departmentMapper.selectByPrimaryKey(id);
        if (department == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "要删除的部門不存在");
        }
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<DepartmentVO> findAllVO() {
        List<Department> departments = departmentMapper.selectAll();
        //转vo
        List<DepartmentVO> departmentVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(departments)) {
            for (Department department : departments) {
                if (department.getStatus()!=0) {
                    DepartmentVO d = new DepartmentVO();
                    BeanUtils.copyProperties(department, d);
                    Example o = new Example(User.class);
                    o.createCriteria().andEqualTo("departmentId", department.getId())
                            .andNotEqualTo("type", 0);
                    d.setTotal(userMapper.selectCountByExample(o));
                    d.setStatus(department.getStatus() == 0 ? true : false);
                    d.setFood(department.getFood() == 1 ? true : false);
                    departmentVOS.add(d);
                }
            }
        }
        return departmentVOS;
    }

    @Override
    public List<DepartmentVO> findAll(DepartmentVO departmentVO) {
        Example o = new Example(Department.class);
        Example.Criteria criteria = o.createCriteria();
        if (departmentVO.getName() != null && !"".equals(departmentVO.getName())) {
            criteria.orLike("nickname", "%" + departmentVO.getName() + "%");
            criteria.orLike("name", "%" + departmentVO.getName() + "%");
        }
        o.and(criteria);
        List<Department> departments = departmentMapper.selectByExample(o);
        List<DepartmentVO> voList = new ArrayList<>();
        departments.stream().forEach(d -> {
            DepartmentVO vo = new DepartmentVO();
            BeanUtils.copyProperties(d, vo);
            String typeName = dictionaryMapper.selectByPrimaryKey(vo.getTypeId()).getValue();
            vo.setTypeCodeName(typeName);
            vo.setFood(d.getFood() == 1 ? true : false);
            vo.setStatus(d.getStatus() == 1 ? false : true);
            voList.add(vo);
        });
        return voList;
    }

    @Override
    public void updateStatus(Long id, Boolean status) throws SystemException {
        Department department = departmentMapper.selectByPrimaryKey(id);
        if (department == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "要更新狀態的公司不存在");
        }
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (department.getId().equals(activeUser.getUser().getId())) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "無法改變當前公司狀態");
        } else {
            Department t = new Department();
            t.setId(id);
            t.setStatus(status ? UserStatusEnum.DISABLE.getStatusCode() :
                    UserStatusEnum.AVAILABLE.getStatusCode());
            t.setLoadTime(new Date());
            departmentMapper.updateByPrimaryKeySelective(t);
        }
    }

    @Override
    public void updateFood(Long id, Boolean food) throws SystemException {
        Department department = departmentMapper.selectByPrimaryKey(id);
        if (department == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "要更新廚餘標記的公司不存在");
        }
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (department.getId().equals(activeUser.getUser().getId())) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "無法改變當前公司廚餘標記");
        } else {
            Department t = new Department();
            t.setId(id);
            t.setFood(food ? UserStatusEnum.AVAILABLE.getStatusCode() :
                    UserStatusEnum.DISABLE.getStatusCode());
            t.setLoadTime(new Date());
            departmentMapper.updateByPrimaryKeySelective(t);
        }
    }
}
