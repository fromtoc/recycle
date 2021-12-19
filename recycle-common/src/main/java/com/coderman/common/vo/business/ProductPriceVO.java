package com.coderman.common.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:16
 * @Version 1.0
 **/
@Excel("price")
@Data
public class ProductPriceVO {

    private Long id;

    private Long[] categoryKeys;

    private Long oneCategoryId;

    private Long twoCategoryId;
    @ExcelField(value = "廢棄物小分類", width = 100)
    private String twoCategoryName;

    private Long threeCategoryId;

    private Long productId;
    @ExcelField(value = "廢棄物名稱", width = 100)
    private String name;
    @ExcelField(value = "單價", width = 100)
    private BigDecimal price;
    @ExcelField(value = "單位", width = 100)
    private String unit;
    @ExcelField(value = "適用月份", width = 100)
    private String validMonth;

    private String oneCategoryName;

    private String threeCategoryName;

    private Boolean recover;
}
