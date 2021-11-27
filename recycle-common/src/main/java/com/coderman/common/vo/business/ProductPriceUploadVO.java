package com.coderman.common.vo.business;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor//添加全参构造函数
@NoArgsConstructor//添加午无参构造函数
public class ProductPriceUploadVO {

    @ExcelProperty(value = "廢棄物名稱",index = 0)
    private String name;
    @ExcelProperty(value = "單價",index = 1)
    private BigDecimal price;
    @ExcelProperty(value = "單位",index = 2)
    private String unit;
    @ExcelProperty(value = "適用月份",index = 3)
    private String validMonth;
}