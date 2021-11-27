package com.coderman.controller.system;


import com.coderman.business.mapper.ProductMapper;
import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.dto.UserLoginDTO;
import com.coderman.common.error.SystemException;
import com.coderman.common.mapper.DictionaryMapper;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.system.*;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.system.*;
import com.coderman.system.converter.RoleConverter;
import com.coderman.system.mapper.DepartmentMapper;
import com.coderman.system.service.LogService;
import com.coderman.system.service.LoginLogService;
import com.coderman.system.service.RoleService;
import com.coderman.system.service.UserService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Author zhangyukang
 * @Date 2020/3/7 16:24
 * @Version 1.0
 **/

@RestController
@RequestMapping("/system/user")
@Validated
@Api(tags = "系统模塊-用戶相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private LogService logService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DictionaryMapper dictionaryMapper;

    /**
     * 用戶登入
     *
     * @return
     */
    @ApiOperation(value = "用戶登入", notes = "接收参数用戶名和密碼,登入成功后,返回JWTToken")
    @PostMapping("/login")
    public ResponseBean<String> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request) throws SystemException {
        String token=userService.login(userLoginDTO.getUsername(),userLoginDTO.getPassword());
        loginLogService.add(request);
        Log sysLog=new Log();
        sysLog.setOperation("用戶登入");
        sysLog.setUsername(userLoginDTO.getUsername());
        sysLog.setCreateTime(new Date());
        logService.saveLog(sysLog);
        return ResponseBean.success(token);
    }



    /**
     * 用戶列表
     *
     * @return
     */
    @ApiOperation(value = "用戶列表", notes = "模糊查询用戶列表")
    @GetMapping("/findUserList")
    public ResponseBean<PageVO<UserVO>> findUserList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize,
                                     UserVO userVO) {
        PageVO<UserVO> userList = userService.findUserList(pageNum, pageSize, userVO);
        return ResponseBean.success(userList);
    }

    /**
     * 用戶信息
     *
     * @return
     */
    @ApiOperation(value = "用戶信息", notes = "用戶登入信息")
    @GetMapping("/info")
    public ResponseBean<UserInfoVO> info() throws SystemException {
        UserInfoVO userInfoVO=userService.info();
        return ResponseBean.success(userInfoVO);
    }

    /**
     * 加载選單
     *
     * @return
     */
    @ApiOperation(value = "加载選單", notes = "用戶登入后,根據角色加载選單树")
    @GetMapping("/findMenu")
    public ResponseBean<List<MenuNodeVO>> findMenu() {
        List<MenuNodeVO> menuTreeVOS = userService.findMenu();
        return ResponseBean.success(menuTreeVOS);
    }

    /**
     * 分配角色
     *
     * @param id
     * @param rids
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "分配角色失敗", operation = "分配角色")
    @ApiOperation(value = "分配角色", notes = "角色分配给用戶")
    @RequiresPermissions({"user:assign"})
    @PostMapping("/{id}/assignRoles")
    public ResponseBean assignRoles(@PathVariable Long id, @RequestBody Long[] rids) throws SystemException {
        userService.assignRoles(id, rids);
        return ResponseBean.success();
    }

    /**
     * 删除用戶
     *
     * @param id 用戶ID
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "删除用戶失敗", operation = "删除用戶")
    @RequiresPermissions({"user:delete"})
    @ApiOperation(value = "删除用戶", notes = "删除用戶信息，根據用戶ID")
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) throws SystemException {
        userService.deleteById(id);
        return ResponseBean.success();
    }

    /**
     * 更新用戶密碼
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "重設密碼失敗", operation = "重設密碼")
    @ApiOperation(value = "重設密碼", notes = "重設密碼")
    @PutMapping("/updatePassword/{oldPassword}/{newPassword}")
    public ResponseBean updatePassword(@PathVariable String oldPassword, @PathVariable String newPassword) throws SystemException {
        boolean success = userService.updatePassword(oldPassword, newPassword);
        if(success) {
            return ResponseBean.success();
        }
        return ResponseBean.error("密碼不正確");
    }

    /**
     * 更新狀態
     *
     * @param id
     * @param status
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新用戶狀態失敗", operation = "用戶|禁用/啟用")
    @ApiOperation(value = "用戶狀態", notes = "禁用和啟用这两种狀態")
    @RequiresPermissions({"user:status"})
    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseBean updateStatus(@PathVariable Long id, @PathVariable Boolean status) throws SystemException {
        userService.updateStatus(id, status);
        return ResponseBean.success();
    }

    /**
     * 更新用戶
     *
     * @param id
     * @param userEditVO
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新用戶失敗", operation = "更新用戶")
    @ApiOperation(value = "更新用戶", notes = "更新用戶信息")
    @RequiresPermissions({"user:edit"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody @Validated UserEditVO userEditVO) throws SystemException {
        userService.update(id, userEditVO);
        return ResponseBean.success();
    }

    /**
     * 更新用戶
     *
     * @param id
     * @param password
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新用戶密碼失敗", operation = "更新用戶密碼")
    @ApiOperation(value = "更新用戶密碼", notes = "更新用戶密碼")
    @RequiresPermissions({"user:changePassword"})
    @PutMapping("/change/password/{id}/{password}")
    public ResponseBean changePassword(@PathVariable Long id, @PathVariable String password) throws SystemException {
        try {
            userService.changePassword(id, password);
        } catch (Exception e){
            return ResponseBean.error("更新用戶密碼失敗");
        }
        return ResponseBean.success();
    }

    /**
     * 編辑用戶
     * @param id
     * @return
     */
    @ApiOperation(value = "編辑用戶", notes = "获取用戶的详情，編辑用戶信息")
    @RequiresPermissions({"user:edit"})
    @GetMapping("/edit/{id}")
    public ResponseBean<UserEditVO> edit(@PathVariable Long id) throws SystemException {
        UserEditVO userVO = userService.edit(id);
        return ResponseBean.success(userVO);
    }

    /**
     * 新增用戶信息
     * @param userVO
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "新增用戶失敗", operation = "新增用戶")
    @ApiOperation(value = "新增用戶", notes = "新增用戶信息")
    @RequiresPermissions({"user:add"})
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated UserVO userVO) throws SystemException {
        userService.add(userVO);
        return ResponseBean.success();
    }

    /**
     * 用戶角色信息
     * @param id
     * @return
     */
    @ApiOperation(value = "已有角色", notes = "根據用戶id，获取用戶已经拥有的角色")
    @GetMapping("/{id}/roles")
    public ResponseBean<Map<String, Object>> roles(@PathVariable Long id) throws SystemException {
        List<Long> values = userService.roles(id);
        List<Role> list = roleService.findAll();
        //转成前端需要的角色Item
        List<RoleTransferItemVO> items = RoleConverter.converterToRoleTransferItem(list);
        Map<String, Object> map = new HashMap<>();
        map.put("roles", items);
        map.put("values", values);
        return ResponseBean.success(map);
    }

    /**
     * 卡片產品信息
     * @param id
     * @return
     */
    @ApiOperation(value = "卡片已有產品", notes = "根據卡片id，獲取卡片已經擁有的產品")
    @GetMapping("/card/{id}/products")
    public ResponseBean<Map<String, Object>> cardProducts(@PathVariable Long id) throws SystemException {
        List<Long> productsByCard = userService.findProductsByCard(id);
        List<Product> products = productMapper.selectAll();
        //转成前端需要的產品Item
        List<RoleTransferItemVO> itemVOList=new ArrayList<>();
        if(!CollectionUtils.isEmpty(products)){
            for (Product p : products) {
                RoleTransferItemVO item = new RoleTransferItemVO();
                item.setLabel(p.getName());
                item.setDisabled(p.getStatus()==0);
                item.setKey(p.getId());
                itemVOList.add(item);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("products", itemVOList);
        map.put("values", productsByCard);
        return ResponseBean.success(map);
    }

    /**
     * 導出excel
     * @param response
     */
    @ApiOperation(value = "導出excel", notes = "導出所有用戶的excel表格")
    @PostMapping("/excel")
    @RequiresPermissions("user:export")
    @ControllerEndpoint(exceptionMessage = "導出Excel失敗",operation = "導出用戶excel")
    public void export(HttpServletResponse response) {
        List<UserVO> users = this.userService.findAll();
        ExcelKit.$Export(UserVO.class, response).downXlsx(users, false);
    }

    /**
     * 加载用戶卡片列表
     *
     * @return
     */
    @ApiOperation(value = "加载用戶卡片列表", notes = "加载用戶卡片列表")
    @RequiresPermissions({"card:manage"})
    @GetMapping("/card/list/{id}")
    public ResponseBean<List<UserCardVO>> findUserCard(@PathVariable Long id) throws SystemException {
        List<UserCard> userCardList = userService.findCardsById(id);
        List<UserCardVO> list = new ArrayList<>();
        for (UserCard c: userCardList) {
            Long userId = c.getUserId();
            UserEditVO user = userService.edit(userId);
            Department department = departmentMapper.selectByPrimaryKey(user.getDepartmentId());
            UserCardVO vo = new UserCardVO();
            vo.setId(c.getId());
            vo.setUserId(userId);
            vo.setCardName(c.getCardName());
            vo.setDepartmentCategoryName(dictionaryMapper.selectByPrimaryKey(department.getTypeId()).getValue());
            vo.setDepartmentName(department.getNickname());
            vo.setRegionName(dictionaryMapper.selectByPrimaryKey(user.getRegionId()).getValue());
            vo.setStatus(c.getStatus()==0);
            list.add(vo);
        }
        return ResponseBean.success(list);
    }

    /**
     * 新增用戶卡片
     *
     * @return
     */
    @ApiOperation(value = "新增用戶卡片", notes = "新增用戶卡片")
    @PutMapping("/card/add/{userId}/{cardId}")
    public ResponseBean addUserCard(@PathVariable Long userId, @PathVariable String cardId) throws SystemException {
        int insert = userService.addUserCard(userId, cardId);
        if (insert==0) {
            return ResponseBean.error("請確認卡號是否重複");
        }
        return ResponseBean.success();
    }

    /**
     * 更新卡片狀態
     *
     * @param id
     * @param status
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新卡片狀態失敗", operation = "卡片|禁用/啟用")
    @ApiOperation(value = "卡片狀態", notes = "禁用和啟用這兩種狀態")
    @PutMapping("/updateCardStatus/{id}/{status}")
    public ResponseBean updateCardStatus(@PathVariable Long id, @PathVariable Boolean status) throws SystemException {
        userService.updateCardStatus(id, status);
        return ResponseBean.success();
    }

    /**
     * 分配卡片廢棄物
     *
     * @param id
     * @param pids
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "分配卡片廢棄物失敗", operation = "分配卡片廢棄物")
    @ApiOperation(value = "分配卡片廢棄物", notes = "廢棄物分配给卡片")
    @PostMapping("/{id}/assignProducts")
    public ResponseBean assignProducts(@PathVariable String id, @RequestBody Long[] pids) throws SystemException {
        userService.assignProducts(id, pids);
        return ResponseBean.success();
    }

}
