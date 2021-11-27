package com.coderman.controller.business;

import com.coderman.business.service.ProductService;
import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.BusinessCodeEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.system.Department;
import com.coderman.common.model.system.Dictionary;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.system.DepartmentVO;
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
 * @Author zhangyukang
 * @Date 2020/3/17 09:19
 * @Version 1.0
 **/
@Api(tags = "業務模塊-廢棄物資料相关接口")
@RestController
@RequestMapping("/business/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    /**
     * 全部廢棄物列表
     *
     * @return
     */
    @ApiOperation(value = "廢棄物列表", notes = "廢棄物列表,根據廢棄物名模糊查询")
    @GetMapping("/findProductList")
    public ResponseBean findProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize") Integer pageSize,
                                        @RequestParam(value = "categorys", required = false) String categorys,
                                        ProductVO productVO) {
        buildCategorySearch(categorys, productVO);
        PageVO<ProductVO> productVOPageVO = productService.findProductList(pageNum, pageSize, productVO);
        return ResponseBean.success(productVOPageVO);
    }

    /**
     * 可入库廢棄物(入库页面使用)
     *
     * @return
     */
    @ApiOperation(value = "可入库廢棄物列表", notes = "廢棄物列表,根據廢棄物名模糊查询")
    @GetMapping("/findProducts")
    public ResponseBean findProducts(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize") Integer pageSize,
                                     @RequestParam(value = "categorys", required = false) String categorys,
                                     ProductVO productVO) {
        productVO.setStatus(true);
        buildCategorySearch(categorys, productVO);
        PageVO<ProductVO> productVOPageVO = productService.findProductList(pageNum, pageSize, productVO);
        return ResponseBean.success(productVOPageVO);
    }

    /**
     * 库存列表
     *
     * @return
     */
    @ApiOperation(value = "库存列表", notes = "廢棄物列表,根據廢棄物名模糊查询")
    @GetMapping("/findProductStocks")
    public ResponseBean findProductStocks(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(value = "pageSize") Integer pageSize,
                                          @RequestParam(value = "categorys", required = false) String categorys,
                                          ProductVO productVO) {

        buildCategorySearch(categorys, productVO);
        PageVO<ProductStockVO> productVOPageVO = productService.findProductStocks(pageNum, pageSize, productVO);
        return ResponseBean.success(productVOPageVO);
    }


    /**
     * 所有库存(饼图使用)
     *
     * @return
     */
    @ApiOperation(value = "全部库存", notes = "廢棄物所有库存信息,饼图使用")
    @GetMapping("/findAllStocks")
    public ResponseBean findAllStocks(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize") Integer pageSize,
                                      @RequestParam(value = "categorys", required = false) String categorys,
                                      ProductVO productVO) {
        buildCategorySearch(categorys, productVO);
        List<ProductStockVO> list = productService.findAllStocks(pageNum, pageSize, productVO);
        return ResponseBean.success(list);
    }


    /**
     * 封装廢棄物查询条件
     *
     * @param categorys
     * @param productVO
     */
    private void buildCategorySearch(@RequestParam(value = "categorys", required = false) String categorys, ProductVO productVO) {
        if (categorys != null && !"".equals(categorys)) {
            String[] split = categorys.split(",");
            switch (split.length) {
                case 1:
                    productVO.setOneCategoryId(Long.parseLong(split[0]));
                    break;
                case 2:
                    productVO.setOneCategoryId(Long.parseLong(split[0]));
                    productVO.setTwoCategoryId(Long.parseLong(split[1]));
                    break;
                case 3:
                    productVO.setOneCategoryId(Long.parseLong(split[0]));
                    productVO.setTwoCategoryId(Long.parseLong(split[1]));
                    productVO.setThreeCategoryId(Long.parseLong(split[2]));
                    break;
            }
        }
    }


    /**
     * 新增廢棄物
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "新增廢棄物失敗", operation = "新增廢棄物")
    @ApiOperation(value = "新增廢棄物")
    @RequiresPermissions({"product:add"})
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated ProductVO productVO) throws BusinessException {
        if (productVO.getCategoryKeys().length != 2) {
            throw new BusinessException(BusinessCodeEnum.PARAMETER_ERROR, "廢棄物需要2级分類");
        }
        try {
            productService.add(productVO);
        } catch (Exception e){
            return ResponseBean.error("廢棄物名稱重複");
        }
        return ResponseBean.success();
    }

    /**
     * 編辑廢棄物
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "編辑廢棄物", notes = "編辑廢棄物信息")
    @RequiresPermissions({"product:edit"})
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Long id) {
        ProductVO productVO = productService.edit(id);
        return ResponseBean.success(productVO);
    }

    /**
     * 更新廢棄物
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新廢棄物失敗", operation = "廢棄物資料更新")
    @ApiOperation(value = "更新廢棄物", notes = "更新廢棄物信息")
    @RequiresPermissions({"product:edit"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody ProductVO productVO) throws BusinessException {
        if (productVO.getCategoryKeys().length != 2) {
            throw new BusinessException(BusinessCodeEnum.PARAMETER_ERROR, "廢棄物需要2级分類");
        }
        try {
            productService.update(id, productVO);
        } catch (Exception e){
            return ResponseBean.error("廢棄物名稱重複");
        }
        return ResponseBean.success();
    }

    /**
     * 删除廢棄物
     *
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "删除廢棄物失敗", operation = "廢棄物資料删除")
    @ApiOperation(value = "删除廢棄物", notes = "删除廢棄物信息")
    @RequiresPermissions({"product:delete"})
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) throws BusinessException {
        productService.delete(id);
        return ResponseBean.success();
    }


    /**
     * 移入回收站
     *
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "回收廢棄物失敗", operation = "廢棄物資料回收")
    @ApiOperation(value = "移入回收站", notes = "移入回收站")
    @RequiresPermissions({"product:remove"})
    @PutMapping("/remove/{id}")
    public ResponseBean remove(@PathVariable Long id) throws BusinessException {
        productService.remove(id);
        return ResponseBean.success();
    }

    /**
     * 廢棄物新增审核
     *
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "廢棄物新增审核失敗", operation = "廢棄物資料审核")
    @ApiOperation(value = "廢棄物新增审核", notes = "廢棄物新增审核")
    @RequiresPermissions({"product:publish"})
    @PutMapping("/publish/{id}")
    public ResponseBean publish(@PathVariable Long id) throws BusinessException {
        productService.publish(id);
        return ResponseBean.success();
    }

    /**
     * 恢复数據从回收站
     *
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "恢复廢棄物失敗", operation = "廢棄物資料恢复")
    @ApiOperation(value = "恢复廢棄物", notes = "从回收站中恢复廢棄物")
    @RequiresPermissions({"product:back"})
    @PutMapping("/back/{id}")
    public ResponseBean back(@PathVariable Long id) throws BusinessException {
        productService.back(id);
        return ResponseBean.success();
    }

    /**
     * 更新狀態
     *
     * @param id
     * @param status
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新廢棄物狀態失敗", operation = "廢棄物|禁用/啟用")
    @ApiOperation(value = "廢棄物狀態", notes = "禁用和啟用兩種狀態")
    @RequiresPermissions({"product:status"})
    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseBean updateStatus(@PathVariable Long id, @PathVariable Boolean status) throws SystemException {
        productService.updateStatus(id, status);
        return ResponseBean.success();
    }

    /**
     * 成本中心分配
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "成本中心分配失敗", operation = "成本中心分配")
    @ApiOperation(value = "成本中心分配", notes = "成本中心分配")
    @RequiresPermissions({"product:costCenter"})
    @PutMapping("/costCenter/{ids}/{costCenterId}")
    public ResponseBean assignCostCenter(@PathVariable String ids, @PathVariable Long costCenterId) throws BusinessException, SystemException {
        String[] idList = ids.split(",");
        List<Long> list = new ArrayList<>();
        if (idList.length > 0) {
            for (String s : idList) {
                list.add(Long.parseLong(s));
            }
        }
        productService.updateCostCenter(list, costCenterId);
        return ResponseBean.success();
    }

    /**
     * 導出excel
     * @param response
     */
    @ApiOperation(value = "導出excel", notes = "導出所有廢棄物的excel表格")
    @PostMapping("/excel")
    @RequiresPermissions("product:export")
    @ControllerEndpoint(exceptionMessage = "導出Excel失敗",operation = "導出廢棄物excel")
    public void export(HttpServletResponse response) {
        List<ProductVO> voList = this.productService.findAll();
        ExcelKit.$Export(ProductVO.class, response).downXlsx(voList, false);
    }
}


