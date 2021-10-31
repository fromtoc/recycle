package com.coderman.system.service;

import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.*;
import com.coderman.common.vo.system.*;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/7 15:43
 * @Version 1.0
 **/
public interface UserService {

    /**
     * 根據用戶名查询用戶
     *
     * @param name 用戶名
     * @return
     */
     User findUserByName(String name);

    /**
     * 查询用戶角色
     *
     * @param id 用戶id
     * @return
     */
     List<Role> findRolesById(Long id) throws SystemException;

     /**
     * 查询用戶卡片
     * @param id 用戶ID
     * @return
     */
    List<UserCard> findCardsById(Long id) throws SystemException;

    /**
     * 查询卡片產品
     * @param id 用戶ID
     * @return
     */
    List<Long> findProductsByCard(Long id) throws SystemException;

    /**
     * 查询用戶卡片
     * @param userId 用戶ID
     * @param cardId 卡片ID
     * @return
     */
    void addUserCard(Long userId, String cardId) throws SystemException;

    /**
     * 根據用戶角色查询用戶的菜单
     * 菜单: menu+button
     *
     * @param roles 用戶的角色
     * @return
     */
    List<Menu> findMenuByRoles(List<Role> roles);

    /**
     * 加载菜单
     *
     * @return
     */
    List<MenuNodeVO> findMenu();

    /**
     * 用戶列表
     * @param userVO
     * @return
     */
    PageVO<UserVO> findUserList(Integer pageNum, Integer pageSize, UserVO userVO);

    /**
     * 删除用戶
     *
     * @param id
     */
    void deleteById(Long id) throws SystemException;

    /**
     * 更新状态
     *
     * @param id
     * @param status
     */
    void updateStatus(Long id, Boolean status) throws SystemException;

    /**
     * 更新卡片状态
     *
     * @param id
     * @param status
     */
    void updateCardStatus(Long id, Boolean status) throws SystemException;

    /**
     * 添加用戶
     * @param userVO
     */
    void add(UserVO userVO) throws SystemException;

    /**
     * 更新用戶
     *
     * @param id
     * @param userVO
     */
    void update(Long id, UserEditVO userVO) throws SystemException;

    /**
     * 更新用戶密碼
     *
     * @param oldPassword
     * @param newPassword
     */
    boolean updatePassword(String oldPassword, String newPassword) throws SystemException;

    /**
     * 更新状态
     *
     * @param id
     * @param password
     */
    void changePassword(Long id, String password) throws SystemException;

    /**
     * 编辑用戶
     *
     * @param id
     * @return
     */
    UserEditVO edit(Long id) throws SystemException;

    /**
     * 已拥有的角色ids
     *
     * @param id 用戶id
     * @return
     */
    List<Long> roles(Long id) throws SystemException;

    /**
     * 分配角色
     *
     * @param id
     * @param rids
     */
    void assignRoles(Long id, Long[] rids) throws SystemException;

    /**
     * 所有用戶
     *
     * @return
     */
    List<User> findAll();

    /**
     * 用戶登入
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password) throws SystemException;


    /**
     * 用戶详情
     *
     * @return
     */
    UserInfoVO info() throws SystemException;

}
