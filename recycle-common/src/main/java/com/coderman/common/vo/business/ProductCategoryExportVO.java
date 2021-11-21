package com.coderman.common.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
@Excel("productCategory")
@Data
public class ProductCategoryExportVO {
    private Long id;

    /** 父级分類id*/
    @NotNull(message = "父级選單不能为空")
    private Long pid;
    @ExcelField(value = "廢棄物大分類", width = 100)
    private String pidName;
    @ExcelField(value = "廢棄物小分類", width = 100)
    @NotBlank(message = "類目名稱不能为空")
    private String name;
    @ExcelField(value = "排序", width = 100)
    @NotNull(message = "排序號不能为空")
    private Integer sort;
    @ExcelField(value = "創建時間", width = 150, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;
    @ExcelField(value = "修改時間", width = 150, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;
    @ExcelField(value = "備註", width = 100)
    private String remark;
    @ExcelField(value = "層級", width = 100)
    private String level;

    private Integer status;
    @ExcelField(value = "狀態", width = 100)
    private String statusName;

    public String getStatusName() {
        return status==0? "停用": "啟用";
    }






}
