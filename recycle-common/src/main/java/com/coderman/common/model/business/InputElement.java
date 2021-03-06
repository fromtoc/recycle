package com.coderman.common.model.business;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "input_data")
public class InputElement {

    @Id
    private Long id;

    private Long itemId;

    private BigDecimal value;

    private String validMonth;

    private Date loadTime;

}