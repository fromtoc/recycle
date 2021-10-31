package com.coderman.common.service.impl;


import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.error.SystemException;
import com.coderman.common.mapper.DictionaryMapper;
import com.coderman.common.model.system.*;
import com.coderman.common.vo.system.PageVO;
import com.coderman.common.service.DictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    public PageVO<Dictionary> findDictionaryList(Integer pageNum, Integer pageSize, Dictionary dictionary) {
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
        criteria.andEqualTo("status", 1);
        List<Dictionary> dictionaries = dictionaryMapper.selectByExample(o);

        PageInfo<Dictionary> dictionaryPageInfo = new PageInfo<>(dictionaries);
        return new PageVO<Dictionary>(dictionaryPageInfo.getTotal(), dictionaries);
    }

    @Override
    public void add(Dictionary dictionary) {
        dictionaryMapper.insert(dictionary);
    }

    @Override
    public Dictionary selectByPrimaryKey(Long id) throws SystemException {
        return dictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Dictionary> selectByType(Integer type) throws SystemException {
        Dictionary query = new Dictionary();
        query.setType(type);
        return dictionaryMapper.select(query);
    }

    @Override
    public void update(Long id, Dictionary dictionary) throws SystemException {
        Dictionary originDictionary = dictionaryMapper.selectByPrimaryKey(id);
        if(originDictionary==null){
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR,"要更新的資料辭典不存在");
        }
        dictionaryMapper.updateByPrimaryKeySelective(dictionary);
    }

    @Override
    public void delete(Long id) throws SystemException {
        Dictionary originDictionary = dictionaryMapper.selectByPrimaryKey(id);
        if(originDictionary==null){
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR,"要删除的資料辭典不存在");
        }
        originDictionary.setStatus(0);
        dictionaryMapper.updateByPrimaryKeySelective(originDictionary);
//        dictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Dictionary> findAll() {
        return dictionaryMapper.selectAll();
    }
}
