package com.cn.idataitech.inuyasha.model.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationEvent;

/**
 * 修改配置信息
 */
public class RefreshConfigEvent extends ApplicationEvent {

    public RefreshConfigEvent(JSONObject config) {
        super(config);
    }

    public JSONObject getSource() {
        return (JSONObject) super.getSource();
    }
}
