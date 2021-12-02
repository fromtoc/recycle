package com.coderman.common.vo.system;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Excel(value = "menu")
@Table(name = "tb_menu")
public class MenuExportVO {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ExcelField(value = "一級選單", width = 150)
    private String firstLevelName;

    @ExcelField(value = "二級選單", width = 150)
    private String secondLevelName;

    @ExcelField(value = "三級按鈕", width = 150)
    private String thirdLevelName;

    private Long parentId;

    private String menuName;

    private String url;

    private String icon;

    private Integer open;

    private Integer type;

    @ExcelField(value = "排序", width = 90)
    private Long orderNum;

    @ExcelField(value = "層級", width = 100)
    private String level;

    private Date createTime;

    private Date modifiedTime;

    private Integer available;

    private String perms;

    private Date loadTime;
    @ExcelField(value = "狀態", width = 100)
    private String statusName;

    public String getStatusName() {
        if (available==null){
            return null;
        }
        return available==0? "停用": "啟用";
    }
}
