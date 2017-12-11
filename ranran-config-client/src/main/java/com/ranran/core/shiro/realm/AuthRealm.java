package com.ranran.core.shiro.realm;


import com.ranran.uums.system.mapper.TsUserMapper;
import com.ranran.uums.system.model.TsUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zengrui on 2017/7/12.
 */
public class AuthRealm extends AuthorizingRealm {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthRealm.class);

    @Autowired
    private TsUserMapper tsUserMapper;

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        TsUser tsUser = new TsUser();
        tsUser.setUserName((String) token.getPrincipal());
        tsUser= tsUserMapper.selectOne(tsUser);
        if (tsUser == null) {
            throw new UnknownAccountException();// 没找到帐号
        }
        if (Boolean.TRUE.equals(tsUser.getUserStatus())) {
            throw new LockedAccountException(); // 帐号锁定
        }
        return new SimpleAuthenticationInfo(tsUser.getUserNo(), // 用户名
                tsUser.getUserPassword(), // 密码
                ByteSource.Util.bytes(tsUser.getUserNo()+tsUser.getUserSalt()),// salt=username+salt
                this.getName() // realm name
        );
    }
    /**
     * 用户授权方法
     * 只是在具体的权限验证的时候触发 例如：getSubject().isPermitted(p);
     *
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        LOGGER.info("##################执行Shiro权限认证##################");
        String userNo = (String) principal.getPrimaryPrincipal();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//        info.setRoles(indexService.findRoleNoByUserNo(userNo));
//        info.addStringPermissions(indexService.findResPermissionByUserNo(userNo));//将权限放入shiro中.
        return info;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

}
