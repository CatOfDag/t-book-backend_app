package com.huse.util;

import com.huse.Service.AccountService;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class Realm1 extends AuthorizingRealm {

    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private AccountService accountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        AccountService userInformation = (AccountService) principalCollection.getPrimaryPrincipal();
        authorizationInfo.addRole("personal");//个人账户权限

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        boolean b = accountService.selectByAccountPassword(token.getUsername(),String.valueOf(token.getPassword()));
        if(b){
            return new SimpleAuthenticationInfo(token.getUsername(),token.getPassword(),getName());
        }
        return null;
    }
}
