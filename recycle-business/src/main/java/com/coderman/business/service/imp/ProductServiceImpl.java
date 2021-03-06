package com.coderman.business.service.imp;

import com.coderman.business.converter.ProductConverter;
import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.mapper.ProductStockMapper;
import com.coderman.business.service.ProductCategoryService;
import com.coderman.business.service.ProductService;
import com.coderman.common.enums.system.UserStatusEnum;
import com.coderman.common.error.BusinessCodeEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.system.Department;
import com.coderman.common.model.system.User;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.service.DictionaryService;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.system.DepartmentVO;
import com.coderman.common.vo.system.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
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
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductMapper productMapper;


    @Autowired
    private ProductStockMapper productStockMapper;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    DictionaryService dictionaryService;


    /**
     * 商品列表
     *
     * @param pageNum
     * @param pageSize
     * @param productVO
     * @return
     */
    @Override
    public PageVO<ProductVO> findProductList(Integer pageNum, Integer pageSize, ProductVO productVO) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products;
        Example o = new Example(Product.class);
        Example.Criteria criteria = o.createCriteria();
        if (productVO.getStatus() != null) {
            criteria.andEqualTo("status", productVO.getStatus()? 0: 1);
        }
        o.setOrderByClause("sort asc");
        if (productVO.getName() != null && !"".equals(productVO.getName())) {
            criteria.andLike("name", "%" + productVO.getName() + "%");
        }
        if (productVO.getThreeCategoryId() != null) {
            criteria.andEqualTo("oneCategoryId", productVO.getOneCategoryId())
                    .andEqualTo("twoCategoryId", productVO.getTwoCategoryId())
                    .andEqualTo("threeCategoryId", productVO.getThreeCategoryId());
            products = productMapper.selectByExample(o);
            List<ProductVO> categoryVOS = ProductConverter.converterToVOList(products);
            List<ProductVO> categoryVOSWithName = categoryVOS.stream()
                    .map(vo -> getCategoryName(vo))
                    .collect(Collectors.toList());
            PageInfo<Product> info = new PageInfo<>(products);
            return new PageVO<>(info.getTotal(), categoryVOSWithName);
        }
        if (productVO.getTwoCategoryId() != null) {
            criteria.andEqualTo("oneCategoryId", productVO.getOneCategoryId())
                    .andEqualTo("twoCategoryId", productVO.getTwoCategoryId());
            products = productMapper.selectByExample(o);
            List<ProductVO> categoryVOS = ProductConverter.converterToVOList(products);
            List<ProductVO> categoryVOSWithName = categoryVOS.stream()
                    .map(vo -> getCategoryName(vo))
                    .collect(Collectors.toList());
            PageInfo<Product> info = new PageInfo<>(products);
            return new PageVO<>(info.getTotal(), categoryVOSWithName);
        }
        if (productVO.getOneCategoryId() != null) {
            criteria.andEqualTo("oneCategoryId", productVO.getOneCategoryId());
            products = productMapper.selectByExample(o);
            List<ProductVO> categoryVOS = ProductConverter.converterToVOList(products);
            List<ProductVO> categoryVOSWithName = categoryVOS.stream()
                    .map(vo -> getCategoryName(vo))
                    .collect(Collectors.toList());
            PageInfo<Product> info = new PageInfo<>(products);
            return new PageVO<>(info.getTotal(), categoryVOSWithName);
        }

        products = productMapper.selectByExample(o);
        List<ProductVO> categoryVOS = ProductConverter.converterToVOList(products);
        List<ProductVO> categoryVOSWithName = categoryVOS.stream()
                .map(vo -> getCategoryName(vo))
                .collect(Collectors.toList());
        PageInfo<Product> info = new PageInfo<>(products);
        return new PageVO<>(info.getTotal(), categoryVOSWithName);
    }


    /**
     * 新增商品
     *
     * @param ProductVO
     */
    @Override
    public void add(ProductVO ProductVO) {
        Product product = new Product();
        BeanUtils.copyProperties(ProductVO, product);
        product.setCreateTime(new Date());
        product.setModifiedTime(new Date());
        @NotNull(message = "分類不能为空") Long[] categoryKeys = ProductVO.getCategoryKeys();
        if (categoryKeys.length == 2) {
            product.setOneCategoryId(categoryKeys[0]);
            product.setTwoCategoryId(categoryKeys[1]);
//            product.setThreeCategoryId(categoryKeys[2]);
        }
        product.setStatus(1);//2未審核
        product.setPNum(UUID.randomUUID().toString().substring(0, 32));
        product.setLoadTime(new Date());
        productMapper.insert(product);
    }

    /**
     * 編辑商品
     *
     * @param id
     * @return
     */
    @Override
    public ProductVO edit(Long id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return ProductConverter.converterToProductVO(product);
    }

    /**
     * 更新商品
     *
     * @param id
     * @param ProductVO
     */
    @Override
    public void update(Long id, ProductVO ProductVO) {
        Product product = new Product();
        BeanUtils.copyProperties(ProductVO, product);
        product.setModifiedTime(new Date());
        @NotNull(message = "分類不能为空") Long[] categoryKeys = ProductVO.getCategoryKeys();
        if (categoryKeys.length == 2) {
            product.setOneCategoryId(categoryKeys[0]);
            product.setTwoCategoryId(categoryKeys[1]);
//            product.setThreeCategoryId(categoryKeys[2]);
        }
        product.setLoadTime(new Date());
        productMapper.updateByPrimaryKeySelective(product);
    }

    /**
     * 删除商品
     *
     * @param id
     */
    @Override
    public void delete(Long id) throws BusinessException {
        Product t = new Product();
        t.setId(id);
        Product product = productMapper.selectByPrimaryKey(t);
        //只有物資处于回收站,或者待审核的情况下可删除
        if (product.getStatus() != 1 && product.getStatus() != 2) {
            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
        } else {
            productMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 物資库存列表
     *
     * @param pageNum
     * @param pageSize
     * @param productVO
     * @return
     */
    @Override
    public PageVO<ProductStockVO> findProductStocks(Integer pageNum, Integer pageSize, ProductVO productVO) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductStockVO> productStockVOList = productStockMapper.findProductStocks(productVO);
        PageInfo<ProductStockVO> info = new PageInfo<>(productStockVOList);
        return new PageVO<>(info.getTotal(), productStockVOList);
    }

    /**
     * 所有库存信息
     *
     * @return
     */
    @Override
    public List<ProductStockVO> findAllStocks(Integer pageNum, Integer pageSize, ProductVO productVO) {
        PageHelper.startPage(pageNum, pageSize);
        return productStockMapper.findAllStocks(productVO);
    }

    /**
     * 移入回收站
     *
     * @param id
     */
    @Override
    public void remove(Long id) throws BusinessException {
        Product t = new Product();
        t.setId(id);
        Product product = productMapper.selectByPrimaryKey(t);
        if (product.getStatus() != 0) {
            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
        } else {
            t.setStatus(1);
            t.setLoadTime(new Date());
            productMapper.updateByPrimaryKeySelective(t);
        }
    }

    /**
     * 从回收站恢复数據
     *
     * @param id
     */
    @Override
    public void back(Long id) throws BusinessException {
        Product t = new Product();
        t.setId(id);
        Product product = productMapper.selectByPrimaryKey(t);
        if (product.getStatus() != 1) {
            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
        } else {
            t.setStatus(0);
            t.setLoadTime(new Date());
            productMapper.updateByPrimaryKeySelective(t);
        }
    }

    /**
     * 物資审核
     *
     * @param id
     */
    @Override
    public void publish(Long id) throws BusinessException {
        Product t = new Product();
        t.setId(id);
        Product product = productMapper.selectByPrimaryKey(t);
        if (product.getStatus() != 2) {
            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
        } else {
            t.setStatus(0);
            t.setLoadTime(new Date());
            productMapper.updateByPrimaryKeySelective(t);
        }
    }

    /**
     * 更新狀態
     *
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(Long id, Boolean status) throws SystemException {
        Product product = productMapper.selectByPrimaryKey(id);
        if (product == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "要更新狀態的廢棄物不存在");
        }
        Product update = new Product();
        update.setId(product.getId());
        update.setStatus(status ? 0 : 1);
        update.setLoadTime(new Date());
        productMapper.updateByPrimaryKeySelective(update);
    }

    @Override
    public void updateCostCenter(List<Long> ids, Long costCenterId) throws SystemException {
        for (Long id :ids){
            Product update = new Product();
            update.setId(id);
            update.setModel(costCenterId);
            update.setLoadTime(new Date());
            productMapper.updateByPrimaryKeySelective(update);
        }

    }

    private ProductVO getCategoryName(ProductVO p) {
        p.setOneCategoryName(productCategoryService.getName(p.getOneCategoryId()));
        p.setTwoCategoryName(productCategoryService.getName(p.getTwoCategoryId()));
        if (p.getModel()!=null){
            try {
                String modelName = dictionaryService.selectByPrimaryKey(p.getModel()).getValue();
                p.setModelName(modelName);
            } catch (SystemException e) {
                e.printStackTrace();
            }
        }

        return p;
    }

    /**
     * 查询產品
     *
     * @param name 產品名
     * @return
     */
    @Override
    public List<Product> findProductByProductName(String name) {

        Example o = new Example(Product.class);
        o.createCriteria().andLike("name", "%" + name + "%");
        return productMapper.selectByExample(o);
    }

    @Override
    public List<ProductVO> findAll(ProductVO productVO) {
        List<Product> products;
        Example o = new Example(Product.class);
        Example.Criteria criteria = o.createCriteria();
        if (productVO.getStatus() != null) {
            criteria.andEqualTo("status", productVO.getStatus()? 0: 1);
        }
        o.setOrderByClause("sort asc");
        if (productVO.getName() != null && !"".equals(productVO.getName())) {
            criteria.andLike("name", "%" + productVO.getName() + "%");
        }
        if (productVO.getThreeCategoryId() != null) {
            criteria.andEqualTo("oneCategoryId", productVO.getOneCategoryId())
                    .andEqualTo("twoCategoryId", productVO.getTwoCategoryId())
                    .andEqualTo("threeCategoryId", productVO.getThreeCategoryId());
            products = productMapper.selectByExample(o);
            List<ProductVO> categoryVOS = ProductConverter.converterToVOList(products);
            List<ProductVO> categoryVOSWithName = categoryVOS.stream()
                    .map(vo -> getCategoryName(vo))
                    .collect(Collectors.toList());
            return categoryVOSWithName;
        }
        if (productVO.getTwoCategoryId() != null) {
            criteria.andEqualTo("oneCategoryId", productVO.getOneCategoryId())
                    .andEqualTo("twoCategoryId", productVO.getTwoCategoryId());
            products = productMapper.selectByExample(o);
            List<ProductVO> categoryVOS = ProductConverter.converterToVOList(products);
            List<ProductVO> categoryVOSWithName = categoryVOS.stream()
                    .map(vo -> getCategoryName(vo))
                    .collect(Collectors.toList());
            return categoryVOSWithName;
        }
        if (productVO.getOneCategoryId() != null) {
            criteria.andEqualTo("oneCategoryId", productVO.getOneCategoryId());
            products = productMapper.selectByExample(o);
            List<ProductVO> categoryVOS = ProductConverter.converterToVOList(products);
            List<ProductVO> categoryVOSWithName = categoryVOS.stream()
                    .map(vo -> getCategoryName(vo))
                    .collect(Collectors.toList());
            return categoryVOSWithName;
        }

        products = productMapper.selectByExample(o);
        List<ProductVO> categoryVOS = ProductConverter.converterToVOList(products);
        List<ProductVO> categoryVOSWithName = categoryVOS.stream()
                .map(vo -> getCategoryName(vo))
                .collect(Collectors.toList());
        return categoryVOSWithName;
    }

}
