package com.coderman.controller.system;

import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.Dictionary;
import com.coderman.common.model.system.RunText;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.service.DictionaryService;
import com.coderman.common.vo.system.DictionaryVO;
import com.coderman.common.vo.system.PageVO;
import com.coderman.common.vo.system.RunTextVO;
import com.coderman.system.service.RunTextService;
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
 * 跑馬燈管理
 *
 * @Author zhangyukang
 * @Date 2020/3/15 14:11
 * @Version 1.0
 **/
@Api(tags = "系统模塊-跑馬燈相關接口")
@RestController
@RequestMapping("/system/runText")
public class RunTextController {


    @Autowired
    private RunTextService runTextService;

    /**
     * 跑馬燈列表
     *
     * @return
     */
    @ApiOperation(value = "跑馬燈列表", notes = "跑馬燈列表,根據跑馬燈名模糊查询")
    @GetMapping("/findByPage")
    public ResponseBean<PageVO<RunText>> findByPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize") Integer pageSize,
                                                    RunText runText) {
        PageVO<RunText> runTextList = runTextService.findRunTextList(pageNum, pageSize, runText);
        return ResponseBean.success(runTextList);
    }

    /**
     * 所有跑馬燈
     *
     * @return
     */
    @ApiOperation(value = "所有跑馬燈")
    @GetMapping("/findAll")
    public ResponseBean<List<RunText>> findAll() throws SystemException {
        List<RunText> runTextList = runTextService.findAll();
        return ResponseBean.success(runTextList);
    }

    /**
     * 新增跑馬燈
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "新增跑馬燈失败", operation = "新增跑馬燈")
    @RequiresPermissions({"runText:add"})
    @ApiOperation(value = "新增跑馬燈")
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated RunText runText) {
        runText.setStatus(1);
        runTextService.add(runText);
        return ResponseBean.success();
    }

    /**
     * 編辑跑馬燈
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "編辑跑馬燈")
    @RequiresPermissions({"runText:edit"})
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Long id) throws SystemException {
        RunText runText = runTextService.selectByPrimaryKey(id);
        return ResponseBean.success(runText);
    }

    /**
     * 更新跑馬燈
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新跑馬燈失败", operation = "更新跑馬燈")
    @ApiOperation(value = "更新跑馬燈")
    @RequiresPermissions({"runText:edit"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody @Validated RunText runText) throws SystemException {
        runTextService.update(id, runText);
        return ResponseBean.success();
    }

    /**
     * 導出excel
     *
     * @param response
     */
    @ApiOperation(value = "導出excel", notes = "導出所有跑馬燈的excel表格")
    @PostMapping("/excel")
    @RequiresPermissions("runText:export")
    @ControllerEndpoint(exceptionMessage = "導出Excel失败", operation = "導出跑馬燈excel")
    public void export(HttpServletResponse response) throws SystemException {
        List<RunText> runTextList = this.runTextService.findAll();
        List<RunTextVO> voList = new ArrayList<>();
        runTextList.stream().forEach(d-> {
            RunTextVO vo = new RunTextVO();
            BeanUtils.copyProperties(d, vo);
            voList.add(vo);
        });
        ExcelKit.$Export(RunTextVO.class, response).downXlsx(voList, false);
    }

    /**
     * 更新狀態
     *
     * @param id
     * @param status
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新跑馬燈狀態失敗", operation = "跑馬燈|禁用/啟用")
    @ApiOperation(value = "跑馬燈狀態", notes = "禁用和啟用这兩種狀態")
    @RequiresPermissions({"runText:status"})
    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseBean updateStatus(@PathVariable Long id, @PathVariable Boolean status) throws SystemException {
        runTextService.updateStatus(id, status);
        return ResponseBean.success();
    }

}
