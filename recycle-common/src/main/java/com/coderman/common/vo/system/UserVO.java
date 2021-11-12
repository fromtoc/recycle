package com.coderman.common.vo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Data
public class UserVO{

    private Long id;

    @NotBlank(message = "用戶帳號不能為空")
    private String username;

    @NotBlank(message = "用戶名稱不能為空")
    private String nickname;

    private String email;

    private String phoneNumber;

    private Boolean status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer sex;

    private Date birth;

    @NotBlank(message = "密碼不能為空")
    private String password;

    private String departmentName;

    private Long regionId;

    private String regionName;

    @NotNull(message = "公司id不能為空")
    private Long departmentId;

}
