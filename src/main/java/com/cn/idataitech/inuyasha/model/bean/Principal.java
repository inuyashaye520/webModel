package com.cn.idataitech.inuyasha.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Principal
 */
public class Principal implements Serializable {

    private static final long serialVersionUID = -7498429614384423750L;
    private Object id;
    private String type;
    private String fullname;
    private String mobile;
    private String head;
    private Date expired;
    private Object extra;
    private int rightid;
    private String nickName;
    private Set<String> permissions;

    public Principal() {
    }

    public Principal(String type, Object id) {
        this.id = id;
        this.type = type;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public int getRightid() {
        return rightid;
    }

    public void setRightid(int rightid) {
        this.rightid = rightid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
