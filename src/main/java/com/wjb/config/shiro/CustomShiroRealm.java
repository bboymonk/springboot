package com.wjb.config.shiro;

import com.wjb.model.Menu;
import com.wjb.model.Role;
import com.wjb.model.User;
import com.wjb.service.MenuService;
import com.wjb.service.RoleService;
import com.wjb.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Useristrator on 2017/7/28.
 */
public class CustomShiroRealm extends AuthorizingRealm {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)principalCollection.getPrimaryPrincipal();
        List<Role> roleList = roleService.roleList(user.getId());
        Set<String> permissions =new HashSet<String>();
        Set<String> roles = new HashSet<String>();
        for(Role role:roleList){
            roles.add(role.getName());
            List<Menu> menus = menuService.getUrl(role.getId());
            for (Menu m:menus){
                permissions.add(m.getMapping());
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        String password = new String((char[])token.getCredentials());
        User user = userService.login(username,ShiroKit.md5(password,username));
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),ByteSource.Util.bytes(user.getUsername()),getName());
        return info;
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        String username =  (String)principals.getPrimaryPrincipal();
        SimplePrincipalCollection spc = new SimplePrincipalCollection(username, getName());
        super.clearCachedAuthenticationInfo(spc);
    }



}
