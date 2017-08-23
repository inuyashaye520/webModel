package com.cn.idataitech.inuyasha.model.service;

import com.cn.idataitech.inuyasha.model.dao.AdminDao;
import com.cn.idataitech.inuyasha.model.dao.RoleDao;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 通过登录信息获取管理员账号信息
     *
     * @param login 用户名或手机号
     * @return 管理员账号信息
     */
    public JSONObject getAdminUserByLogin(String login) {
        return adminDao.selectAdminUserByLogin(login);
    }

    /**
     * 获取管理员用户信息
     *
     * @param id 账号ID
     * @return 账号信息
     */
    public JSONObject selectAdminUserById(int id) {
        JSONObject admin = adminDao.selectAdminUserById(id);
        return admin;
    }

    /**
     * 更新登录信息
     */
    public boolean updateAdminUserLogin(JSONObject log) {
        return adminDao.updateAdminUserLogin(log) > 0;
    }

    @EventListener
    public void handApplicationReadyEvent(ApplicationReadyEvent event) {
        //默认初始化一个管理员账号
        if (adminDao.selectAdminUserCount() == 0) {
            JSONObject admin = new JSONObject();
            admin.put("fullname", "Administrator");
            admin.put("username", "admin");
            admin.put("password", DigestUtils.sha1DigestAsHex("admin"));
            adminDao.insertAdminUser(admin);

            JSONObject role = new JSONObject();
            role.put("name", "系统管理员");
            role.put("assets", "盟友审核,盟友关系,盟友商户,交易明细,商户列表,商户交易,盟友收益,盟友订单,系统设置,账号管理,账号添加,账号修改,账号删除,权限管理,参数设置,更多设置,盟友列表,系统配置,盟友设置");
            roleDao.insert(role);

            int userId = admin.getIntValue("id");
            int roleId = role.getIntValue("id");

            JSONObject userRole = new JSONObject();
            userRole.put("userId", userId);
            userRole.put("roleId", roleId);
            roleDao.insertUserRole(userRole);
        }
    }


}
