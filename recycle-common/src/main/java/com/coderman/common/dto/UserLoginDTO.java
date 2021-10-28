package com.coderman.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author zhangyukang
 * @Date 2020/12/16 21:40
 * @Version 1.0
 **/
@Data
@ApiModel(value = "用戶登入表单")
public class UserLoginDTO {
    @NotBlank(message = "用戶名不能为空")
    @ApiModelProperty(value = "用戶名")
    private String username;
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;
}
