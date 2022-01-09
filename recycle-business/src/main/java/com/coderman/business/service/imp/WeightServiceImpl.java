package com.coderman.business.service.imp;

import com.coderman.business.converter.ProductConverter;
import com.coderman.business.mapper.ProductCategoryMapper;
import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.mapper.ProductStockMapper;
import com.coderman.business.mapper.WeightMapper;
import com.coderman.business.service.ProductCategoryService;
import com.coderman.business.service.ProductService;
import com.coderman.business.service.WeightService;
import com.coderman.common.enums.system.UserStatusEnum;
import com.coderman.common.enums.system.UserTypeEnum;
import com.coderman.common.error.BusinessCodeEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.business.ProductCategory;
import com.coderman.common.model.business.ProductPrice;
import com.coderman.common.model.business.Weight;
import com.coderman.common.model.system.*;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.service.DictionaryService;
import com.coderman.common.vo.business.*;
import com.coderman.common.vo.system.PageVO;
import com.coderman.system.mapper.DepartmentMapper;
import com.coderman.system.mapper.UserCardMapper;
import com.coderman.system.mapper.UserMapper;
import com.coderman.system.service.UserService;
import com.coderman.system.util.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
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
public class WeightServiceImpl implements WeightService {

    @Autowired
    private WeightMapper weightMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCardMapper userCardMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public String add(Weight weight) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
        weight.setStatus(1);
        weight.setCreateTime(new Date());
        Date date = new Date();
        weight.setLoadTime(date);
        int i = weightMapper.insert(weight);
        if (i==0) {
            return null;
        }
        return sdf.format(date);
    }

    @Override
    public int addVO(WeightVO weightVO) throws SystemException {
//        Example o1 = new Example(UserCard.class);
//        o1.createCriteria().andEqualTo("cardName", weightVO.getCardName());
//        o1.createCriteria().andEqualTo("status", 1);
//        UserCard userCard = userCardMapper.selectOneByExample(o1);
        UserCard userCard = userCardMapper.selectByPrimaryKey(weightVO.getCardId());
        if (userCard == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "登入卡號不存在");
        }
//        Example o2 = new Example(Product.class);
//        o2.createCriteria().andEqualTo("name", weightVO.getProductName());
//        o2.createCriteria().andEqualTo("status", 1);
//        Product product = productMapper.selectOneByExample(o2);
        Product product = productMapper.selectByPrimaryKey(weightVO.getProductId());
        if (product == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "廢棄物名稱不存在");
        }
        User user = userMapper.selectByPrimaryKey(userCard.getUserId());
        Department department = departmentMapper.selectByPrimaryKey(user.getDepartmentId());

        Weight weight = new Weight();
        BeanUtils.copyProperties(weightVO, weight);
        weight.setDepartmentId(department.getId());
        weight.setUserId(user.getId());
        weight.setCardId(userCard.getId());
        weight.setProductId(product.getId());
//        weight.setCreateTime(new Date());
        weight.setStatus(1);
