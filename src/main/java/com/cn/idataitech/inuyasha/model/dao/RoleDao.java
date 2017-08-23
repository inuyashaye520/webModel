package com.cn.idataitech.inuyasha.model.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao {

    /**
     * 添加角色
     */
    int insert(JSONObject json);

    /**
     * 保存用户权限
     */
    int insertUserRole(JSONObject data);


}
