package com.coderman.common.vo.system;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Excel("dictionary")
@Data
@Table(name = "tb_dictionary")
public class DictionaryVO {
    @Id
    private Long id;

    private Integer type;

    @ExcelField(value = "代碼", width = 80)
    private String code;

    @ExcelField(value = "名稱", width = 150)
    private String value;

    private Integer status;

    private Date loadTime;

    @ExcelField(value = "狀態", width = 100)
    private String statusName;

    public String getStatusName() {
        if (status==null){
            return null;
        }
        return status==0? "停用": "啟用";
    }

}
