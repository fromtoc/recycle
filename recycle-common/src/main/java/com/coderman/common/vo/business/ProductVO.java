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
 * @Date 2020/3/17 09:16
 * @Version 1.0
 **/
@Excel("product")
@Data
public class ProductVO {

    private Long id;

    private String pNum;
    @ExcelField(value = "廢棄物名稱", width = 100)
    @NotBlank
    private String name;

    private Long model;

    private String unit;

    private String remark;

    private Integer sort;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    private String imageUrl;


    @NotNull(message = "分類不能为空")
    private Long[] categoryKeys;

    private Long oneCategoryId;

    private Long twoCategoryId;

    private Long threeCategoryId;
    @ExcelField(value = "廢棄物大分類", width = 100)
    private String oneCategoryName;
    @ExcelField(value = "廢棄物小分類", width = 100)
    private String twoCategoryName;

    private String threeCategoryName;

    private String modelName;

    private Boolean status;
    @ExcelField(value = "狀態", width = 100)
    private String statusName;

    public String getStatusName() {
        if (status==null){
            return null;
        }
        return status? "停用": "啟用";
    }

}
