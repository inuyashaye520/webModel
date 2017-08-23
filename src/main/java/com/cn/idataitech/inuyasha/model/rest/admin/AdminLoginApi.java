package com.cn.idataitech.inuyasha.model.rest.admin;

import com.cn.idataitech.inuyasha.model.auth.AdminToken;
import com.cn.idataitech.inuyasha.model.bean.JsonResult;
import com.cn.idataitech.inuyasha.model.service.AdminService;
import com.cn.idataitech.inuyasha.model.bean.Principal;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController("adminLoginApi")
public class AdminLoginApi {

    @Autowired
    @Qualifier("adminService")
    private AdminService adminService;

    /**
     * 检查后台登录
     */
    @RequestMapping(method = RequestMethod.GET, value = "/api/admin/login")
    public JsonResult<JSONObject> session(HttpServletRequest request) {

        JSONObject admin = null;
        try {

            Integer adminId = (Integer) ((Principal) SecurityUtils.getSubject().getPrincipal()).getId();
            if (adminId == null) {
                adminId = (Integer) request.getSession().getAttribute("adminId");
            }
            admin = adminService.selectAdminUserById(adminId);
        } catch (Exception e) {
            return JsonResult.error(0, e.getMessage());
        }
        return JsonResult.success(admin);
    }

    /**
     * 管理后台登录
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/admin/login")
    public JsonResult<JSONObject> login(@RequestBody JSONObject login,
                                        HttpServletRequest request,
                                        HttpSession session) {
        String account = login.getString("account");
        String password = login.getString("password");
        String code = login.getString("code");
        String imageCode = (String) session.getAttribute("ImageCode");
        session.removeAttribute("ImageCode");
        String pass = DigestUtils.sha1DigestAsHex(password);
        if (!code.equals(imageCode)) {
            return JsonResult.error(0, "验证码不正确");
        }
        JSONObject admin = adminService.getAdminUserByLogin(account);
        if (admin == null) {
            return JsonResult.error(0, "账号不存在");
        }
        String old = admin.getString("password");
        if (!old.equals(pass)) {
            return JsonResult.error(0, "密码错误");
        }
        AdminToken token = new AdminToken();
        token.setUsername(account);
        token.setPassword(pass.toCharArray());
        SecurityUtils.getSubject().login(token);
        session.setAttribute("adminId", admin.getIntValue("id"));
        JSONObject log = new JSONObject();
        log.put("id", admin.getIntValue("id"));
        log.put("lastLoginIp", request.getRemoteAddr());
        log.put("lastLoginUa", request.getHeader("user-agent"));
        adminService.updateAdminUserLogin(log);
        return JsonResult.success(admin);
    }
}
