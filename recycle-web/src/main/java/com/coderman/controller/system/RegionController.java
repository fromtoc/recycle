package com.coderman.controller.system;

import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Dictionary;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.service.DictionaryService;
import com.coderman.common.vo.system.DictionaryVO;
import com.coderman.common.vo.system.PageVO;
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
 * 區域管理
 *
 * @Author zhangyukang
 * @Date 2020/3/15 14:11
 * @Version 1.0
 **/
@Api(tags = "系统模塊-區域相關接口")
@RestController
@RequestMapping("/system/region")
public class RegionController {


    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 區域列表
     *
     * @return
     */
    @ApiOperation(value = "區域列表", notes = "區域列表,根據區域名模糊查询")
    @GetMapping("/findByPage")
    public ResponseBean<PageVO<Dictionary>> findByPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize") Integer pageSize,
                                                       Dictionary dictionary) {
        dictionary.setType(2);
        PageVO<Dictionary> regionList = dictionaryService.findDictionaryList(pageNum, pageSize, dictionary);
        return ResponseBean.success(regionList);
    }

    /**
     * 所有區域
     *
     * @return
     */
    @ApiOperation(value = "所有區域")
    @GetMapping("/findAll")
    public ResponseBean<List<Dictionary>> findAll() throws SystemException {
        List<Dictionary> regionList = dictionaryService.selectByType(2,1);
        return ResponseBean.success(regionList);
    }

    /**
     * 新增區域
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "新增區域失敗", operation = "新增區域")
    @RequiresPermissions({"region:add"})
    @ApiOperation(value = "新增區域")
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated Dictionary dictionary) {
        dictionary.setType(2);
        dictionary.setStatus(1);
        dictionaryService.add(dictionary);
        return ResponseBean.success();
    }

    /**
     * 編辑區域
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "編辑區域")
    @RequiresPermissions({"region:edit"})
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Long id) throws SystemException {
        Dictionary dictionary = dictionaryService.selectByPrimaryKey(id);
        return ResponseBean.success(dictionary);
    }

    /**
     * 更新區域
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新區域失敗", operation = "更新區域")
    @ApiOperation(value = "更新區域")
    @RequiresPermissions({"region:edit"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody @Validated Dictionary dictionary) throws SystemException {
        dictionaryService.update(id, dictionary);
        return ResponseBean.success();
    }

    /**
     * 删除區域
     *
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "删除區域失敗", operation = "删除區域")
    @ApiOperation(value = "删除區域")
    @RequiresPermissions({"region:delete"})
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
    @ApiOperation(value = "導出excel", notes = "導出所有區域的excel表格")
    @PostMapping("/excel")
    @RequiresPermissions("region:export")
    @ControllerEndpoint(exceptionMessage = "導出Excel失敗", operation = "導出區域excel")
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

    /**
     * 更新狀態
     *
     * @param id
     * @param status
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新區域狀態失敗", operation = "區域|禁用/啟用")
    @ApiOperation(value = "區域狀態", notes = "禁用和啟用这兩種狀態")
    @RequiresPermissions({"region:status"})
    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseBean updateStatus(@PathVariable Long id, @PathVariable Boolean status) throws SystemException {
        dictionaryService.updateStatus(id, status);
        return ResponseBean.success();
    }

}
