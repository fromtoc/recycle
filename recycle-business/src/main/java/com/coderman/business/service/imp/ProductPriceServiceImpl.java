package com.coderman.business.service.imp;

import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.mapper.ProductPriceMapper;
import com.coderman.business.service.ProductCategoryService;
import com.coderman.business.service.ProductPriceService;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.business.ProductPrice;
import com.coderman.common.service.DictionaryService;
import com.coderman.common.vo.business.ProductPriceUploadVO;
import com.coderman.common.vo.business.ProductPriceVO;
import com.coderman.common.vo.system.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        if (CollectionUtils.isEmpty(products) || products.size()!=1) {
            return -1;
        }
        Example o1 = new Example(ProductPrice.class);
        Example.Criteria criteria1 = o1.createCriteria();
        criteria1.andEqualTo("name", productPrice.getName());
        criteria1.andEqualTo("validMonth", productPrice.getValidMonth());
        List<ProductPrice> productPrices = productPriceMapper.selectByExample(o1);
        boolean recover = productPriceVO.getRecover() == null? false : productPriceVO.getRecover();
        if (!CollectionUtils.isEmpty(productPrices) && !recover) {
            return -2;
        } else if (recover) {
            productPriceMapper.deleteByPrimaryKey(productPrices.get(0).getId());
        }

        Product product = products.get(0);
        productPrice.setProductId(product.getId());
        productPrice.setOneCategoryId(product.getOneCategoryId());
        productPrice.setTwoCategoryId(product.getTwoCategoryId());
        productPrice.setLoadTime(new Date());
        return productPriceMapper.insert(productPrice);
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
            o.setOrderByClause("valid_month desc");
            products = productPriceMapper.selectByExample(o);
            for (ProductPrice p: products){
                ProductPriceVO vo = new ProductPriceVO();
                BeanUtils.copyProperties(p,vo);
                productPriceVOs.add(vo);
            }
            List<ProductPriceVO> categoryVOSWithName = productPriceVOs.stream()
                    .map(vo -> getCategoryName(vo))
                    .collect(Collectors.toList());
            PageInfo<ProductPrice> info = new PageInfo<>(products);
            return new PageVO<>(info.getTotal(), categoryVOSWithName);
        }
        if (productPriceVO.getTwoCategoryId() != null) {
            criteria.andEqualTo("oneCategoryId", productPriceVO.getOneCategoryId())
                    .andEqualTo("twoCategoryId", productPriceVO.getTwoCategoryId());
            o.setOrderByClause("valid_month desc");
            products = productPriceMapper.selectByExample(o);
            for (ProductPrice p: products){
                ProductPriceVO vo = new ProductPriceVO();
                BeanUtils.copyProperties(p,vo);
                productPriceVOs.add(vo);
            }
            List<ProductPriceVO> categoryVOSWithName = productPriceVOs.stream()
                    .map(vo -> getCategoryName(vo))
                    .collect(Collectors.toList());
            PageInfo<ProductPrice> info = new PageInfo<>(products);
            return new PageVO<>(info.getTotal(), categoryVOSWithName);
        }
        if (productPriceVO.getOneCategoryId() != null) {
            criteria.andEqualTo("oneCategoryId", productPriceVO.getOneCategoryId());
            o.setOrderByClause("valid_month desc");
            products = productPriceMapper.selectByExample(o);
            for (ProductPrice p: products){
                ProductPriceVO vo = new ProductPriceVO();
                BeanUtils.copyProperties(p,vo);
                productPriceVOs.add(vo);
            }
            List<ProductPriceVO> categoryVOSWithName = productPriceVOs.stream()
                    .map(vo -> getCategoryName(vo))
                    .collect(Collectors.toList());
            PageInfo<ProductPrice> info = new PageInfo<>(products);
            return new PageVO<>(info.getTotal(), categoryVOSWithName);
        }
        o.setOrderByClause("valid_month desc");
        products = productPriceMapper.selectByExample(o);
        for (ProductPrice p: products){
            ProductPriceVO vo = new ProductPriceVO();
            BeanUtils.copyProperties(p,vo);
            productPriceVOs.add(vo);
        }

        List<ProductPriceVO> categoryVOSWithName = productPriceVOs.stream()
                .map(vo -> getCategoryName(vo))
                .collect(Collectors.toList());
        PageInfo<ProductPrice> info = new PageInfo<>(products);
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
        productPrice.setLoadTime(new Date());
        productPriceMapper.updateByPrimaryKeySelective(productPrice);
    }

    private ProductPriceVO getCategoryName(ProductPriceVO p) {
        p.setOneCategoryName(productCategoryService.getName(p.getOneCategoryId()));
        p.setTwoCategoryName(productCategoryService.getName(p.getTwoCategoryId()));

        return p;
    }

    @Override
    public List<ProductPriceVO> findAll() {
        List<ProductPrice> products = productPriceMapper.selectAll();
        List<ProductPriceVO> voList = new ArrayList<>();
        for (ProductPrice p: products){
            ProductPriceVO vo = new ProductPriceVO();
            BeanUtils.copyProperties(p,vo);
            voList.add(vo);
        }
        List<ProductPriceVO> categoryVOSWithName = voList.stream()
                .map(vo -> getCategoryName(vo))
                .collect(Collectors.toList());
        return categoryVOSWithName;
    }

    @Override
    public int batchAdd(List<ProductPriceUploadVO> productPriceUploadVOList) {
        int count = 0;
        for (ProductPriceUploadVO p: productPriceUploadVOList) {
            ProductPrice productPrice = new ProductPrice();
            BeanUtils.copyProperties(p, productPrice);
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
                productPrice.setLoadTime(new Date());
                int i = productPriceMapper.insert(productPrice);
                count = count + i;
            }
        }
        return count;
    }

    @Override
    public List<ProductPriceVO> checkSame(List<ProductPriceUploadVO> productPriceUploadVOList) {
        List<ProductPrice> products = new ArrayList<>();
        productPriceUploadVOList.stream().forEach(upload-> {
            Example o = new Example(ProductPrice.class);
            Example.Criteria criteria = o.createCriteria();
            criteria.andEqualTo("name", upload.getName());
            criteria.andEqualTo("validMonth", upload.getValidMonth());
            List<ProductPrice> productPrices = productPriceMapper.selectByExample(o);
            if (!CollectionUtils.isEmpty(productPrices)) {
                products.add(productPrices.get(0));
            }
        });
        List<ProductPriceVO> sameList = new ArrayList<>();
        for (ProductPrice p: products){
            ProductPriceVO vo = new ProductPriceVO();
            BeanUtils.copyProperties(p,vo);
            ProductPriceVO voWIthName = getCategoryName(vo);
            sameList.add(voWIthName);
        }
        return sameList;
    }

    @Override
    public List<ProductPriceUploadVO> checkNotExist(List<ProductPriceUploadVO> productPriceUploadVOList) {
        List<ProductPriceUploadVO> emptyList = new ArrayList<>();
        for (ProductPriceUploadVO p: productPriceUploadVOList) {
            ProductPrice productPrice = new ProductPrice();
            BeanUtils.copyProperties(p, productPrice);
            String productName = productPrice.getName();
            Example o = new Example(Product.class);
            Example.Criteria criteria = o.createCriteria();
            criteria.andEqualTo("name", productName);
            List<Product> products = productMapper.selectByExample(o);
            if (CollectionUtils.isEmpty(products)){
                emptyList.add(p);
            }
        }
        return emptyList;
    }

    @Override
    public int recover(List<ProductPriceUploadVO> productPriceUploadVOList) {
        int count = 0;
        for (ProductPriceUploadVO p: productPriceUploadVOList) {
            ProductPrice productPrice = new ProductPrice();
            BeanUtils.copyProperties(p, productPrice);
            Example o = new Example(ProductPrice.class);
            Example.Criteria criteria = o.createCriteria();
            criteria.andEqualTo("name", productPrice.getName());
            criteria.andEqualTo("validMonth", productPrice.getValidMonth());
            productPriceMapper.deleteByExample(o);

            Example o1 = new Example(Product.class);
            Example.Criteria criteria1 = o1.createCriteria();
            criteria1.andEqualTo("name", productPrice.getName());
            List<Product> products = productMapper.selectByExample(o1);
            if (!CollectionUtils.isEmpty(products) && products.size()==1){
                Product product = products.get(0);
                productPrice.setProductId(product.getId());
                productPrice.setOneCategoryId(product.getOneCategoryId());
                productPrice.setTwoCategoryId(product.getTwoCategoryId());
                productPrice.setLoadTime(new Date());
                int i = productPriceMapper.insert(productPrice);
                count = count + i;
            }
        }
        return count;
    }
}
