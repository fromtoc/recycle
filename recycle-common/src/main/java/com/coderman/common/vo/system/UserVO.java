package com.coderman.common.vo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/7 19:16
 * @Version 1.0
 **/
@Excel("user")
@Data
public class UserVO{

    private Long id;

    @ExcelField(value = "用戶帳號", width = 100)
    @NotBlank(message = "用戶帳號不能為空")
    private String username;

    @ExcelField(value = "用戶名稱", width = 100)
    @NotBlank(message = "用戶名稱不能為空")
    private String nickname;

    @ExcelField(value = "E-mail信箱", width = 100)
    private String email;

    private String phoneNumber;

    private Boolean status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer sex;

    private Date birth;

    @NotBlank(message = "密碼不能為空")
    private String password;

    @ExcelField(value = "所屬公司", width = 100)
    private String departmentName;

    private Long regionId;

    @ExcelField(value = "區域", width = 100)
    private String regionName;

    @NotNull(message = "公司id不能為空")
    private Long departmentId;

    @ExcelField(value = "狀態", width = 100)
    private String statusName;

    public String getStatusName() {
        return status? "停用": "啟用";
    }



}
