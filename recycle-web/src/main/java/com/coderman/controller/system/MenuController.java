package com.coderman.controller.system;

import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Menu;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.system.MenuExportVO;
import com.coderman.common.vo.system.MenuNodeVO;
import com.coderman.common.vo.system.MenuVO;
import com.coderman.system.service.MenuService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * @Date 2020/3/10 11:51
 * @Version 1.0
 **/
@Api(tags = "系统模塊-選單權限相关接口")
@RequestMapping("/system/menu")
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 加载選單树
     *
     * @return
     */
    @ApiOperation(value = "加载選單树", notes = "获取所有選單树，以及展开项")
    @GetMapping("/tree")
    public ResponseBean<Map<String, Object>> tree() {
        List<MenuNodeVO> menuTree = menuService.findMenuTree();
        List<Long> ids = menuService.findOpenIds();
        Map<String, Object> map = new HashMap<>();
        map.put("tree", menuTree);
        map.put("open", ids);
        return ResponseBean.success(map);
    }

    /**
     * 新增選單/按钮
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "新增選單/按钮失敗", operation = "新增選單/按钮")
    @ApiOperation(value = "新增選單")
    @RequiresPermissions({"menu:add"})
    @PostMapping("/add")
    public ResponseBean<Map<String, Object>> add(@RequestBody @Validated MenuVO menuVO) {
        Menu node = menuService.add(menuVO);
        Map<String, Object> map = new HashMap<>();
        map.put("id", node.getId());
        map.put("menuName", node.getMenuName());
        map.put("children", new ArrayList<>());
        map.put("icon", node.getIcon());
        return ResponseBean.success(map);
    }

    /**
     * 删除選單/按钮
     *
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "删除選單/按钮失敗", operation = "删除選單/按钮")
    @ApiOperation(value = "删除選單", notes = "根據id删除選單節點")
    @RequiresPermissions({"menu:delete"})
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) throws SystemException {
        menuService.delete(id);
        return ResponseBean.success();
    }

    /**
     * 選單详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "選單详情", notes = "根據id編辑選單，获取選單详情")
    @RequiresPermissions({"menu:edit"})
    @GetMapping("/edit/{id}")
    public ResponseBean<MenuVO> edit(@PathVariable Long id) throws SystemException {
        MenuVO menuVO = menuService.edit(id);
        return ResponseBean.success(menuVO);
    }

    /**
     * 更新選單
     *
     * @param id
     * @param menuVO
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新選單失敗", operation = "更新選單")
    @ApiOperation(value = "更新選單", notes = "根據id更新選單節點")
    @RequiresPermissions({"menu:edit"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody @Validated MenuVO menuVO) throws SystemException {
        menuService.update(id, menuVO);
        return ResponseBean.success();
    }

    /**
     * 導出excel
     * @param response
     */
    @ApiOperation(value = "導出excel", notes = "導出所有選單的excel表格")
    @PostMapping("excel")
    @RequiresPermissions("menu:export")
    @ControllerEndpoint(exceptionMessage = "導出Excel失敗",operation = "導出選單excel")
    public void export(HttpServletResponse response) {
        List<MenuExportVO> menus = this.menuService.findAll();
        ExcelKit.$Export(MenuExportVO.class, response).downXlsx(menus, false);
    }

}
