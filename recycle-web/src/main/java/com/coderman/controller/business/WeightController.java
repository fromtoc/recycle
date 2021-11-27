package com.coderman.controller.business;


import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.service.WeightService;
import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.business.Weight;
import com.coderman.common.model.system.*;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.business.ProductPriceVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.business.WeightVO;
import com.coderman.system.service.UserService;
import com.coderman.common.vo.system.*;
import com.coderman.system.mapper.CardProductMapper;
import com.coderman.system.mapper.UserCardMapper;
import com.coderman.system.mapper.UserMapper;
import com.coderman.system.service.*;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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
        o.createCriteria().andEqualTo("status", 1);
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
                    .filter(product -> product.getStatus()==1)
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
            String date = weightService.add(weight);
            if (StringUtils.isEmpty(date)) {
                return ResponseBean.error("新增秤重單失敗");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("date", date);
            return ResponseBean.success(map);
        } catch (Exception e) {
            return ResponseBean.error("新增秤重單失敗");
        }
    }

    /**
     * 秤重列表
     *
     * @return
     */
    @ApiOperation(value = "秤重列表", notes = "查询秤重列表")
    @GetMapping("/findWeightList")
    public ResponseBean<PageVO<WeightVO>> findUserList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize,
                                                     WeightVO weightVO) {
        PageVO<WeightVO> weightList = weightService.findWeightList(pageNum, pageSize, weightVO);
        return ResponseBean.success(weightList);
    }

    /**
     * 新增秤重明細
     *
     * @param weightVO
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "新增秤重明細失敗", operation = "新增秤重明細")
    @ApiOperation(value = "新增秤重明細", notes = "新增秤重明細")
    @RequiresPermissions({"weight:add"})
    @PostMapping("/addVO")
    public ResponseBean addVO(@RequestBody WeightVO weightVO) throws SystemException {
        weightService.addVO(weightVO);
        return ResponseBean.success();
    }

    /**
     * 編辑秤重明細
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "編辑秤種明細", notes = "編辑秤重明細")
    @RequiresPermissions({"weight:edit"})
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Long id) {
        WeightVO weightVO = weightService.edit(id);
        return ResponseBean.success(weightVO);
    }

    /**
     * 更新秤種明細
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "編輯秤種明細失敗", operation = "編輯秤種明細")
    @ApiOperation(value = "編輯秤種明細", notes = "編輯秤種明細")
    @RequiresPermissions({"weight:edit"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody WeightVO weightVO) throws BusinessException, SystemException {
        weightService.update(id, weightVO);
        return ResponseBean.success();
    }

    /**
     * 更新狀態
     *
     * @param id
     * @param status
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新秤重明細狀態失敗", operation = "秤重明細|作廢/啟用")
    @ApiOperation(value = "秤重明細狀態", notes = "作廢和啟用这兩種狀態")
    @RequiresPermissions({"weight:status"})
    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseBean updateStatus(@PathVariable Long id, @PathVariable Boolean status) throws SystemException {
        weightService.updateStatus(id, status);
        return ResponseBean.success();
    }

    /**
     * 導出excel
     * @param response
     */
    @ApiOperation(value = "導出excel", notes = "導出所有秤重明細的excel表格")
    @PostMapping("/excel")
    @RequiresPermissions("weight:export")
    @ControllerEndpoint(exceptionMessage = "導出Excel失敗",operation = "導出秤重明細excel")
    public void export(HttpServletResponse response) {
        List<WeightVO> voList = this.weightService.findAll();
        ExcelKit.$Export(WeightVO.class, response).downXlsx(voList, false);
    }





}
