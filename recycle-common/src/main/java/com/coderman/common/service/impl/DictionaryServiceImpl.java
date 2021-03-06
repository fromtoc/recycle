package com.coderman.common.service.impl;


import com.coderman.common.enums.system.UserStatusEnum;
import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.error.SystemException;
import com.coderman.common.mapper.DictionaryMapper;
import com.coderman.common.model.system.*;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.vo.system.DictionaryVO;
import com.coderman.common.vo.system.PageVO;
import com.coderman.common.service.DictionaryService;
import com.coderman.common.vo.system.RunTextVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public PageVO<DictionaryVO> findDictionaryList(Integer pageNum, Integer pageSize, Dictionary dictionary) {
        PageHelper.startPage(pageNum, pageSize);
        String code = dictionary.getCode();
        String value = dictionary.getValue();
        Integer type = dictionary.getType();
        Example o = new Example(Dictionary.class);
        Example.Criteria criteria = o.createCriteria();
        if (type != null && !"".equals(type)) {
            criteria.andEqualTo("type", type);
        }
        if (code != null && !"".equals(code)) {
            criteria.andEqualTo("code", code);
        }
        if (value != null && !"".equals(value)) {
            criteria.andLike("value", "%" + value + "%");
        }
        List<Dictionary> dictionaries = dictionaryMapper.selectByExample(o);
        List<DictionaryVO> voList = new ArrayList<>();
        dictionaries.stream().forEach(d-> {
            DictionaryVO vo = new DictionaryVO();
            BeanUtils.copyProperties(d, vo);
            vo.setStatus(d.getStatus()==1? false : true);
            voList.add(vo);
        });

        PageInfo<Dictionary> dictionaryPageInfo = new PageInfo<>(dictionaries);
        return new PageVO<DictionaryVO>(dictionaryPageInfo.getTotal(), voList);
    }

    @Override
    public void add(Dictionary dictionary) {
        dictionary.setLoadTime(new Date());
        dictionaryMapper.insert(dictionary);
    }

    @Override
    public Dictionary selectByPrimaryKey(Long id) throws SystemException {
        return dictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Dictionary> selectByType(Integer type, Integer status) throws SystemException {
        Dictionary query = new Dictionary();
        query.setType(type);
        if (status != null) {
            query.setStatus(status);
        }
        return dictionaryMapper.select(query);
    }

    @Override
    public void update(Long id, Dictionary dictionary) throws SystemException {
        Dictionary originDictionary = dictionaryMapper.selectByPrimaryKey(id);
        if (originDictionary == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "?????????????????????????????????");
        }
        dictionary.setLoadTime(new Date());
        dictionaryMapper.updateByPrimaryKeySelective(dictionary);
    }

    @Override
    public void delete(Long id) throws SystemException {
        Dictionary originDictionary = dictionaryMapper.selectByPrimaryKey(id);
        if (originDictionary == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "?????????????????????????????????");
        }
        originDictionary.setStatus(0);
        originDictionary.setLoadTime(new Date());
        dictionaryMapper.updateByPrimaryKeySelective(originDictionary);
//        dictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Dictionary> findAll() {
        return dictionaryMapper.selectAll();
    }

    @Override
    public void updateStatus(Long id, Boolean status) throws SystemException {
        Dictionary d = new Dictionary();
        d.setId(id);
        d.setStatus(status ? UserStatusEnum.DISABLE.getStatusCode() :
                UserStatusEnum.AVAILABLE.getStatusCode());
        d.setLoadTime(new Date());
        dictionaryMapper.updateByPrimaryKeySelective(d);
    }
}
