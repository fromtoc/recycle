package com.coderman.common.model.system;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "tb_card_product")
public class CardProduct {

    private Long cardId;

    private Long productId;
}
