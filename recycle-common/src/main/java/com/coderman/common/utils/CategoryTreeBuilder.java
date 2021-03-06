package com.coderman.common.utils;


import com.coderman.common.vo.business.ProductCategoryTreeNodeVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhangyukang on 2020/2/6 15:34
 */
public class CategoryTreeBuilder {

    private static int count=1;

    /**
     * 构建多级
     * @param nodes
     * @return
     */
    public static List<ProductCategoryTreeNodeVO> build(List<ProductCategoryTreeNodeVO> nodes){
        //根節點
        List<ProductCategoryTreeNodeVO> rootMenu = new ArrayList<>();
        for (ProductCategoryTreeNodeVO nav : nodes) {
            if(nav.getPid()==0){
                nav.setLev(1);
                rootMenu.add(nav);
            }
        }
        /* 根據Menu類的order排序 */
        Collections.sort(rootMenu,ProductCategoryTreeNodeVO.order());
        /*为根選單设置子選單，getChild是递归调用的*/
        for (ProductCategoryTreeNodeVO nav : rootMenu) {
            /* 获取根節點下的所有子節點 使用getChild方法*/
            List<ProductCategoryTreeNodeVO> childList = getChild(nav, nodes);
            nav.setChildren(childList);//给根節點设置子節點
        }
        return rootMenu;
    }

    /**
     * 获取子選單
     * @param pNode
     * @param nodes
     * @return
     */
    private static List<ProductCategoryTreeNodeVO> getChild(ProductCategoryTreeNodeVO pNode, List<ProductCategoryTreeNodeVO> nodes) {
        //子選單
        List<ProductCategoryTreeNodeVO> childList = new ArrayList<>();
        for (ProductCategoryTreeNodeVO nav : nodes) {
            // 遍历所有節點，将所有選單的父id与传过来的根節點的id比较
            //相等说明：为該根節點的子節點。
            if(nav.getPid().equals(pNode.getId())){
                nav.setLev(pNode.getLev()+1);
                childList.add(nav);
            }
        }
        //递归
        for (ProductCategoryTreeNodeVO nav : childList) {
            nav.setChildren(getChild(nav, nodes));
        }
        Collections.sort(childList,ProductCategoryTreeNodeVO.order());//排序
        //如果節點下没有子節點，返回一个空List（递归退出）
        if(childList.size() == 0){
            return null;
        }
        return childList;
    }

//    获取二级父级分類

    public static List<ProductCategoryTreeNodeVO> buildParent(List<ProductCategoryTreeNodeVO> nodes) {
        //根節點
        List<ProductCategoryTreeNodeVO> rootMenu = new ArrayList<>();
        for (ProductCategoryTreeNodeVO nav : nodes) {
            if(nav.getPid()==0){
                nav.setLev(1);
                rootMenu.add(nav);
            }
        }
        /* 根據Menu類的order排序 */
        Collections.sort(rootMenu,ProductCategoryTreeNodeVO.order());
        /*为根選單设置子選單，getChild是递归调用的*/
        for (ProductCategoryTreeNodeVO nav : rootMenu) {
            /* 获取根節點下的所有子節點 使用getChild方法*/
            List<ProductCategoryTreeNodeVO> childList = getParentChild(nav, nodes);
            nav.setChildren(childList);//给根節點设置子節點
        }
        return rootMenu;
    }

    private static List<ProductCategoryTreeNodeVO> getParentChild(ProductCategoryTreeNodeVO pNode, List<ProductCategoryTreeNodeVO> nodes) {
        //子選單
        List<ProductCategoryTreeNodeVO> childList = new ArrayList<>();
        for (ProductCategoryTreeNodeVO nav : nodes) {
            // 遍历所有節點，将所有選單的父id与传过来的根節點的id比较
            //相等说明：为該根節點的子節點。
            if(nav.getPid().equals(pNode.getId())){
                nav.setLev(2);
                childList.add(nav);
            }
        }
        return childList;
    }


}
