package com.coderman.controller.business;


import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.service.WeightService;
import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.dto.UserLoginDTO;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.business.Weight;
import com.coderman.common.model.system.*;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.system.*;
import com.coderman.system.converter.RoleConverter;
import com.coderman.system.mapper.CardProductMapper;
import com.coderman.system.mapper.UserCardMapper;
import com.coderman.system.mapper.UserMapper;
import com.coderman.system.service.*;
import com.coderman.system.util.MD5Utils;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author zhangyukang
 * @Date 2020/3/7 16:24
 * @Version 1.0
 **/

@RestController
@RequestMapping("/business/weight")
@Validated
@Api(tags = "秤重相關接口")
public class WeightController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private LogService logService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private WeightService weightService;

    @Autowired
    private CardProductMapper cardProductMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserCardMapper userCardMapper;

    @Autowired
    private UserMapper userMapper;




    /**
     * 刷卡登入
     *
     * @return
     */
    @ApiOperation(value = "刷卡登入", notes = "接收参数卡號登入")
    @PostMapping("/login")
    public ResponseBean login(@RequestBody Map<String, String> map, HttpServletRequest request) throws SystemException {
        String cardName = map.get("cardName");
        Example o = new Example(UserCard.class);
        o.createCriteria().andEqualTo("cardName", cardName);
        List<UserCard> cardProducts = userCardMapper.selectByExample(o);
        if (!CollectionUtils.isEmpty(cardProducts)) {
            UserCard userCard = cardProducts.get(0);
            if (userCard.getStatus()!=1){
                return ResponseBean.error("卡號錯誤");
            }
            Long userId = userCard.getUserId();
            User user = userMapper.selectByPrimaryKey(userId);
            //登入log
            Log sysLog=new Log();
            sysLog.setOperation("刷卡登入");
            sysLog.setUsername(user.getUsername());
            sysLog.setCreateTime(new Date());
            logService.saveLog(sysLog);
            //取得公司資訊
            DepartmentVO dept = departmentService.edit(user.getDepartmentId());
            //取得卡片廢棄物
            List<Long> productsByCard = userService.findProductsByCard(userCard.getId());
            List<Product> productList = productsByCard.stream()
                    .map(id -> productMapper.selectByPrimaryKey(id))
                    .collect(Collectors.toList());

            Map responseMap = new HashMap();
            responseMap.put("dept", dept);
            responseMap.put("user", user);
            responseMap.put("cardId", userCard.getId());
            responseMap.put("products", productList);

            return ResponseBean.success(responseMap);
        }
        return ResponseBean.error("卡號錯誤");
    }

    /**
     * 新增秤重單
     *
     * @return
     */
    @ApiOperation(value = "新增秤重單", notes = "接收参数新增秤重單")
    @PostMapping("/add")
    public ResponseBean add(@RequestBody Weight weight, HttpServletRequest request) throws SystemException {
        try {
            int add = weightService.add(weight);
            if (add==0) {
                return ResponseBean.error("新增秤重單失敗");
            }
        } catch (Exception e) {
            return ResponseBean.error("新增秤重單失敗");
        }
        return ResponseBean.success();
    }





}
