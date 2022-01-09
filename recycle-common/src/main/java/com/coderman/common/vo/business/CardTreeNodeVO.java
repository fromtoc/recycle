package com.coderman.common.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 12:18
 * @Version 1.0
 **/
@Data
public class CardTreeNodeVO {
    private Long id;

    private String name;

//    private Integer sort;

    private List<CardTreeNodeVO> children;

//    /*
//     * 排序,根據order排序
//     */
//    public static Comparator<CardTreeNodeVO> order(){
//        Comparator<CardTreeNodeVO> comparator = (o1, o2) -> {
//            if(o1.getSort() != o2.getSort()){
//                return (int) (o1.getSort() - o2.getSort());
//            }
//            return 0;
//        };
//        return comparator;
//    }
}
