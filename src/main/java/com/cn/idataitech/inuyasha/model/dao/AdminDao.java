package com.cn.idataitech.inuyasha.model.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {

    // 查询登录用户
    JSONObject selectAdminUserByLogin(String login);

    // 通过ID查询管理信息
    JSONObject selectAdminUserById(int id);

    // 更新管理用户登录信息
    int updateAdminUserLogin(JSONObject login);

    // 统计管理员总数
    int selectAdminUserCount();

    // 添加管理员
    int insertAdminUser(JSONObject admin);


}
