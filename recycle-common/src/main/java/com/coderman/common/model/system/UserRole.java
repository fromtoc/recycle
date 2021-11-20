package com.coderman.common.model.system;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_user_role")
public class UserRole {

    @Id
    private Long userId;

    private Long roleId;

    private Date loadTime;
}
