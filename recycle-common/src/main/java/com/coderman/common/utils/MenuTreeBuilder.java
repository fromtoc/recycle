package com.coderman.common.utils;


import com.coderman.common.vo.system.MenuNodeVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 該類用于递归构建树形選單
 * Created by zhangyukang on 2020/2/6 15:34
 */
public class MenuTreeBuilder {


    /**
     * 构建多级選單树
     * @param nodes
     * @return
     */
    public static List<MenuNodeVO> build(List<MenuNodeVO> nodes){
        //根節點
        List<MenuNodeVO> rootMenu = new ArrayList<>();
        for (MenuNodeVO nav : nodes) {
            if(nav.getParentId()==0){
                rootMenu.add(nav);
            }
        }
        /* 根據Menu類的order排序 */
        Collections.sort(rootMenu,MenuNodeVO.order());
        /*为根選單设置子選單，getChild是递归调用的*/
        for (MenuNodeVO nav : rootMenu) {
            /* 获取根節點下的所有子節點 使用getChild方法*/
            List<MenuNodeVO> childList = getChild(nav.getId(), nodes);
            nav.setChildren(childList);//给根節點设置子節點
        }
        return rootMenu;
    }

    /**
     * 获取子選單
     * @param id
     * @param nodes
     * @return
     */
    private static List<MenuNodeVO> getChild(Long id, List<MenuNodeVO> nodes) {
        //子選單
        List<MenuNodeVO> childList = new ArrayList<MenuNodeVO>();
        for (MenuNodeVO nav : nodes) {
            // 遍历所有節點，将所有選單的父id与传过来的根節點的id比较
            //相等说明：为該根節點的子節點。
            if(nav.getParentId().equals(id)){
                childList.add(nav);
            }
        }
        //递归
        for (MenuNodeVO nav : childList) {
            nav.setChildren(getChild(nav.getId(), nodes));
        }
        Collections.sort(childList,MenuNodeVO.order());//排序
        //如果節點下没有子節點，返回一个空List（递归退出）
        if(childList.size() == 0){
            return new ArrayList<MenuNodeVO>();
        }
        return childList;
    }


}
