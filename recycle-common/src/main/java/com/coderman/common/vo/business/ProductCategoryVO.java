package com.coderman.common.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
@Data
public class ProductCategoryVO {
    private Long id;

    @NotBlank(message = "类目名稱不能为空")
    private String name;

    @NotBlank(message = "类目备注不能为空")
    private String remark;

    @NotNull(message = "排序號不能为空")
    private Integer sort;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    /** 父级分类id*/
    @NotNull(message = "父级選單不能为空")
    private Long pid;

}
