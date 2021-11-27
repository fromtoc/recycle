package com.coderman.controller.business;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.coderman.business.service.ProductPriceService;
import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.BusinessException;
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
@Api(tags = "業務模塊-物資資料相关接口")
@RestController
@RequestMapping("/business/productPrice")
public class ProductPriceController {


    @Autowired
    private ProductPriceService productPriceService;

    /**
     * 全部物資列表
     *
     * @return
     */
    @ApiOperation(value = "單價列表", notes = "單價列表")
    @GetMapping("/findProductPriceList")
    public ResponseBean findProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize") Integer pageSize,
                                        @RequestParam(value = "categorys", required = false) String categorys,
                                        ProductPriceVO productPriceVO) {
        buildCategorySearch(categorys, productPriceVO);
        PageVO<ProductPriceVO> productPriceVOPageVO = productPriceService.findProductPriceList(pageNum, pageSize, productPriceVO);
        return ResponseBean.success(productPriceVOPageVO);
    }

    /**
     * 封装物資查询条件
     *
     * @param categorys
     * @param productPriceVO
     */
    private void buildCategorySearch(@RequestParam(value = "categorys", required = false) String categorys, ProductPriceVO productPriceVO) {
        if (categorys != null && !"".equals(categorys)) {
            String[] split = categorys.split(",");
            switch (split.length) {
                case 1:
                    productPriceVO.setOneCategoryId(Long.parseLong(split[0]));
                    break;
                case 2:
                    productPriceVO.setOneCategoryId(Long.parseLong(split[0]));
                    productPriceVO.setTwoCategoryId(Long.parseLong(split[1]));
                    break;
                case 3:
                    productPriceVO.setOneCategoryId(Long.parseLong(split[0]));
                    productPriceVO.setTwoCategoryId(Long.parseLong(split[1]));
                    productPriceVO.setThreeCategoryId(Long.parseLong(split[2]));
                    break;
            }
        }
    }


    /**
     * 新增物資
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "新增單價維護失敗", operation = "單價維護新增")
    @ApiOperation(value = "新增單價維護")
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated ProductPriceVO productPriceVO) throws BusinessException {
        int add = productPriceService.add(productPriceVO);
        if (add != 1 ) {
            return ResponseBean.error("廢棄物名稱不存在");
        }
        return ResponseBean.success();
    }

    /**
     * 編辑物資
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "編辑單價", notes = "編辑單價")
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Long id) {
        ProductPriceVO productPriceVO = productPriceService.edit(id);
        return ResponseBean.success(productPriceVO);
    }

    /**
     * 更新物資
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "編輯單價失敗", operation = "單價資料編輯")
    @ApiOperation(value = "編輯單價", notes = "編輯單價信息")
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody ProductPriceVO productPriceVO) throws BusinessException {
        productPriceService.update(id, productPriceVO);
        return ResponseBean.success();
    }

    /**
     * 導出excel
     * @param response
     */
    @ApiOperation(value = "導出excel", notes = "導出所有單價的excel表格")
    @PostMapping("/excel")
    @ControllerEndpoint(exceptionMessage = "導出Excel失敗",operation = "導出單價excel")
    public void export(HttpServletResponse response) {
        List<ProductPriceVO> voList = this.productPriceService.findAll();
        ExcelKit.$Export(ProductPriceVO.class, response).downXlsx(voList, false);
    }

    /**
     * @Description  Excel文件通知信息导入
     * @author ztt
     * @date 2021-07-21 15:18
     * @param file 文件流
     */
    @PostMapping(value = "/import")
    public ResponseBean productPriceImport(MultipartFile file) throws IOException {
        List<ProductPriceUploadVO> entityList = new ArrayList<>();
        // 這裡需要指定讀用哪個class去讀，然後讀取第一個sheet檔案流會自動關閉
        // excel中表的列要與物件的欄位相對應
        EasyExcel.read(file.getInputStream(), ProductPriceUploadVO.class, new AnalysisEventListener<ProductPriceUploadVO>() {
            // 每解析一條資料都會呼叫該方法
            @Override
            public void invoke(ProductPriceUploadVO entity, AnalysisContext analysisContext) {
                entityList.add(entity);
            }
            // 解析完畢的回撥方法
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("excel檔案讀取完畢！");
            }
        }).sheet().doRead();
        if (!CollectionUtils.isEmpty(entityList)){
            List<ProductPriceUploadVO> emptyList = productPriceService.checkNotExist(entityList);
            if (!CollectionUtils.isEmpty(emptyList)){
                Map map = new HashMap<>();
                map.put("type", "empty");
                map.put("list", emptyList);
                return ResponseBean.error(map);
            }
            List<ProductPriceVO> sameList = productPriceService.checkSame(entityList);
            if (!CollectionUtils.isEmpty(sameList)){
                Map map = new HashMap<>();
                map.put("type", "same");
                map.put("list", sameList);
                map.put("entityList", entityList);
                return ResponseBean.error(map);
            }
            int i = productPriceService.batchAdd(entityList);
            return ResponseBean.success(i);
        }
        Map map = new HashMap<>();
        map.put("type", "default");
        map.put("message", "批次上傳失敗");
        return ResponseBean.error(map);
    }

    /**
     * 新增物資
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "批次維護單價失敗", operation = "批次維護單價")
    @ApiOperation(value = "批次維護單價")
    @PostMapping("/recover")
    public ResponseBean recover(@RequestBody List<ProductPriceUploadVO> productPriceVOList) throws BusinessException {
        int add = productPriceService.recover(productPriceVOList);
        if (add != productPriceVOList.size() ) {
            return ResponseBean.error("廢棄物批次上傳失敗");
        }
        return ResponseBean.success();
    }

}


