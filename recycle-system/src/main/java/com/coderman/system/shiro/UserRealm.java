package com.coderman.system.shiro;

import com.coderman.common.enums.system.UserTypeEnum;
import com.coderman.common.model.system.Menu;
import com.coderman.common.model.system.Role;
import com.coderman.common.model.system.User;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.utils.JWTUtils;
import com.coderman.system.service.UserService;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报錯
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用戶權限的時候才会调用此方法，例如checkRole,checkPermission之類的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();

        if (activeUser.getUser().getType() == 0) {
            authorizationInfo.addStringPermission("*:*");
        } else {
            List<String> permissions = new ArrayList<>(activeUser.getPermissions());
            List<Role> roleList = activeUser.getRoles();
            //授權角色
            if (!CollectionUtils.isEmpty(roleList)) {
                for (Role role : roleList) {
                    authorizationInfo.addRole(role.getRoleName());
                }
            }
            //授權權限
            if (!CollectionUtils.isEmpty(permissions)) {
                for (String permission : permissions) {
                    if (permission != null && !"".equals(permission)) {
                        authorizationInfo.addStringPermission(permission);
                    }
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 默认使用此方法进行用戶名正确与否验证，錯誤抛出異常即可。
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数據库进行对比
        String username = JWTUtils.getUsername(token);

        if (username == null) {
            throw new AuthenticationException(" token錯誤，請重新登入！");
        }

        User userBean = userService.findUserByName(username);

        if (userBean == null) {
            throw new AccountException("账號不存在!");
        }
        if (JWTUtils.isExpire(token)) {
            throw new AuthenticationException(" token过期，請重新登入！");
        }

        if (!JWTUtils.verify(token, username, userBean.getPassword())) {
            throw new CredentialsException("密碼錯誤!");
        }

        if (userBean.getStatus() == 0) {
            throw new LockedAccountException("账號已被锁定!");
        }

        //如果验证通过，获取用戶的角色
        List<Role> roles = userService.findRolesById(userBean.getId());
        Boolean limitUser = true;
        if (userBean.getType() == UserTypeEnum.SYSTEM_ADMIN.getTypeCode()) {
            //超级管理員
            limitUser = false;
        } else {
            for (Role r : roles) {
                if (r.getType()==0) {
                    limitUser = false;
                    break;
                }
            }
        }
        //查询用戶的所有選單(包括了選單和按钮)
        List<Menu> menus = userService.findMenuByRoles(roles);

        Set<String> urls = new HashSet<>();
        Set<String> perms = new HashSet<>();
        if (!CollectionUtils.isEmpty(menus)) {
            for (Menu menu : menus) {
                String url = menu.getUrl();
                String per = menu.getPerms();
                if (menu.getType() == 0 && !StringUtils.isEmpty(url)) {
                    urls.add(menu.getUrl());
                }
                if (menu.getType() == 1 && !StringUtils.isEmpty(per)) {
                    perms.add(menu.getPerms());
                }
            }
        }
        //过滤出url,和用戶的權限
        ActiveUser activeUser = new ActiveUser();
        activeUser.setRoles(roles);
        activeUser.setUser(userBean);
        activeUser.setMenus(menus);
        activeUser.setUrls(urls);
        activeUser.setPermissions(perms);
        activeUser.setLimitUser(limitUser);
        return new SimpleAuthenticationInfo(activeUser, token, getName());
    }
}
