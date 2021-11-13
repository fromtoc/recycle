package com.coderman.controller.business;

import com.coderman.business.service.ProductPriceService;
import com.coderman.business.service.ProductService;
import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.BusinessCodeEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.error.SystemException;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.business.ProductPriceVO;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.system.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:19
 * @Version 1.0
 **/
@Api(tags = "業務模塊-物资资料相关接口")
@RestController
@RequestMapping("/business/productPrice")
public class ProductPriceController {


    @Autowired
    private ProductPriceService productPriceService;

    /**
     * 全部物资列表
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
     * 封装物资查询条件
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
     * 新增物资
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "新增單價維護失败", operation = "單價維護新增")
    @ApiOperation(value = "新增單價維護")
    @RequiresPermissions({"productPrice:add"})
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated ProductPriceVO productPriceVO) throws BusinessException {
        int add = productPriceService.add(productPriceVO);
        if (add != 1 ) {
            return ResponseBean.error("廢棄物名稱不存在");
        }
        return ResponseBean.success();
    }

    /**
     * 編辑物资
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "編辑單價", notes = "編辑單價")
    @RequiresPermissions({"productPrice:edit"})
    @GetMapping("/edit/{id}")
    public ResponseBean edit(@PathVariable Long id) {
        ProductPriceVO productPriceVO = productPriceService.edit(id);
        return ResponseBean.success(productPriceVO);
    }

    /**
     * 更新物资
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "編輯單價失败", operation = "單價資料編輯")
    @ApiOperation(value = "編輯單價", notes = "編輯單價信息")
    @RequiresPermissions({"productPrice:update"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody ProductPriceVO productPriceVO) throws BusinessException {
        productPriceService.update(id, productPriceVO);
        return ResponseBean.success();
    }

}


