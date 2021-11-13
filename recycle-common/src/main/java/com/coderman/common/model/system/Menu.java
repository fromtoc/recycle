package com.coderman.common.model.system;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Excel(value = "選單表格")
@Table(name = "tb_menu")
public class Menu {

    @Id
    @ExcelField(value = "編號", width = 50)
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @ExcelField(value = "父級id", width = 50)
    private Long parentId;

    @ExcelField(value = "選單名稱", width = 100)
    private String menuName;

    @ExcelField(value = "選單url", width = 100)
    private String url;

    @ExcelField(value = "選單圖標", width = 80)
    private String icon;

    @ExcelField(value = "是否展開", width = 50)
    private Integer open;

    @ExcelField(value = "選單類型", width = 80)
    private Integer type;

    @ExcelField(value = "排序", width = 90)
    private Long orderNum;

    @ExcelField(value = "創建時間", dateFormat = "yyyy年MM月dd日 HH:mm:ss", width = 180)
    private Date createTime;

    @ExcelField(value = "修改時間", dateFormat = "yyyy年MM月dd日 HH:mm:ss", width = 180)
    private Date modifiedTime;

    @ExcelField(value = "是否可用",width = 80)
    private Integer available;

    @ExcelField(value = "權限編碼", width = 180)
    private String perms;
}