//        weight.setLoadTime(new Date());
        return weightMapper.insert(weight);
    }

    @Override
    public PageVO<WeightVO> findWeightList(Integer pageNum, Integer pageSize, WeightVO weightVO) {
        PageHelper.startPage(pageNum, pageSize);

        Example o = new Example(Weight.class);
        Example.Criteria criteria = o.createCriteria();
        Long departmentId = weightVO.getDepartmentId();
        if (weightVO.getStatus() != null) {
            criteria.andEqualTo("status", weightVO.getStatus()? 0: 1);
        }
        if (departmentId != null && !"".equals(departmentId)) {
            criteria.andEqualTo("departmentId", departmentId);
        }
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (activeUser.getLimitUser()){
            criteria.andEqualTo("userId", activeUser.getUser().getId());
        } else {
            String userNickname = weightVO.getUserNickname();
            if (userNickname != null && !"".equals(userNickname)) {
                List<User> userList = userService.findUserByNickName(userNickname);
                if (!CollectionUtils.isEmpty(userList)) {
                    List<Long> userIds = userList.stream().map(user -> user.getId()).collect(Collectors.toList());
                    criteria.andIn("userId", userIds);
                } else {
                    criteria.andEqualTo("userId", -1);
                }
            }
        }
        String productName = weightVO.getProductName();
        if (productName != null && !"".equals(productName)) {
            List<Product> productList = productService.findProductByProductName(productName);
            if (!CollectionUtils.isEmpty(productList)) {
                List<Long> productIds = productList.stream().map(product -> product.getId()).collect(Collectors.toList());
                criteria.andIn("productId", productIds);
            } else {
                criteria.andEqualTo("productId", -1);
            }
        }
        Date createTimeStart = weightVO.getCreateTimeStart();
        Date createTimeEnd = weightVO.getCreateTimeEnd();
        if (createTimeStart != null) {
            criteria.andGreaterThanOrEqualTo("loadTime", createTimeStart);
        }
        if (createTimeEnd != null) {
            criteria.andLessThanOrEqualTo("loadTime", createTimeEnd);
        }
        o.setOrderByClause("load_time desc");
        List<Weight> weightList = weightMapper.selectByExample(o);
        List<WeightVO> weightVOList = new ArrayList<>();
        for (Weight w : weightList) {
            WeightVO vo = new WeightVO();
            BeanUtils.copyProperties(w, vo);
            vo.setDepartmentName(departmentMapper.selectByPrimaryKey(w.getDepartmentId()).getNickname());
            vo.setUserNickname(userMapper.selectByPrimaryKey(w.getUserId()).getNickname());
            vo.setCardName(userCardMapper.selectByPrimaryKey(w.getCardId()).getCardName());
            vo.setProductName(productMapper.selectByPrimaryKey(w.getProductId()).getName());
            vo.setStatus(w.getStatus() == 0 ? true : false);
            weightVOList.add(vo);
        }

        PageInfo<Weight> weightPageInfo = new PageInfo<>(weightList);
        return new PageVO<>(weightPageInfo.getTotal(), weightVOList);
    }

    @Override
    public WeightVO edit(Long id) {
        Weight weight = weightMapper.selectByPrimaryKey(id);
        WeightVO weightVO = new WeightVO();
        BeanUtils.copyProperties(weight, weightVO);
        weightVO.setCardName(userCardMapper.selectByPrimaryKey(weightVO.getCardId()).getCardName());
        weightVO.setProductName(productMapper.selectByPrimaryKey(weightVO.getProductId()).getName());
        return weightVO;
    }

    @Override
    public void update(Long id, WeightVO weightVO) throws SystemException {
        Example o1 = new Example(UserCard.class);
        o1.createCriteria().andEqualTo("cardName", weightVO.getCardName());
        o1.createCriteria().andEqualTo("status", 1);
        UserCard userCard = userCardMapper.selectOneByExample(o1);
        if (userCard == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "登入卡號不存在");
        }
        Example o2 = new Example(Product.class);
        o2.createCriteria().andEqualTo("name", weightVO.getProductName());
        o2.createCriteria().andEqualTo("status", 1);
        Product product = productMapper.selectOneByExample(o2);
        if (product == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "廢棄物名稱不存在");
        }
        User user = userMapper.selectByPrimaryKey(userCard.getUserId());
        Department department = departmentMapper.selectByPrimaryKey(user.getDepartmentId());

        Weight weight = new Weight();
        BeanUtils.copyProperties(weightVO, weight);
        weight.setDepartmentId(department.getId());
        weight.setUserId(user.getId());
        weight.setCardId(userCard.getId());
        weight.setProductId(product.getId());
        weight.setLoadTime(new Date());
        weightMapper.updateByPrimaryKeySelective(weight);
    }

    @Override
    public void delete(Long id) throws BusinessException {

    }

    @Override
    public void updateStatus(Long id, Boolean status) throws SystemException {
        Weight d = new Weight();
        d.setId(id);
        d.setStatus(status ? UserStatusEnum.DISABLE.getStatusCode() :
                UserStatusEnum.AVAILABLE.getStatusCode());
        d.setLoadTime(new Date());
        weightMapper.updateByPrimaryKeySelective(d);
    }

    @Override
    public List<WeightVO> findAll(WeightVO weightVO) {
        Example o = new Example(Weight.class);
        Example.Criteria criteria = o.createCriteria();
        Long departmentId = weightVO.getDepartmentId();
        if (weightVO.getStatus() != null) {
            criteria.andEqualTo("status", weightVO.getStatus()? 0: 1);
        }
        if (departmentId != null && !"".equals(departmentId)) {
            criteria.andEqualTo("departmentId", departmentId);
        }
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (activeUser.getLimitUser()){
            criteria.andEqualTo("userId", activeUser.getUser().getId());
        } else {
            String userNickname = weightVO.getUserNickname();
            if (userNickname != null && !"".equals(userNickname)) {
                List<User> userList = userService.findUserByNickName(userNickname);
                if (!CollectionUtils.isEmpty(userList)) {
                    List<Long> userIds = userList.stream().map(user -> user.getId()).collect(Collectors.toList());
                    criteria.andIn("userId", userIds);
                } else {
                    criteria.andEqualTo("userId", -1);
                }
            }
        }
        String productName = weightVO.getProductName();
        if (productName != null && !"".equals(productName)) {
            List<Product> productList = productService.findProductByProductName(productName);
            if (!CollectionUtils.isEmpty(productList)) {
                List<Long> productIds = productList.stream().map(product -> product.getId()).collect(Collectors.toList());
                criteria.andIn("productId", productIds);
            } else {
                criteria.andEqualTo("productId", -1);
            }
        }
        Date createTimeStart = weightVO.getCreateTimeStart();
        Date createTimeEnd = weightVO.getCreateTimeEnd();
        if (createTimeStart != null) {
            criteria.andGreaterThanOrEqualTo("loadTime", createTimeStart);
        }
        if (createTimeEnd != null) {
            criteria.andLessThanOrEqualTo("loadTime", createTimeEnd);
        }
        o.setOrderByClause("load_time desc");
        List<Weight> weightList = weightMapper.selectByExample(o);
        List<WeightVO> voList = new ArrayList<>();
        weightList.stream().forEach(w-> {
            WeightVO vo = new WeightVO();
            BeanUtils.copyProperties(w, vo);
            vo.setDepartmentName(departmentMapper.selectByPrimaryKey(w.getDepartmentId()).getNickname());
            vo.setUserNickname(userMapper.selectByPrimaryKey(w.getUserId()).getNickname());
            vo.setCardName(userCardMapper.selectByPrimaryKey(w.getCardId()).getCardName());
            vo.setProductName(productMapper.selectByPrimaryKey(w.getProductId()).getName());
            vo.setStatus(w.getStatus() == 0 ? true : false);
            voList.add(vo);
        });
        return voList;
    }

    @Override
    public List<CardTreeNodeVO> cardTree() {
        List<CardTreeNodeVO> cardTreeNode1VOList = new ArrayList<>();
        List<Department> departmentList= departmentMapper.selectAll().stream().filter(d -> d.getStatus() == 1).collect(Collectors.toList());
        for (Department d: departmentList) {
            CardTreeNodeVO cardTreeNode1VO = new CardTreeNodeVO();
            cardTreeNode1VO.setId(d.getId());
            cardTreeNode1VO.setName(d.getNickname());

            Example o = new Example(User.class);
            Example.Criteria criteria = o.createCriteria();
            criteria.andEqualTo("departmentId", d.getId());
            criteria.andNotEqualTo("type", 0);
            criteria.andEqualTo("status", 1);
            List<User> userList = userMapper.selectByExample(o);
            List<CardTreeNodeVO> cardTreeNode2VOList = new ArrayList<>();
            for (User u : userList) {
                CardTreeNodeVO cardTreeNode2VO = new CardTreeNodeVO();
                cardTreeNode2VO.setId(u.getId());
                cardTreeNode2VO.setName(u.getNickname());

                Example o1 = new Example(UserCard.class);
                o1.createCriteria()
                        .andEqualTo("userId", u.getId())
                        .andEqualTo("status", 1);
                List<UserCard> userCardList = userCardMapper.selectByExample(o1);
                List<CardTreeNodeVO> cardTreeNode3VOList = new ArrayList<>();
                for (UserCard c : userCardList) {
                    CardTreeNodeVO cardTreeNode3VO = new CardTreeNodeVO();
                    cardTreeNode3VO.setId(c.getId());
                    cardTreeNode3VO.setName(c.getCardName());
                    cardTreeNode3VOList.add(cardTreeNode3VO);
                }
                cardTreeNode2VO.setChildren(cardTreeNode3VOList);

                cardTreeNode2VOList.add(cardTreeNode2VO);
            }
            cardTreeNode1VO.setChildren(cardTreeNode2VOList);

            cardTreeNode1VOList.add(cardTreeNode1VO);
        }
        return cardTreeNode1VOList;
    }

    @Override
    public List<CardTreeNodeVO> productTree() {
        List<CardTreeNodeVO> cardTreeNode1VOList = new ArrayList<>();
        Example o = new Example(ProductCategory.class);
        o.createCriteria().andEqualTo("pid", 0).andEqualTo("status", 1);
        List<ProductCategory> oneProductCategoryList = productCategoryMapper.selectByExample(o);
        for (ProductCategory p1: oneProductCategoryList) {
            CardTreeNodeVO cardTreeNode1VO = new CardTreeNodeVO();
            cardTreeNode1VO.setId(p1.getId());
            cardTreeNode1VO.setName(p1.getName());

            Example o1 = new Example(ProductCategory.class);
            o1.createCriteria()
                    .andEqualTo("pid", p1.getId())
                    .andEqualTo("status", 1);
            List<ProductCategory> twoProductCategoryList = productCategoryMapper.selectByExample(o1);
            List<CardTreeNodeVO> cardTreeNode2VOList = new ArrayList<>();
            for (ProductCategory p2 : twoProductCategoryList) {
                CardTreeNodeVO cardTreeNode2VO = new CardTreeNodeVO();
                cardTreeNode2VO.setId(p2.getId());
                cardTreeNode2VO.setName(p2.getName());

                Example o2 = new Example(Product.class);
                o2.createCriteria()
                        .andEqualTo("oneCategoryId", p1.getId())
                        .andEqualTo("twoCategoryId", p2.getId())
                        .andEqualTo("status", 1);
                List<Product> productList = productMapper.selectByExample(o2);
                List<CardTreeNodeVO> cardTreeNode3VOList = new ArrayList<>();
                for (Product product : productList) {
                    CardTreeNodeVO cardTreeNode3VO = new CardTreeNodeVO();
                    cardTreeNode3VO.setId(product.getId());
                    cardTreeNode3VO.setName(product.getName());
                    cardTreeNode3VOList.add(cardTreeNode3VO);
                }
                cardTreeNode2VO.setChildren(cardTreeNode3VOList);

                cardTreeNode2VOList.add(cardTreeNode2VO);
            }
            cardTreeNode1VO.setChildren(cardTreeNode2VOList);

            cardTreeNode1VOList.add(cardTreeNode1VO);
        }
        return cardTreeNode1VOList;
    }
}
