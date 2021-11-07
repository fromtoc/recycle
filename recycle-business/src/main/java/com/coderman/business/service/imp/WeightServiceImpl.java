package com.coderman.business.service.imp;

import com.coderman.business.converter.ProductConverter;
import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.mapper.ProductStockMapper;
import com.coderman.business.mapper.WeightMapper;
import com.coderman.business.service.ProductCategoryService;
import com.coderman.business.service.ProductService;
import com.coderman.business.service.WeightService;
import com.coderman.common.error.BusinessCodeEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.business.Weight;
import com.coderman.common.service.DictionaryService;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.business.WeightVO;
import com.coderman.common.vo.system.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:19
 * @Version 1.0
 **/
@Service
@Transactional
public class WeightServiceImpl implements WeightService {

    @Autowired
    WeightMapper weightMapper;


    @Override
    public int add(Weight weight) {
        weight.setStatus(1);
        weight.setCreateTime(new Date());
        return weightMapper.insert(weight);
    }

    @Override
    public PageVO<WeightVO> findWeightList(Integer pageNum, Integer pageSize, WeightVO weightVO) {
        return null;
    }

    @Override
    public WeightVO edit(Long id) {
        return null;
    }

    @Override
    public void update(Long id, WeightVO weightVO) {

    }

    @Override
    public void delete(Long id) throws BusinessException {

    }

    @Override
    public void updateStatus(Long id, Boolean status) throws SystemException {

    }
}
