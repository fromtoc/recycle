package com.coderman.controller.system;

import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Dictionary;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.system.PageVO;
import com.coderman.common.service.DictionaryService;
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
 * 公司類型管理
 *
 * @Author zhangyukang
 * @Date 2020/3/15 14:11
 * @Version 1.0
 **/
@Api(tags = "系统模块-公司類型相關接口")
@RestController
@RequestMapping("/system/departmentCategory")
public class DepartmentCategoryController {


    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 公司類型列表
     *
     * @return
     */
    @ApiOperation(value = "公司類型列表", notes = "公司類型列表,根據公司類型名模糊查询")
    @GetMapping("/findByPage")
    public ResponseBean<PageVO<Dictionary>> findByPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize") Integer pageSize,
                                                       Dictionary dictionary) {
        dictionary.setType(1);
        PageVO<Dictionary> departmentCategoryList = dictionaryService.findDictionaryList(pageNum, pageSize, dictionary);
        return ResponseBean.success(departmentCategoryList);
    }

    /**
     * 所有公司類型
     *
     * @return
     */
    @ApiOperation(value = "所有公司類型")
    @GetMapping("/findAll")
    public ResponseBean<List<Dictionary>> findAll() throws SystemException {
        List<Dictionary> departmentCategoryList = dictionaryService.selectByType(1);
        return ResponseBean.success(departmentCategoryList);
    }

    /**
     * 添加公司類型
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "添加公司類型失败", operation = "添加公司類型")
    @RequiresPermissions({"departmentCategory:add"})
    @ApiOperation(value = "添加公司類型")
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated Dictionary dictionary) {
        dictionary.setType(1);
        dictionary.setStatus(1);
        dictionaryService.add(dictionary);
        return ResponseBean.success();
    }

    /**
     * 编辑公司類型
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "编辑公司類型")
    @RequiresPermissions({"departmentCategory:edit"})
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Long id) throws SystemException {
        Dictionary dictionary = dictionaryService.selectByPrimaryKey(id);
        return ResponseBean.success(dictionary);
    }

    /**
     * 更新公司類型
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新公司類型失败", operation = "更新公司類型")
    @ApiOperation(value = "更新公司類型")
    @RequiresPermissions({"departmentCategory:update"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody @Validated Dictionary dictionary) throws SystemException {
        dictionaryService.update(id, dictionary);
        return ResponseBean.success();
    }

    /**
     * 删除公司類型
     *
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "删除公司類型失败", operation = "删除公司類型")
    @ApiOperation(value = "删除公司類型")
    @RequiresPermissions({"departmentCategory:delete"})
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) throws SystemException {
        dictionaryService.delete(id);
        return ResponseBean.success();
    }

    /**
     * 导出excel
     *
     * @param response
     */
    @ApiOperation(value = "导出excel", notes = "导出所有公司類型的excel表格")
    @PostMapping("/excel")
    @RequiresPermissions("DepartmentCategory:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败", operation = "导出公司類型excel")
    public void export(HttpServletResponse response) throws SystemException {
        List<Dictionary> departmentCategoryList = this.dictionaryService.selectByType(1);
        ExcelKit.$Export(Dictionary.class, response).downXlsx(departmentCategoryList, false);
    }

}
