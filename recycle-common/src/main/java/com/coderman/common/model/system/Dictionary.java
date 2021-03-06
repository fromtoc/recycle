package com.coderman.common.model.system;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Excel("dictionary")
@Data
@Table(name = "tb_dictionary")
public class Dictionary {
    @Id
    private Long id;

    private Integer type;

    @ExcelField(value = "代碼", width = 80)
    private String code;

    @ExcelField(value = "名稱", width = 150)
    private String value;

    private Integer status;

    private Date loadTime;

}
