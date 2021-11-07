package com.coderman.common.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:16
 * @Version 1.0
 **/
@Data
public class WeightVO {

    private Long id;

    private Long departmentId;

    private Long userId;

    private Long cardId;

    private Long productId;

    private BigDecimal totalWeight;

    private BigDecimal deductWeight;

    private BigDecimal netWeight;

    private Integer status;

    private Date createTime;

}
