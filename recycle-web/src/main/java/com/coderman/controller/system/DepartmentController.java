package com.coderman.controller.system;

import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Department;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.system.DeanVO;
import com.coderman.common.vo.system.DepartmentVO;
import com.coderman.common.vo.system.PageVO;
import com.coderman.system.service.DepartmentService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 部門管理
 *
 * @Author zhangyukang
 * @Date 2020/3/15 14:11
 * @Version 1.0
 **/
@Api(tags = "系统模塊-部門相关接口")
@RestController
@RequestMapping("/system/department")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;

    /**
     * 部門列表
     *
     * @return
     */
    @ApiOperation(value = "部門列表", notes = "部門列表,根據部門名模糊查询")
    @GetMapping("/findDepartmentList")
    public ResponseBean<PageVO<DepartmentVO>> findDepartmentList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize") Integer pageSize,
                                           DepartmentVO departmentVO) {
        PageVO<DepartmentVO> departmentsList = departmentService.findDepartmentList(pageNum, pageSize, departmentVO);
        return ResponseBean.success(departmentsList);
    }

    /**
     * 所有部門
     *
     * @return
     */
    @ApiOperation(value = "所有部門")
    @GetMapping("/findAll")
    public ResponseBean<List<DepartmentVO>> findAll() {
        List<DepartmentVO> departmentVOS = departmentService.findAllVO();
        return ResponseBean.success(departmentVOS);
    }

    /**
     * 查找部門主任
     *
     * @return
     */
    @ApiOperation(value = "部門主任", notes = "查找部門主任,排除掉已经禁用的用戶")
    @GetMapping("/findDeanList")
    public ResponseBean<List<DeanVO>> findDeanList() {
        List<DeanVO> managerList = departmentService.findDeanList();
        return ResponseBean.success(managerList);
    }

    /**
     * 新增部門
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "新增部門失败", operation = "新增部門")
    @RequiresPermissions({"department:add"})
    @ApiOperation(value = "新增部門")
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated DepartmentVO departmentVO) {
        departmentService.add(departmentVO);
        return ResponseBean.success();
    }

    /**
     * 編辑部門
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "編辑部門")
    @RequiresPermissions({"department:edit"})
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Long id) throws SystemException {
        DepartmentVO departmentVO = departmentService.edit(id);
        return ResponseBean.success(departmentVO);
    }

    /**
     * 更新部門
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新部門失败", operation = "更新部門")
    @ApiOperation(value = "更新部門")
    @RequiresPermissions({"department:update"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody @Validated DepartmentVO departmentVO) throws SystemException {
        departmentService.update(id, departmentVO);
        return ResponseBean.success();
    }

    /**
     * 删除部門
     *
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "删除部門失败", operation = "删除部門")
    @ApiOperation(value = "删除部門")
    @RequiresPermissions({"department:delete"})
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) throws SystemException {
        departmentService.delete(id);
        return ResponseBean.success();
    }

    /**
     * 导出excel
     * @param response
     */
    @ApiOperation(value = "导出excel", notes = "导出所有部門的excel表格")
    @PostMapping("/excel")
    @RequiresPermissions("department:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败",operation = "导出部門excel")
    public void export(HttpServletResponse response) {
        List<Department> departments = this.departmentService.findAll();
        ExcelKit.$Export(Department.class, response).downXlsx(departments, false);
    }

    /**
     * 更新狀態
     *
     * @param id
     * @param status
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新公司狀態失敗", operation = "公司|禁用/啟用")
    @ApiOperation(value = "公司狀態", notes = "禁用和啟用这兩種狀態")
    @RequiresPermissions({"department:status"})
    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseBean updateStatus(@PathVariable Long id, @PathVariable Boolean status) throws SystemException {
        departmentService.updateStatus(id, status);
        return ResponseBean.success();
    }

    /**
     * 更新廚餘標記
     *
     * @param id
     * @param food
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新廚餘標記失敗", operation = "廚餘|標記/移除標記")
    @ApiOperation(value = "廚餘標記", notes = "廚餘標記")
    @RequiresPermissions({"department:food"})
    @PutMapping("/updateFood/{id}/{food}")
    public ResponseBean updateFood(@PathVariable Long id, @PathVariable Boolean food) throws SystemException {
        departmentService.updateFood(id, food);
        return ResponseBean.success();
    }

}
