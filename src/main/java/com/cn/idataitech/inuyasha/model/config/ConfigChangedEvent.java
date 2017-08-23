package com.cn.idataitech.inuyasha.model.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationEvent;

/**
 * 配置变化事件
 */
public class ConfigChangedEvent extends ApplicationEvent {

    public ConfigChangedEvent(JSONObject config) {
        super(config);
    }

    public JSONObject getSource() {
        return (JSONObject) super.getSource();
    }
}
