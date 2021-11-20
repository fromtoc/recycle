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
 * 成本中心管理
 *
 * @Author zhangyukang
 * @Date 2020/3/15 14:11
 * @Version 1.0
 **/
@Api(tags = "系统模塊-成本中心相關接口")
@RestController
@RequestMapping("/system/costCenter")
public class CostCenterController {


    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 成本中心列表
     *
     * @return
     */
    @ApiOperation(value = "成本中心列表", notes = "成本中心列表,根據成本中心名模糊查询")
    @GetMapping("/findByPage")
    public ResponseBean<PageVO<Dictionary>> findByPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize") Integer pageSize,
                                                       Dictionary dictionary) {
        dictionary.setType(2);
        PageVO<Dictionary> list = dictionaryService.findDictionaryList(pageNum, pageSize, dictionary);
        return ResponseBean.success(list);
    }

    /**
     * 所有成本中心
     *
     * @return
     */
    @ApiOperation(value = "所有成本中心")
    @GetMapping("/findAll")
    public ResponseBean<List<Dictionary>> findAll() throws SystemException {
        List<Dictionary> list = dictionaryService.selectByType(2, 1);
        return ResponseBean.success(list);
    }

    /**
     * 新增成本中心
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "新增成本中心失败", operation = "新增成本中心")
    @RequiresPermissions({"costCenter:add"})
    @ApiOperation(value = "新增成本中心")
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated Dictionary dictionary) {
        dictionary.setType(2);
        dictionary.setStatus(1);
        dictionaryService.add(dictionary);
        return ResponseBean.success();
    }

    /**
     * 編辑成本中心
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "編辑成本中心")
    @RequiresPermissions({"costCenter:edit"})
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Long id) throws SystemException {
        Dictionary dictionary = dictionaryService.selectByPrimaryKey(id);
        return ResponseBean.success(dictionary);
    }

    /**
     * 更新成本中心
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新成本中心失败", operation = "更新成本中心")
    @ApiOperation(value = "更新成本中心")
    @RequiresPermissions({"costCenter:edit"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody @Validated Dictionary dictionary) throws SystemException {
        dictionaryService.update(id, dictionary);
        return ResponseBean.success();
    }

    /**
     * 删除成本中心
     *
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "删除成本中心失败", operation = "删除成本中心")
    @ApiOperation(value = "删除成本中心")
    @RequiresPermissions({"costCenter:delete"})
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
    @ApiOperation(value = "導出excel", notes = "導出所有成本中心的excel表格")
    @PostMapping("/excel")
    @RequiresPermissions("costCenter:export")
    @ControllerEndpoint(exceptionMessage = "導出Excel失败", operation = "導出成本中心excel")
    public void export(HttpServletResponse response) throws SystemException {
        List<Dictionary> list = this.dictionaryService.selectByType(2, null);
        List<DictionaryVO> voList = new ArrayList<>();
        list.stream().forEach(d-> {
            DictionaryVO vo = new DictionaryVO();
            BeanUtils.copyProperties(d, vo);
            voList.add(vo);
        });
        ExcelKit.$Export(DictionaryVO.class, response).downXlsx(voList, false);
    }

}
