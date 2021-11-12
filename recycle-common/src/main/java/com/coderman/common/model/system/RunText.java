package com.coderman.common.model.system;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Excel("runText")
@Data
@Table(name = "tb_run_text")
public class RunText {
    @Id
    private Long id;

    @ExcelField(value = "跑馬燈內容", width = 300)
    private String message;

    @ExcelField(value = "狀態", width = 50)
    private Integer status;

}
