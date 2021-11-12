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
public class UserCardVO {

    private Long id;

    private Long userId;

    private String cardName;

    private String departmentCategoryName;

    private String departmentName;

    private String regionName;

    private Boolean status;

}
