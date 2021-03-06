package com.coderman.controller.system;

import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Dictionary;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.system.DictionaryVO;
import com.coderman.common.vo.system.PageVO;
import com.coderman.common.service.DictionaryService;
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
import java.util.List;

/**
 * 公司類型管理
 *
 * @Author zhangyukang
 * @Date 2020/3/15 14:11
 * @Version 1.0
 **/
@Api(tags = "系统模塊-公司類型相關接口")
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
    public ResponseBean<PageVO<DictionaryVO>> findByPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize") Integer pageSize,
                                                       Dictionary dictionary) {
        dictionary.setType(1);
        PageVO<DictionaryVO> departmentCategoryList = dictionaryService.findDictionaryList(pageNum, pageSize, dictionary);
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
        List<Dictionary> departmentCategoryList = dictionaryService.selectByType(1,1);
        return ResponseBean.success(departmentCategoryList);
    }

    /**
     * 新增公司類型
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "新增公司類型失敗", operation = "新增公司類型")
    @RequiresPermissions({"departmentCategory:add"})
    @ApiOperation(value = "新增公司類型")
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated Dictionary dictionary) {
        dictionary.setType(1);
        dictionary.setStatus(1);
        dictionaryService.add(dictionary);
        return ResponseBean.success();
    }

    /**
     * 編辑公司類型
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "編辑公司類型")
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
    @ControllerEndpoint(exceptionMessage = "更新公司類型失敗", operation = "更新公司類型")
    @ApiOperation(value = "更新公司類型")
    @RequiresPermissions({"departmentCategory:edit"})
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
    @ControllerEndpoint(exceptionMessage = "删除公司類型失敗", operation = "删除公司類型")
    @ApiOperation(value = "删除公司類型")
    @RequiresPermissions({"departmentCategory:delete"})
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) throws SystemException {
        dictionaryService.delete(id);
        return ResponseBean.success();
    }

    /**
     * 導出excel
     *
     * @param response
     */
    @ApiOperation(value = "導出excel", notes = "導出所有公司類型的excel表格")
    @PostMapping("/excel")
    @RequiresPermissions("departmentCategory:export")
    @ControllerEndpoint(exceptionMessage = "導出Excel失敗", operation = "導出公司類型excel")
    public void export(HttpServletResponse response) throws SystemException {
        List<Dictionary> list = this.dictionaryService.selectByType(1, null);
        List<DictionaryVO> voList = new ArrayList<>();
        list.stream().forEach(d-> {
            DictionaryVO vo = new DictionaryVO();
            BeanUtils.copyProperties(d, vo);
            vo.setStatus(d.getStatus()==1? false : true);
            voList.add(vo);
        });
        ExcelKit.$Export(DictionaryVO.class, response).downXlsx(voList, false);
    }

    /**
     * 更新狀態
     *
     * @param id
     * @param status
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新公司類型狀態失敗", operation = "公司類型|禁用/啟用")
    @ApiOperation(value = "公司類型狀態", notes = "禁用和啟用这兩種狀態")
    @RequiresPermissions({"departmentCategory:status"})
    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseBean updateStatus(@PathVariable Long id, @PathVariable Boolean status) throws SystemException {
        dictionaryService.updateStatus(id, status);
        return ResponseBean.success();
    }

}
