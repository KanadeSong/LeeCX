package com.ljj.web.shiro;

import com.ljj.pojo.ActiveUser;
import com.ljj.pojo.SysPermission;
import com.ljj.pojo.SysUser;
import com.ljj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @CLassName ShiroDBRealm
 * @Description 使用自定义realm来实现数据库的用户名密码校验进行登录认证
 * @Author LeeJack
 * @Date 2019/3/15/015 16:01
 * @Version 1.0
 */
@Component
public class ShiroDBRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    public ShiroDBRealm(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") CacheManager cacheManager) {
        super(cacheManager);
    }

    /**
     * @Description: 用于登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1. 从token中获取用户在表单中输入的域，即 用户名 以及 密码，以此来和数据库中的用户真实数据进行匹配
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        // 用户的登录名,密码
        String username = userToken.getUsername();
        String password = String.valueOf(userToken.getPassword());

        // 2. 根据用户输入的用户名和数据库进行匹配，查询数据库中的用户，如果查询不到，则返回一个null
        SysUser user = userService.queryUserByUsername(username);

        // 3. 判断数据库中查询出来的用户是否存在，不存在代表用户名密码错误；如果存在，则返回 AuthenticationInfo
        if (user == null) {
            return null;
        }

        String dbPassword = user.getPassword();
        String dbSalt = user.getAuthSalt();
        String userPassword = ShiroPasswordUtil.getShiroPassword(password , dbSalt , 2);

        if (!userPassword.equals(dbPassword)) {
            //抛出异常，密码不正确
            throw new IncorrectCredentialsException();
        }

        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserId(user.getId());
        activeUser.setUsername(user.getUsername());

        // 4. 返回AuthenticationInfo
        // 参数意义：
        // Object principal: 用户对象，可以使一个对象类，或者一个字符串，存与session中
        // Object credentials：密码
        // 							题外话：我们目前直接根据用户名密码来查询，所以这里直接放用户输入的密码即可；但是，也可疑直接用用户名来查询数据库中的用户，再然后进行密码的比对，如此则此处应该填写用户输入的密码
        // String realmName：当前我们自定义realm的名称
        AuthenticationInfo info = new SimpleAuthenticationInfo(activeUser , password , getName());
        return info;
    }


    /**
     * @Description: 用于授权鉴权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //从principals中获取当前用户
        ActiveUser sessionUser = (ActiveUser) principals.getPrimaryPrincipal();
        String userid = sessionUser.getUserId();
        System.out.println("userid:"+userid);
        // 模拟从数据库中获取用户的权限（资源权限字符串）
        List<SysPermission> permissionList = null;
        try {
            permissionList = userService.findPermissionListByUserId(userid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> percodeList = new ArrayList<String>();
        for (SysPermission p : permissionList) {
            percodeList.add(p.getPercode());
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(percodeList);

        return simpleAuthorizationInfo;
    }

    /**
     * @return void
     * @Description: 权限修改生效后，立即刷新清空缓存，则可以实现用户不退出生效新的权限
     * @Param []
     * @author LeeJack
     * @Date 17:02 2019/3/15/015
     */
    public void clearCache() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }


}
