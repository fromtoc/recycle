package com.coderman.common.vo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/9 16:22
 * @Version 1.0
 **/
@Excel("role")
@Data
public class RoleVO {

    private Long id;

    private Integer type;
    @ExcelField(value = "角色名稱", width = 100)
    @NotBlank(message = "角色名必填")
    private String roleName;
    @ExcelField(value = "備註", width = 100)
    private String remark;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;
    @ExcelField(value = "最後修改時間", width = 100)
    private Date modifiedTime;

    private Boolean status;
    @ExcelField(value = "資料權限", width = 100)
    private String typeName;

    public String getTypeName() {
        if (type==null){
            return null;
        }
        return type==0? "所有資料": "限本帳號";
    }
    @ExcelField(value = "狀態", width = 100)
    private String statusName;

    public String getStatusName() {
        if (status==null){
            return null;
        }
        return status? "停用": "啟用";
    }

}
