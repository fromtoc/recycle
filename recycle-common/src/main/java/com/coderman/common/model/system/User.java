package com.coderman.common.model.system;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Excel("user")
@Table(name = "tb_user")
public class User {
    @Id
    @ExcelField(value = "編號", width = 50)
    private Long id;

    @ExcelField(value = "帳號", width = 100)
    private String username;

    @ExcelField(value = "名稱", width = 100)
    private String nickname;

    @ExcelField(value = "信箱", width = 150)
    private String email;

    @ExcelField(value = "电话號碼", width = 100)
    private String phoneNumber;

    private Integer status;

    @ExcelField(value = "创建時間", dateFormat = "yyyy年MM月dd日 HH:mm:ss", width = 180)
    private Date createTime;

    @ExcelField(value = "修改時間", dateFormat = "yyyy年MM月dd日 HH:mm:ss",width = 180)
    private Date modifiedTime;

    @ExcelField(//
            value = "性别",
            readConverterExp = "男=1,女=0",
            writeConverterExp = "1=男,0=女"
            ,width = 50
    )
    private Integer sex;

    @ExcelField(value = "密碼盐值", width = 100)
    private String salt;

    @ExcelField(//
            value = "用戶类型",
            readConverterExp = "超级管理員=0,普通用戶=1",
            writeConverterExp = "0=超级管理員,1=普通用戶"
            ,width = 80
    )
    private Integer type;

    @ExcelField(value = "用戶密碼", width = 100)
    private String password;

    @ExcelField(value = "出生日期", dateFormat = "yyyy/MM/dd",width = 100)
    private Date birth;

    private Long departmentId;

    @ExcelField(value = "头像url", width = 200)
    private String avatar;

}
