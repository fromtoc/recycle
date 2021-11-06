package com.coderman.common.vo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/15 14:13
 * @Version 1.0
 **/
@Data
public class DepartmentVO {

    private Long id;

    private Long typeCode;

    private String typeName;

    private Long number;

    private String typeNumber;

    private String name;

    private String nickname;

    private Long regionId;

    private String contact;

    private String phone;

    private String cellPhone;

    private String email;

    private String remark;

    private Integer food;

    private Integer status;

    private String address;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;


    /** 部門内人数**/
    private int total;

}
