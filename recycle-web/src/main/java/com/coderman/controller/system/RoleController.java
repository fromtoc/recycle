package com.coderman.controller.system;

import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Role;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.business.WeightVO;
import com.coderman.common.vo.system.MenuNodeVO;
import com.coderman.common.vo.system.PageVO;
import com.coderman.common.vo.system.RoleVO;
import com.coderman.system.service.MenuService;
import com.coderman.system.service.RoleService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangyukang
 * @Date 2020/3/9 16:21
 * @Version 1.0
 **/
@Api(tags = "系统模塊-角色相关接口")
@RestController
@RequestMapping("/system/role")
public class RoleController {


    @Autowired
    private RoleService roleService;


    @Autowired
    private MenuService menuService;


    /**
     * 角色授權
     *
     * @param mids
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "角色授權失敗", operation = "角色授權")
    @ApiOperation(value = "角色授權")
    @RequiresPermissions({"role:authority"})
    @PostMapping("/authority/{id}")
    public ResponseBean authority(@PathVariable Long id, @RequestBody Long[] mids) throws SystemException {
        roleService.authority(id, mids);
        return ResponseBean.success();
    }

    /**
     * 角色拥有的選單權限id和選單树
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "角色選單")
    @GetMapping("/findRoleMenu/{id}")
    public ResponseBean<Map<String, Object>> findRoleMenu(@PathVariable Long id) throws SystemException {
        List<MenuNodeVO> tree = menuService.findMenuTree();
        //角色拥有的選單id
        List<Long> mids = roleService.findMenuIdsByRoleId(id);
        List<Long> ids = menuService.findOpenIds();
        Map<String, Object> map = new HashMap<>();
        map.put("tree", tree);
        map.put("mids", mids);
        map.put("open", ids);
        return ResponseBean.success(map);
    }

    /**
     * 角色列表
     *
     * @return
     */
    @ApiOperation(value = "角色列表")
    @GetMapping("/findRoleList")
    public ResponseBean<PageVO<RoleVO>> findRoleList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize,
                                     RoleVO roleVO) {
        PageVO<RoleVO> roleList = roleService.findRoleList(pageNum, pageSize, roleVO);
        return ResponseBean.success(roleList);
    }

    /**
     * 新增角色信息
     *
     * @param roleVO
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "新增角色失敗", operation = "新增角色")
    @ApiOperation(value = "新增角色")
    @RequiresPermissions({"role:add"})
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated RoleVO roleVO) throws SystemException {
        roleService.add(roleVO);
        return ResponseBean.success();
    }

    /**
     * 删除角色
     *
     * @param id 角色ID
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "删除角色失敗", operation = "删除角色")
    @ApiOperation(value = "删除角色", notes = "根據id删除角色信息")
    @RequiresPermissions({"role:delete"})
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) throws SystemException {
        roleService.deleteById(id);
        return ResponseBean.success();
    }


    /**
     * 編辑角色信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "編辑用戶", notes = "根據id更新角色信息")
    @GetMapping("/edit/{id}")
    @RequiresPermissions({"role:edit"})
    public ResponseBean<RoleVO> edit(@PathVariable Long id) throws SystemException {
        RoleVO roleVO = roleService.edit(id);
        return ResponseBean.success(roleVO);
    }

    /**
     * 更新角色
     *
     * @param id
     * @param roleVO
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新角色失敗", operation = "更新角色")
    @ApiOperation(value = "更新角色", notes = "根據id更新角色信息")
    @RequiresPermissions({"role:edit"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody @Validated RoleVO roleVO) throws SystemException {
        roleService.update(id, roleVO);
        return ResponseBean.success();
    }

    /**
     * 更新角色狀態
     *
     * @param id
     * @param status
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "角色更新狀態失敗", operation = "角色|禁用/啟用")
    @ApiOperation(value = "更新狀態", notes = "禁用和更新两种狀態")
    @RequiresPermissions({"role:status"})
    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseBean updateStatus(@PathVariable Long id, @PathVariable Boolean status) throws SystemException {
        roleService.updateStatus(id, status);
        return ResponseBean.success();
    }

    /**
     * 導出excel
     * @param response
     */
    @ApiOperation(value = "導出excel", notes = "導出所有角色的excel表格")
    @PostMapping("/excel")
    @RequiresPermissions("role:export")
    @ControllerEndpoint(exceptionMessage = "導出Excel失敗",operation = "導出角色excel")
    public void export(HttpServletResponse response) {
        List<Role> roles = this.roleService.findAll();
        List<RoleVO> voList = new ArrayList<>();
        roles.stream().forEach(d-> {
            RoleVO vo = new RoleVO();
            BeanUtils.copyProperties(d, vo);
            vo.setStatus(d.getStatus() == 1 ? false : true);
            voList.add(vo);
        });
        ExcelKit.$Export(RoleVO.class, response).downXlsx(voList, false);
    }


}
