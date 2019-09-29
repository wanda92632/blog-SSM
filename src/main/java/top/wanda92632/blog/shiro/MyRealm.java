package top.wanda92632.blog.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import top.wanda92632.blog.entity.Role;
import top.wanda92632.blog.entity.User;
import top.wanda92632.blog.service.impl.PermissionServiceImpl;
import top.wanda92632.blog.service.impl.RoleServiceImpl;
import top.wanda92632.blog.service.impl.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    RoleServiceImpl roleServiceImpl;
    @Autowired
    PermissionServiceImpl permissionServiceImpl;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录用户名
        String userName = principalCollection.getPrimaryPrincipal().toString();
        // 从数据库或者缓存中获取角色数据
        List<Role> roleList = roleServiceImpl.getRolesByUserName(userName);
        Set<String> roles = new HashSet<>();
        if(roleList.size()>0){
            for(Role role :roleList){
                roles.add(role.getRoleName());
            }
        }else {
            return null;
        }
        // 从数据库或者缓存中获取权限数据
        Set<String> permissions = permissionServiceImpl.getPermissionsByUserName(userName);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取token中的登录账户
        String userName = authenticationToken.getPrincipal().toString();
        //获取角色信息By用户名
        User user= userServiceImpl.findUserByUserName(userName);
        //如果没有查到,则抛出异常
        if(user==null){
            System.out.println("用户不存在");
            return null;
        }
        //获取盐，用于对密码在加密算法(MD5)的基础上二次加密ֵ
        ByteSource byteSalt = ByteSource.Util.bytes("abcd");
        //获取自定义Realm名
        String realmName=getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),byteSalt,realmName);
        //返回给调用login(token)方法
        return info;
    }
}
