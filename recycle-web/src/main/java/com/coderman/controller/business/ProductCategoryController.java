package com.coderman.controller.business;

import com.coderman.business.service.ProductCategoryService;
import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemException;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.business.ProductCategoryExportVO;
import com.coderman.common.vo.business.ProductCategoryTreeNodeVO;
import com.coderman.common.vo.business.ProductCategoryVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.system.PageVO;
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
 * 物資分類管理
 *
 * @Author zhangyukang
 * @Date 2020/3/16 17:16
 * @Version 1.0
 **/
@Api(tags = "業務模塊-物資類别相关接口")
@RestController
@RequestMapping("/business/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;


    /**
     * 物資分類列表
     *
     * @return
     */
    @ApiOperation(value = "分類列表", notes = "物資分類列表,根據物資分類名模糊查询")
    @GetMapping("/findProductCategoryList")
    public ResponseBean findProductCategoryList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            ProductCategoryVO productCategoryVO) {

        PageVO<ProductCategoryVO> productCategoryList = productCategoryService.findProductCategoryList(pageNum, pageSize, productCategoryVO);
        return ResponseBean.success(productCategoryList);
    }

    /**
     * 分類树形结构(分页)
     *
     * @return
     */
    @ApiOperation(value = "分類树形结构")
    @GetMapping("/categoryTreeAll")
    public ResponseBean categoryTreeAll(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        PageVO<ProductCategoryTreeNodeVO> pageVO = productCategoryService.categoryTreeAll(pageNum, pageSize);
        return ResponseBean.success(pageVO);
    }

    /**
     * 分類树形结构(分页)
     *
     * @return
     */
    @ApiOperation(value = "分類树形结构")
    @GetMapping("/categoryTree")
    public ResponseBean categoryTree(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        PageVO<ProductCategoryTreeNodeVO> pageVO = productCategoryService.categoryTree(pageNum, pageSize);
        return ResponseBean.success(pageVO);
    }

    /**
     * 获取父级分類树：2级树
     *
     * @return
     */
    @ApiOperation(value = "父级分類树")
    @GetMapping("/getParentCategoryTree")
    public ResponseBean getParentCategoryTree() {
        List<ProductCategoryTreeNodeVO> parentTree = productCategoryService.getParentCategoryTree();
        return ResponseBean.success(parentTree);
    }

    /**
     * 查询所有分類
     *
     * @return
     */
    @ApiOperation(value = "所有分類")
    @GetMapping("/findAll")
    public ResponseBean findAll() {
        List<ProductCategoryVO> productCategoryVOS = productCategoryService.findAll();
        return ResponseBean.success(productCategoryVOS);
    }

    /**
     * 新增物資分類
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "廢棄物分類新增失敗", operation = "廢棄物分類新增")
    @RequiresPermissions({"productCategory:add"})
    @ApiOperation(value = "新增分類")
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated ProductCategoryVO productCategoryVO) {
            productCategoryService.add(productCategoryVO);
            return ResponseBean.success();
    }

    /**
     * 編辑物資分類
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "編辑分類")
    @RequiresPermissions({"productCategory:edit"})
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Long id) {
        ProductCategoryVO productCategoryVO = productCategoryService.edit(id);
        return ResponseBean.success(productCategoryVO);
    }

    /**
     * 更新物資分類
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "物資分類更新失敗", operation = "物資分類更新")
    @ApiOperation(value = "更新分類")
    @RequiresPermissions({"productCategory:edit"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody @Validated ProductCategoryVO productCategoryVO) {
        productCategoryService.update(id, productCategoryVO);
        return ResponseBean.success();
    }

    /**
     * 删除物資分類
     *
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "物資分類删除失敗", operation = "物資分類删除")
    @ApiOperation(value = "删除分類")
    @RequiresPermissions({"productCategory:delete"})
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) throws BusinessException {
        productCategoryService.delete(id);
        return ResponseBean.success();
    }

    /**
     * 更新狀態
     *
     * @param id
     * @param status
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新廢棄物類型狀態失敗", operation = "廢棄物類型|禁用/啟用")
    @ApiOperation(value = "廢棄物類型狀態", notes = "禁用和啟用这兩種狀態")
    @RequiresPermissions({"productCategory:status"})
    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseBean updateStatus(@PathVariable Long id, @PathVariable Boolean status) throws SystemException {
        productCategoryService.updateStatus(id, status);
        return ResponseBean.success();
    }

    /**
     * 導出excel
     * @param response
     */
    @ApiOperation(value = "導出excel", notes = "導出所有廢棄物類型的excel表格")
    @PostMapping("/excel")
    @RequiresPermissions("productCategory:export")
    @ControllerEndpoint(exceptionMessage = "導出Excel失敗",operation = "導出廢棄物類型excel")
    public void export(HttpServletResponse response) {
        List<ProductCategoryExportVO> voList = this.productCategoryService.getAll();
        ExcelKit.$Export(ProductCategoryExportVO.class, response).downXlsx(voList, false);
    }
}
