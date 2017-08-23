package com.cn.idataitech.inuyasha.model.session;

import com.cn.idataitech.inuyasha.model.bean.Principal;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Proxy realm
 */
@Component("proxyRealm")
public class ProxyRealm extends AuthorizingRealm {

    private Map<String, ProxyRealm> tokenRealms = new HashMap<>();
    private Map<String, ProxyRealm> principalRealms = new HashMap<>();

    @Override
    public boolean supports(AuthenticationToken token) {
        return tokenRealms.get(token.getClass().getName()) != null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) principals.getPrimaryPrincipal();
        return principalRealms.get(principal.getType()).doGetAuthorizationInfo(principals);
    }

    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
        return;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return tokenRealms.get(token.getClass().getName()).getAuthenticationInfo(token);
    }

    public void addRealm(Class<? extends AuthenticationToken> cls, ProxyRealm realm) {
        tokenRealms.put(cls.getName(), realm);
        principalRealms.put(realm.getClass().getName(), realm);
    }

}
