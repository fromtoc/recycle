package com.coderman.controller.business;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.coderman.business.service.InputElementService;
import com.coderman.business.service.ProductPriceService;
import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.BusinessException;
import com.coderman.common.model.business.InputElement;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.business.ProductPriceUploadVO;
import com.coderman.common.vo.business.ProductPriceVO;
import com.coderman.common.vo.system.PageVO;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:19
 * @Version 1.0
 **/
@Api(tags = "相關資料補登")
@RestController
@RequestMapping("/business/inputElement")
public class InputElementController {


    @Autowired
    private InputElementService inputElementService;

    /**
     * @Description  相關資料補登
     * @author ztt
     * @date 2021-07-21 15:18
     * @param
     */
    @ControllerEndpoint(exceptionMessage = "相關資料補登", operation = "相關資料補登")
    @ApiOperation(value = "相關資料補登")
    @PostMapping("/add")
    public ResponseBean add(@RequestBody List<InputElement> inputElementList) throws BusinessException {
        if (!CollectionUtils.isEmpty(inputElementList)){
            List<InputElement> sameList = inputElementService.checkSame(inputElementList);
            if (!CollectionUtils.isEmpty(sameList)){
                Map map = new HashMap<>();
                map.put("type", "same");
                map.put("list", sameList);
                map.put("entityList", inputElementList);
                return ResponseBean.error(map);
            }
            int i = inputElementService.batchAdd(inputElementList);
            return ResponseBean.success(i);
        }
        Map map = new HashMap<>();
        map.put("type", "default");
        map.put("message", "新增補登資料失敗");
        return ResponseBean.error(map);
    }

    /**
     * 新增物資
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "覆蓋相關資料補登", operation = "覆蓋相關資料補登")
    @ApiOperation(value = "覆蓋相關資料補登")
    @PostMapping("/recover")
    public ResponseBean recover(@RequestBody List<InputElement> inputElementList) throws BusinessException {
        int add = inputElementService.recover(inputElementList);
        if (add != inputElementList.size() ) {
            return ResponseBean.error("新增補登資料失敗");
        }
        return ResponseBean.success();
    }

}


