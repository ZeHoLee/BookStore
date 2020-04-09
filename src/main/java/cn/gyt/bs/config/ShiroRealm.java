package cn.gyt.bs.config;

import cn.gyt.bs.entity.User;
import cn.gyt.bs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Administrator
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //token.getUsername()
//        User user = userService.login(token.getUsername(), String.valueOf(token.getPassword()));

        log.info("进入认证步骤");

//        if (user == null) {
//            new Exception("用户不存在");
//        }
        //密码验证
//        try {
//            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, token.getCredentials(), getName());
//            return simpleAuthenticationInfo;
//        } catch (Exception e) {
//            e.printStackTrace();
            return null;
//        }
    }
}
