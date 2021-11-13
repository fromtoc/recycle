package com.coderman.common.model.business;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "biz_product_price")
public class ProductPrice {

    @Id
    private Long id;

    private Long oneCategoryId;

    private Long twoCategoryId;

    private Long threeCategoryId;

    private Long productId;

    private String name;

    private BigDecimal price;

    private String unit;

    private String validMonth;

}
