package com.coderman.common.model.system;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "tb_user_card")
public class UserCard {

    private Long id;

    private Long userId;

    private String cardId;

    private Integer status;
}
