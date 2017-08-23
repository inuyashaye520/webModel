package com.cn.idataitech.inuyasha.model.bean;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * 管理员运营账号
 */
public class Admin {

    private int id;
    private int merchantId;
    private String fullname;
    private String mobile;
    private String email;
    private String username;
    private String password;
    private String lastLoginUa;
    private String lastLoginIp;
    private Date lastLoginTime;
    private Date created;
    private Date updated;

    public Admin() {

    }

    public Admin(JSONObject admin) {
        this.id = admin.getIntValue("id");
        this.merchantId = admin.getIntValue("merchantId");
        this.fullname = admin.getString("fullname");
        this.mobile = admin.getString("mobile");
        this.email = admin.getString("email");
        this.username = admin.getString("username");
        this.password = admin.getString("password");
        this.lastLoginUa = admin.getString("lastLoginUa");
        this.lastLoginIp = admin.getString("lastLoginIp");
        this.lastLoginTime = admin.getDate("lastLoginTime");
        this.created = admin.getDate("created");
        this.updated = admin.getDate("updated");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginUa() {
        return lastLoginUa;
    }

    public void setLastLoginUa(String lastLoginUa) {
        this.lastLoginUa = lastLoginUa;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", merchantId=" + merchantId +
                ", fullname='" + fullname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastLoginUa='" + lastLoginUa + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
