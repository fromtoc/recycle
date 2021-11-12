package com.coderman.system.service.impl;

import com.coderman.common.enums.system.UserStatusEnum;
import com.coderman.common.enums.system.UserTypeEnum;
import com.coderman.common.error.SystemCodeEnum;
import com.coderman.common.error.SystemException;
import com.coderman.common.model.system.*;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.utils.JWTUtils;
import com.coderman.common.utils.MenuTreeBuilder;
import com.coderman.common.vo.system.*;
import com.coderman.system.converter.MenuConverter;
import com.coderman.system.converter.UserConverter;
import com.coderman.system.mapper.*;
import com.coderman.system.service.DepartmentService;
import com.coderman.system.service.UserService;
import com.coderman.system.shiro.JWTToken;
import com.coderman.system.util.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author zhangyukang
 * @Date 2020/3/7 15:44
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserCardMapper userCardMapper;

    @Autowired
    private CardProductMapper cardProductMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询用戶
     *
     * @param name 用戶名
     * @return
     */
    @Override
    public User findUserByName(String name) {
        User t = new User();
        t.setUsername(name);
        return userMapper.selectOne(t);
    }

    /**
     * 查询用戶角色
     *
     * @param id 用戶ID
     * @return
     */
    @Override
    public List<Role> findRolesById(Long id) throws SystemException {
        User dbUser = userMapper.selectByPrimaryKey(id);
        if (dbUser == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "該用戶不存在");
        }
        List<Role> roles = new ArrayList<>();
        UserRole t = new UserRole();
        t.setUserId(dbUser.getId());
        List<UserRole> userRoleList = userRoleMapper.select(t);
        List<Long> rids = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userRoleList)) {
            for (UserRole userRole : userRoleList) {
                rids.add(userRole.getRoleId());
            }
            if (!CollectionUtils.isEmpty(rids)) {
                for (Long rid : rids) {
                    Role role = roleMapper.selectByPrimaryKey(rid);
                    if (role != null) {
                        roles.add(role);
                    }
                }
            }
        }
        return roles;
    }

    /**
     * 查询用戶卡片
     *
     * @param id 用戶ID
     * @return
     */
    @Override
    public List<UserCard> findCardsById(Long id) throws SystemException {
        User dbUser = userMapper.selectByPrimaryKey(id);
        if (dbUser == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "該用戶不存在");
        }
        UserCard t = new UserCard();
        t.setUserId(dbUser.getId());
        return userCardMapper.select(t);
    }

    @Override
    public List<Long> findProductsByCard(Long id) throws SystemException {
        Example o = new Example(CardProduct.class);
        o.createCriteria().andEqualTo("cardId", id);
        List<CardProduct> cardProducts = cardProductMapper.selectByExample(o);
        List<Long> productIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cardProducts)) {
            for (CardProduct cp : cardProducts) {
                productIds.add(cp.getProductId());
            }
        }
        return productIds;
    }

    @Override
    public int addUserCard(Long userId, String cardName) throws SystemException {
        Example o = new Example(UserCard.class);
        o.createCriteria().andEqualTo("cardName", cardName);
        List<UserCard> userCardList = userCardMapper.selectByExample(o);
        if (!CollectionUtils.isEmpty(userCardList)){
            return 0;
        }
        UserCard userCard = new UserCard();
        userCard.setUserId(userId);
        userCard.setCardName(cardName);
        userCard.setStatus(1);
        return userCardMapper.insert(userCard);
    }

    /**
     * 查询權限
     *
     * @param roles 用戶的角色
     * @return
     */
    @Override
    public List<Menu> findMenuByRoles(List<Role> roles) {
        List<Menu> menus = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roles)) {
            Set<Long> menuIds = new HashSet<>();//存放用戶的選單id
            List<RoleMenu> roleMenus;
            for (Role role : roles) {
                //根據角色ID查询權限ID
                Example o = new Example(RoleMenu.class);
                o.createCriteria().andEqualTo("roleId", role.getId());
                roleMenus = roleMenuMapper.selectByExample(o);
                if (!CollectionUtils.isEmpty(roleMenus)) {
                    for (RoleMenu roleMenu : roleMenus) {
                        menuIds.add(roleMenu.getMenuId());
                    }
                }
            }
            if (!CollectionUtils.isEmpty(menuIds)) {
                for (Long menuId : menuIds) {
                    //該用戶所有的選單
                    Menu menu = menuMapper.selectByPrimaryKey(menuId);
                    if (menu != null) {
                        menus.add(menu);
                    }
                }
            }
        }
        return menus;
    }

    /**
     * 获取選單
     *
     * @return
     */
    @Override
    public List<MenuNodeVO> findMenu() {
        List<Menu> menus = null;
        List<MenuNodeVO> menuNodeVOS = new ArrayList<>();
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (activeUser.getUser().getType() == UserTypeEnum.SYSTEM_ADMIN.getTypeCode()) {
            //超级管理員
            menus = menuMapper.selectAll();
        } else if (activeUser.getUser().getType() == UserTypeEnum.SYSTEM_USER.getTypeCode()) {
            //普通系统用戶
            menus = activeUser.getMenus();
        }
        if (!CollectionUtils.isEmpty(menus)) {
            menuNodeVOS = MenuConverter.converterToMenuNodeVO(menus);
        }
        //构建树形選單
        return MenuTreeBuilder.build(menuNodeVOS);
    }

    /**
     * 用戶列表
     *
     * @param userVO
     * @return
     */
    @Override
    public PageVO<UserVO> findUserList(Integer pageNum, Integer pageSize, UserVO userVO) {
        //已经拥有的
        PageHelper.startPage(pageNum, pageSize);
        Example o = new Example(User.class);
        String username = userVO.getUsername();
        String nickname = userVO.getNickname();
        Long departmentId = userVO.getDepartmentId();
        Integer sex = userVO.getSex();
        String email = userVO.getEmail();
        Example.Criteria criteria = o.createCriteria();
        if (username != null && !"".equals(username)) {
            criteria.andLike("username", "%" + username + "%");
        }
        if (nickname != null && !"".equals(nickname)) {
            criteria.andLike("nickname", "%" + nickname + "%");
        }
        if (email != null && !"".equals(email)) {
            criteria.andLike("email", "%" + email + "%");
        }
        if (sex != null) {
            criteria.andEqualTo("sex", sex);
        }
        if (departmentId != null) {
            criteria.andEqualTo("departmentId", departmentId);
        }


        criteria.andNotEqualTo("type", 0);
        List<User> userList = userMapper.selectByExample(o);
        List<UserVO> userVOS = userConverter.converterToUserVOList(userList);
        PageInfo<User> info = new PageInfo<>(userList);
        return new PageVO<>(info.getTotal(), userVOS);
    }

    /**
     * 删除用戶
     *
     * @param id 用戶ID
     */
    @Transactional
    @Override
    public void deleteById(Long id) throws SystemException {
        User user = userMapper.selectByPrimaryKey(id);
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();

        if (user == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "要删除的用戶不存在");
        }

        if (user.getId().equals(activeUser.getUser().getId())) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "不能删除当前登入用戶");
        }

        userMapper.deleteByPrimaryKey(id);
        //删除对应[用戶-角色]记录
        Example o = new Example(UserRole.class);
        o.createCriteria().andEqualTo("userId", id);
        userRoleMapper.deleteByExample(o);
    }

    /**
     * 更新用戶禁用状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(Long id, Boolean status) throws SystemException {
        User dbUser = userMapper.selectByPrimaryKey(id);
        if (dbUser == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "要更新状态的用戶不存在");
        }
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (dbUser.getId().equals(activeUser.getUser().getId())) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "無法改变当前用戶状态");
        } else {
            User t = new User();
            t.setId(id);
            t.setStatus(status ? UserStatusEnum.DISABLE.getStatusCode() :
                    UserStatusEnum.AVAILABLE.getStatusCode());
            userMapper.updateByPrimaryKeySelective(t);
        }
    }

    @Override
    public void updateCardStatus(Long id, Boolean status) throws SystemException {
        UserCard c = new UserCard();
        c.setId(id);
        c.setStatus(status ? 0 : 1);
        userCardMapper.updateByPrimaryKeySelective(c);
    }

    /**
     * 添加用戶
     *
     * @param userVO
     */
    @Transactional
    @Override
    public void add(UserVO userVO) throws SystemException {
        @NotBlank(message = "用戶名不能为空") String username = userVO.getUsername();
        @NotNull(message = "部門id不能为空") Long departmentId = userVO.getDepartmentId();
        Example o = new Example(User.class);
        o.createCriteria().andEqualTo("username", username);
        int i = userMapper.selectCountByExample(o);
        if (i != 0) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "該用戶名已被占用");
        }
        Department department = departmentMapper.selectByPrimaryKey(departmentId);
        if (department == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "該部門不存在");
        }
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        String salt = UUID.randomUUID().toString().substring(0, 32);
        user.setPassword(MD5Utils.md5Encryption(user.getPassword(), salt));
        user.setModifiedTime(new Date());
        user.setCreateTime(new Date());
        user.setSalt(salt);
        user.setType(UserTypeEnum.SYSTEM_USER.getTypeCode());//添加的用戶默认是普通用戶
        user.setStatus(UserStatusEnum.AVAILABLE.getStatusCode());//添加的用戶默认启用
        user.setAvatar("http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg");
        userMapper.insert(user);
    }

    /**
     * 更新
     *
     * @param id
     * @param userVO
     */
    @Transactional
    @Override
    public void update(Long id, UserEditVO userVO) throws SystemException {
        User dbUser = userMapper.selectByPrimaryKey(id);
        @NotBlank(message = "用戶名不能为空") String username = userVO.getUsername();
        @NotNull(message = "部門不能为空") Long departmentId = userVO.getDepartmentId();
        if (dbUser == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "要删除的用戶不存在");
        }
        Department department = departmentMapper.selectByPrimaryKey(departmentId);
        if (department == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "該部門不存在");
        }
        Example o = new Example(User.class);
        o.createCriteria().andEqualTo("username", username);
        List<User> users = userMapper.selectByExample(o);
        if (!CollectionUtils.isEmpty(users)) {
            if (!users.get(0).getId().equals(id)) {
                throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "該用戶名已被占用");
            }
        }
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        user.setModifiedTime(new Date());
        user.setId(dbUser.getId());
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public boolean updatePassword(String oldPassword, String newPassword) throws SystemException {
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        User dbUser = activeUser.getUser();
        if (dbUser == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "要更新密碼的用戶不存在");
        }
        if (dbUser != null) {
            String salt = dbUser.getSalt();
            //秘钥为盐
            String target = MD5Utils.md5Encryption(oldPassword, salt);
            String password = dbUser.getPassword();
            if (target.equals(password)) {
                User user = new User();
                user.setId(dbUser.getId());
                user.setPassword(MD5Utils.md5Encryption(newPassword, salt));
                userMapper.updateByPrimaryKeySelective(user);
                return true;
            }
        }
        return false;

    }

    @Override
    public void changePassword(Long id, String password) throws SystemException {
        User user = new User();
        String salt = userMapper.selectByPrimaryKey(id).getSalt();
        user.setId(id);
        user.setPassword(MD5Utils.md5Encryption(password, salt));
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 編辑
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public UserEditVO edit(Long id) throws SystemException {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "要編辑的用戶不存在");
        }
        UserEditVO userEditVO = new UserEditVO();
        BeanUtils.copyProperties(user, userEditVO);
        Department department = departmentMapper.selectByPrimaryKey(user.getDepartmentId());
        if (department != null) {
            userEditVO.setDepartmentId(department.getId());
        }
        return userEditVO;
    }

    /**
     * 用戶拥有的角色ID
     *
     * @param id 用戶id
     * @return
     */
    @Transactional
    @Override
    public List<Long> roles(Long id) throws SystemException {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "該用戶不存在");
        }
        Example o = new Example(UserRole.class);
        o.createCriteria().andEqualTo("userId", user.getId());
        List<UserRole> userRoleList = userRoleMapper.selectByExample(o);
        List<Long> roleIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userRoleList)) {
            for (UserRole userRole : userRoleList) {
                Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
                if (role != null) {
                    roleIds.add(role.getId());
                }
            }
        }
        return roleIds;
    }

    /**
     * 分配角色
     *
     * @param id   用戶id
     * @param rids 角色数组
     */
    @Override
    @Transactional
    public void assignRoles(Long id, Long[] rids) throws SystemException {
        //删除之前用戶的所有角色
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "用戶不存在");
        }
        //删除之前分配的
        Example o = new Example(UserRole.class);
        o.createCriteria().andEqualTo("userId", user.getId());
        userRoleMapper.deleteByExample(o);
        //增加现在分配的
        if (rids.length > 0) {
            for (Long rid : rids) {
                Role role = roleMapper.selectByPrimaryKey(rid);
                if (role == null) {
                    throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "roleId=" + rid + ",該角色不存在");
                }
                //判断角色状态
                if (role.getStatus() == 0) {
                    throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "roleName=" + role.getRoleName() + ",該角色已禁用");
                }
                UserRole userRole = new UserRole();
                userRole.setUserId(id);
                userRole.setRoleId(rid);
                userRoleMapper.insert(userRole);
            }
        }
    }

    @Override
    @Transactional
    public void assignProducts(String id, Long[] pids) throws SystemException {
        //删除之前分配的
        Example o = new Example(CardProduct.class);
        o.createCriteria().andEqualTo("cardId", id);
        cardProductMapper.deleteByExample(o);
        //增加现在分配的
        if (pids.length > 0) {
            for (Long pid : pids) {
                CardProduct cardProduct = new CardProduct();
                cardProduct.setCardId(id);
                cardProduct.setProductId(pid);
                cardProductMapper.insert(cardProduct);
            }
        }
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    /**
     * 用戶登入
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) throws SystemException {
        String token;
        User user = findUserByName(username);
        if (user != null) {
            String salt = user.getSalt();
            //秘钥为盐
            String target = MD5Utils.md5Encryption(password, salt);
            //生成Token
            token = JWTUtils.sign(username, target);
            JWTToken jwtToken = new JWTToken(token);
            try {
                SecurityUtils.getSubject().login(jwtToken);
            } catch (AuthenticationException e) {
                throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, e.getMessage());
            }
        } else {
            throw new SystemException(SystemCodeEnum.PARAMETER_ERROR, "用戶不存在");
        }
        return token;
    }

    /**
     * 用戶详情
     *
     * @return
     */
    @Override
    public UserInfoVO info() throws SystemException {
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setAvatar(activeUser.getUser().getAvatar());
        userInfoVO.setUsername(activeUser.getUser().getUsername());
        userInfoVO.setUrl(activeUser.getUrls());
        userInfoVO.setNickname(activeUser.getUser().getNickname());
        List<String> roleNames = activeUser.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
        userInfoVO.setRoles(roleNames);
        userInfoVO.setPerms(activeUser.getPermissions());
        userInfoVO.setIsAdmin(activeUser.getUser().getType() == UserTypeEnum.SYSTEM_ADMIN.getTypeCode());
        DepartmentVO dept = departmentService.edit(activeUser.getUser().getDepartmentId());
        if (dept != null) {
            userInfoVO.setDepartment(dept.getName());
        }
        return userInfoVO;
    }
}
