package com.cn.idataitech.inuyasha.model.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 配置数据库操作类
 */
@Mapper
public interface ConfigDao {

    /**
     * 插入一条配置项，如果配置项已经存在，将更新值
     *
     * @param key  配置项名称
     * @param value 配置项值
     * @return 变更的记录数
     */
    int insert(@Param("key") String key, @Param("value") String value);


    /**
     * 插入多条配置项，如果配置项已经存在，将更新值
     *
     * @param configs 必须包含
     * @return 变更的记录数
     */
    int insertAll(Map<String, List<JSONObject>> configs);

    /**
     * 查询所有的配置信息
     *
     * @return 所有的配置信息。
     *
     * 示例： [ {"key": "key1", "value": "value1"}, {"key": "key2", "value": "value2"} ]
     */
    List<JSONObject> select();
}
