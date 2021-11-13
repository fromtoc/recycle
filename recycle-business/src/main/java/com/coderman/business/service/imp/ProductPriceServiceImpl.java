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
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
    private ProductPriceMapper productPriceMapper;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public int add(ProductPriceVO productPriceVO) {
        ProductPrice productPrice = new ProductPrice();
        BeanUtils.copyProperties(productPriceVO, productPrice);
        String productName = productPrice.getName();
        Example o = new Example(Product.class);
        Example.Criteria criteria = o.createCriteria();
        criteria.andEqualTo("name", productName);
        List<Product> products = productMapper.selectByExample(o);
        if (!CollectionUtils.isEmpty(products) && products.size()==1){
            Product product = products.get(0);
            productPrice.setProductId(product.getId());
            productPrice.setOneCategoryId(product.getOneCategoryId());
            productPrice.setTwoCategoryId(product.getTwoCategoryId());
            return productPriceMapper.insert(productPrice);
        }
        return 0;
    }

    @Override
    public PageVO<ProductPriceVO> findProductPriceList(Integer pageNum, Integer pageSize,  ProductPriceVO productPriceVO) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductPrice> products;
        List<ProductPriceVO> productPriceVOs = new ArrayList<>();
        Example o = new Example(ProductPrice.class);
        Example.Criteria criteria = o.createCriteria();
        if (productPriceVO.getName() != null && !"".equals(productPriceVO.getName())) {
            criteria.andLike("name", "%" + productPriceVO.getName() + "%");
        }
        if (productPriceVO.getValidMonth() != null && !"".equals(productPriceVO.getValidMonth())) {
            criteria.andEqualTo("validMonth", productPriceVO.getValidMonth());
        }
        if (productPriceVO.getThreeCategoryId() != null) {
            criteria.andEqualTo("oneCategoryId", productPriceVO.getOneCategoryId())
                    .andEqualTo("twoCategoryId", productPriceVO.getTwoCategoryId())
                    .andEqualTo("threeCategoryId", productPriceVO.getThreeCategoryId());
            products = productPriceMapper.selectByExample(o);
            for (ProductPrice p: products){
                ProductPriceVO vo = new ProductPriceVO();
                BeanUtils.copyProperties(p,vo);
                productPriceVOs.add(vo);
            }
            List<ProductPriceVO> categoryVOSWithName = productPriceVOs.stream()
                    .map(vo -> getCategoryName(vo))
                    .collect(Collectors.toList());
            PageInfo<ProductPriceVO> info = new PageInfo<>(productPriceVOs);
            return new PageVO<>(info.getTotal(), categoryVOSWithName);
        }
        if (productPriceVO.getTwoCategoryId() != null) {
            criteria.andEqualTo("oneCategoryId", productPriceVO.getOneCategoryId())
                    .andEqualTo("twoCategoryId", productPriceVO.getTwoCategoryId());
            products = productPriceMapper.selectByExample(o);
            for (ProductPrice p: products){
                ProductPriceVO vo = new ProductPriceVO();
                BeanUtils.copyProperties(p,vo);
                productPriceVOs.add(vo);
            }
            List<ProductPriceVO> categoryVOSWithName = productPriceVOs.stream()
                    .map(vo -> getCategoryName(vo))
                    .collect(Collectors.toList());
            PageInfo<ProductPriceVO> info = new PageInfo<>(productPriceVOs);
            return new PageVO<>(info.getTotal(), categoryVOSWithName);
        }
        if (productPriceVO.getOneCategoryId() != null) {
            criteria.andEqualTo("oneCategoryId", productPriceVO.getOneCategoryId());
            products = productPriceMapper.selectByExample(o);
            for (ProductPrice p: products){
                ProductPriceVO vo = new ProductPriceVO();
                BeanUtils.copyProperties(p,vo);
                productPriceVOs.add(vo);
            }
            List<ProductPriceVO> categoryVOSWithName = productPriceVOs.stream()
                    .map(vo -> getCategoryName(vo))
                    .collect(Collectors.toList());
            PageInfo<ProductPriceVO> info = new PageInfo<>(productPriceVOs);
            return new PageVO<>(info.getTotal(), categoryVOSWithName);
        }

        products = productPriceMapper.selectByExample(o);
        for (ProductPrice p: products){
            ProductPriceVO vo = new ProductPriceVO();
            BeanUtils.copyProperties(p,vo);
            productPriceVOs.add(vo);
        }

        List<ProductPriceVO> categoryVOSWithName = productPriceVOs.stream()
                .map(vo -> getCategoryName(vo))
                .collect(Collectors.toList());
        PageInfo<ProductPriceVO> info = new PageInfo<>(productPriceVOs);
        return new PageVO<>(info.getTotal(), categoryVOSWithName);
    }

    @Override
    public ProductPriceVO edit(Long id) {
        ProductPrice productPrice = productPriceMapper.selectByPrimaryKey(id);
        ProductPriceVO productPriceVO = new ProductPriceVO();
        BeanUtils.copyProperties(productPrice,productPriceVO);
        return productPriceVO;
    }

    @Override
    public void update(Long id, ProductPriceVO productPriceVO) {
//        ProductPrice originProductPrice = productPriceMapper.selectByPrimaryKey(id);
        ProductPrice productPrice = new ProductPrice();
        BeanUtils.copyProperties(productPriceVO, productPrice);
        productPriceMapper.updateByPrimaryKeySelective(productPrice);
    }

    private ProductPriceVO getCategoryName(ProductPriceVO p) {
        p.setOneCategoryName(productCategoryService.getName(p.getOneCategoryId()));
        p.setTwoCategoryName(productCategoryService.getName(p.getTwoCategoryId()));

        return p;
    }
}
