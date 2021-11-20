package com.coderman.common.model.system;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_user_card")
public class UserCard {

    @Id
    private Long id;

    private Long userId;

    private String cardName;

    private Integer status;

    private Date loadTime;
}
