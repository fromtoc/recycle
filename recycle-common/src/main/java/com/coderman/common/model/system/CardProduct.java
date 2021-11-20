package com.coderman.common.model.system;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_card_product")
public class CardProduct {

    private String cardId;

    private Long productId;

    private Date loadTime;
}
