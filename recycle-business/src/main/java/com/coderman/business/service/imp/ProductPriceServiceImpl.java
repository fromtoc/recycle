package com.coderman.business.service.imp;

import com.coderman.business.converter.ProductConverter;
import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.mapper.ProductPriceMapper;
import com.coderman.business.mapper.ProductStockMapper;
import com.coderman.business.service.ProductCategoryService;
import com.coderman.business.service.ProductPriceService;
import com.coderman.business.service.ProductService;
import com.coderman.common.error.BusinessCodeEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.business.ProductPrice;
import com.coderman.common.model.system.Dictionary;
import com.coderman.common.service.DictionaryService;
import com.coderman.common.vo.business.ProductPriceVO;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.system.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    ProductPriceMapper productPriceMapper;

    @Override
    public void add(ProductPrice productPrice) {
        productPriceMapper.insert(productPrice);
    }

    @Override
    public PageVO<ProductPriceVO> findProductPriceList(Integer pageNum, Integer pageSize, ProductPrice productPrice) {
        PageHelper.startPage(pageNum, pageSize);
        String name = productPrice.getName();
        Example o = new Example(Dictionary.class);
        Example.Criteria criteria = o.createCriteria();
        if (name != null && !"".equals(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        List<ProductPrice> productPrices = productPriceMapper.selectByExample(o);
        List<ProductPriceVO> productPriceVOList = new ArrayList<>();
        for (ProductPrice price:productPrices) {
            ProductPriceVO priceVO = new ProductPriceVO();
            BeanUtils.copyProperties(price, priceVO);
            productPriceVOList.add(priceVO);
        }

        PageInfo<ProductPriceVO> productPricePageInfo = new PageInfo<>(productPriceVOList);
        return new PageVO<ProductPriceVO>(productPricePageInfo.getTotal(), productPriceVOList);
    }

    @Override
    public ProductPrice edit(Long id) {
        return productPriceMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Long id, ProductPriceVO productPriceVO) {
        ProductPrice originProductPrice = productPriceMapper.selectByPrimaryKey(id);
        productPriceMapper.updateByPrimaryKeySelective(originProductPrice);
    }
}
