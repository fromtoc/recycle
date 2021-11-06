package com.coderman.common.model.business;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "tb_weight")
public class Weight {

    @Id
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
