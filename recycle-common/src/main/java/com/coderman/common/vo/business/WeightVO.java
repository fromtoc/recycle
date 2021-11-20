package com.coderman.common.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:16
 * @Version 1.0
 **/
@Excel("weight")
@Data
public class WeightVO {

    private Long id;

    private Long departmentId;

    private Long userId;

    private Long cardId;

    private Long productId;
    @ExcelField(value = "總重", width = 100)
    private BigDecimal totalWeight;
    @ExcelField(value = "扣重", width = 100)
    private BigDecimal deductWeight;
    @ExcelField(value = "淨重", width = 100)
    private BigDecimal netWeight;

    private Boolean status;
    @ExcelField(value = "日期", width = 150)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;
    @ExcelField(value = "公司名稱", width = 100)
    private String departmentName;
    @ExcelField(value = "用戶名稱", width = 100)
    private String userNickname;
    @ExcelField(value = "登入卡號", width = 100)
    private String cardName;
    @ExcelField(value = "廢棄物名稱", width = 100)
    private String productName;
    @ExcelField(value = "狀態", width = 100)
    private String statusName;

    public String getStatusName() {
        return status? "作廢": "啟用";
    }

}
