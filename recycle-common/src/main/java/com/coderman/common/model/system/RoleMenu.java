package com.coderman.common.model.system;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "tb_role_menu")
public class RoleMenu {
    private Long roleId;

    private Long menuId;

    private Date loadTime;

}
