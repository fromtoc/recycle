package com.coderman.common.vo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/9 11:06
 * @Version 1.0
 **/
@Data
public class UserEditVO {
    private Long id;

    @NotBlank(message = "用戶帳號不能為空")
    private String username;

    @NotBlank(message = "用戶名稱不能為空")
    private String nickname;

    private String email;

    private String phoneNumber;

    private Integer sex;

    private Date birth;

    @NotNull(message = "所屬公司不能為空")
    private Long departmentId;

    private Long regionId;

}
