package com.cn.idataitech.inuyasha.model.auth;

import com.alibaba.fastjson.JSONObject;
import com.cn.idataitech.inuyasha.model.bean.Constant;
import com.cn.idataitech.inuyasha.model.bean.Principal;
import com.cn.idataitech.inuyasha.model.service.AdminService;
import com.cn.idataitech.inuyasha.model.session.ProxyRealm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 后台登陆 realm
 */
@Component
public class AdminRealm extends ProxyRealm implements InitializingBean {

    @Autowired
    private ProxyRealm proxyRealm;

    @Autowired
    @Qualifier("adminService")
    private AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo author = new SimpleAuthorizationInfo();
        author.addRole(Constant.ROLE_ADMIN);
        //author.addRole(cn.dypay.openwx.merchant.Constant.ROLE_MERCHANT);
        Principal principal = (Principal) principals.getPrimaryPrincipal();
        author.setStringPermissions(principal.getPermissions());
        return author;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JSONObject result = adminService.getAdminUserByLogin((String) token.getPrincipal());
        if (result == null) {
            throw new UnknownAccountException("No account found for user [" + token.getPrincipal() + "]");
        }
        Principal principal = new Principal();
        principal.setId(result.getInteger("id"));
        principal.setType(AdminRealm.class.getName());
        principal.setFullname(result.getString("fullname"));
        Set<String> roles = new HashSet<>();
        if (StringUtils.isNotBlank(result.getString("assets"))) {
            roles.addAll(Arrays.asList(result.getString("assets").split(",")));
        }
        principal.setPermissions(roles);
        return new SimpleAuthenticationInfo(principal, result.getString("password"), AdminRealm.class.getName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        proxyRealm.addRealm(AdminToken.class, this);
    }


}
