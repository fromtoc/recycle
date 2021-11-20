package com.coderman.business.service.imp;

import com.coderman.business.converter.ProductConverter;
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
import com.coderman.common.model.business.ProductPrice;
import com.coderman.common.model.business.Weight;
import com.coderman.common.model.system.*;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.service.DictionaryService;
import com.coderman.common.vo.business.ProductPriceVO;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.business.WeightVO;
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

    @Override
    public int add(Weight weight) {
        weight.setStatus(1);
        weight.setCreateTime(new Date());
        weight.setLoadTime(new Date());
        return weightMapper.insert(weight);
    }

    @Override
    public int addVO(WeightVO weightVO) throws SystemException {
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
        weight.setCreateTime(new Date());
        weight.setStatus(1);
        weight.setLoadTime(new Date());
        return weightMapper.insert(weight);
    }

    @Override
    public PageVO<WeightVO> findWeightList(Integer pageNum, Integer pageSize, WeightVO weightVO) {
        PageHelper.startPage(pageNum, pageSize);

        Example o = new Example(Weight.class);
        Example.Criteria criteria = o.createCriteria();
        Long departmentId = weightVO.getDepartmentId();
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
            criteria.andGreaterThanOrEqualTo("createTime", createTimeStart);
        }
        if (createTimeEnd != null) {
            criteria.andLessThanOrEqualTo("createTime", createTimeEnd);
        }
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

        PageInfo<WeightVO> weightPageInfo = new PageInfo<>(weightVOList);
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
    public List<Weight> findAll() {
        return weightMapper.selectAll();
    }
}
