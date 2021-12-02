package com.coderman.common.vo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/15 14:13
 * @Version 1.0
 **/
@Excel("department")
@Data
public class DepartmentVO {

    private Long id;

    private Long typeId;

    private String typeCode;
    @ExcelField(value = "公司類型", width = 100)
    private String typeCodeName;

    private String typeName;

    private Long number;

    private String typeNumber;
    @ExcelField(value = "公司名稱", width = 100)
    private String name;
    @ExcelField(value = "公司簡稱", width = 100)
    private String nickname;
    @ExcelField(value = "聯絡人姓名", width = 100)
    private String contact;
    @ExcelField(value = "聯絡人市話", width = 100)
    private String phone;
    @ExcelField(value = "聯絡人手機", width = 100)
    private String cellPhone;
    @ExcelField(value = "E-mail郵件信箱", width = 100)
    private String email;
    @ExcelField(value = "備註", width = 100)
    private String remark;

    private Boolean food;

    private Boolean status;

    private String address;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    /** 部門内人数**/
    private int total;
    @ExcelField(value = "廚餘標記", width = 100)
    private String foodName;

    public String getFoodName() {
        if (food==null){
            return null;
        }
        return food? "是": "否";
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
