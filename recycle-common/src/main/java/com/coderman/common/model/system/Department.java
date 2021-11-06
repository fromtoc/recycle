package com.coderman.common.model.system;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Excel("department")
@Data
@Table(name = "tb_department")
public class Department {
    @Id
    @ExcelField(value = "編號", width = 50)
    private Long id;

    @ExcelField(value = "公司類型id", width = 50)
    private Long typeId;

    @ExcelField(value = "公司類型名稱", width = 50)
    private String typeCode;

    @ExcelField(value = "流水編號", width = 50)
    private Long number;

    @ExcelField(value = "公司帳號", width = 100)
    private String typeNumber;

    @ExcelField(value = "公司名稱", width = 100)
    private String name;

    @ExcelField(value = "公司簡稱", width = 100)
    private String nickname;

    @ExcelField(value = "區域id", width = 50)
    private Long regionId;

    @ExcelField(value = "聯絡人姓名", width = 120)
    private String contact;

    @ExcelField(value = "電話", width = 120)
    private String phone;

    @ExcelField(value = "手機", width = 120)
    private String cellPhone;

    @ExcelField(value = "信箱", width = 120)
    private String email;

    @ExcelField(value = "備註", width = 120)
    private String remark;

    @ExcelField(value = "廚餘標記", width = 120)
    private Integer food;

    @ExcelField(value = "狀態", width = 120)
    private Integer status;

    @ExcelField(value = "地址", width = 150)
    private String address;

    @ExcelField(value = "創建時間", dateFormat = "yyyy年MM月dd日 HH:mm:ss", width = 180)
    private Date createTime;

    @ExcelField(value = "修改時間", dateFormat = "yyyy年MM月dd日 HH:mm:ss", width = 180)
    private Date modifiedTime;

}
