package com.coderman.common.vo.system;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Excel("runText")
@Data
@Table(name = "tb_run_text")
public class RunTextVO {
    @Id
    private Long id;

    @ExcelField(value = "跑馬燈內容", width = 300)
    private String message;

    private Boolean status;

    private Date loadTime;
    @ExcelField(value = "狀態", width = 100)
    private String statusName;

    public String getStatusName() {
        if (status==null){
            return null;
        }
        return status? "停用": "啟用";
    }

}
