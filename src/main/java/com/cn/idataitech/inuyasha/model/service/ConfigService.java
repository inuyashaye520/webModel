package com.cn.idataitech.inuyasha.model.service;

import com.cn.idataitech.inuyasha.model.config.ConfigChangedEvent;
import com.cn.idataitech.inuyasha.model.config.RefreshConfigEvent;
import com.cn.idataitech.inuyasha.model.dao.ConfigDao;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Config Service
 */
@Service("configService")
public class ConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigService.class);
    private static final JSONObject CONFIG = new JSONObject();
    private static final String CONFIG_REDIS_PREFIX = "config:";

    @Autowired
    private ConfigDao configDao;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    //private String profile;
    private String version;
    private long versionTime;

    public void setConfigs(JSONObject configs) {
        final List<JSONObject> configList = new ArrayList<>();
        final Map<String, String> changed = new HashMap<>();
        for (String key : configs.keySet()) {
            JSONObject config = new JSONObject();
            config.put("key", key);
            String value = configs.getString(key);
            config.put("value", value);
            configList.add(config);
            changed.put(key, value);
        }
        configDao.insertAll(new HashMap<String, List<JSONObject>>() {{
            put("configs", configList);
        }});
        CONFIG.clear();
        getConfig();
        notifyChanged();

        //redisTemplate.opsForValue().multiSet(changed);
    }

    public JSONObject getConfig() {
        if (CONFIG.isEmpty()) {
            List<JSONObject> configList = configDao.select();

            for (JSONObject config : configList) {
                String key = config.getString("key");
                //if (profile.equalsIgnoreCase("dev") && multiDevConfigKey.contains(key)) {
                // To distinguish multi wx dev account
                //    CONFIG.put(key, System.getProperty(key));
                //} else {
                CONFIG.put(key, config.get("value"));
                //}
            }
        }
        return CONFIG;
    }

    public String get(String key) {
        return getConfig().getString(key);
    }

    public void set(String key, String value) {
        configDao.insert(key, value);
        CONFIG.put(key, value);
        notifyChanged();
    }

    private void notifyChanged() {
        eventPublisher.publishEvent(new ConfigChangedEvent(getConfig()));
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getVersionTime() {
        return versionTime;
    }

    public void setVersionTime(long versionTime) {
        this.versionTime = versionTime;
    }

    @EventListener
    public void handleApplicationReady(ApplicationReadyEvent event) {
        //profile = StringUtils.join(environment.getActiveProfiles());
        JSONObject configs = getConfig();
        configs.put("version", version);
        configs.put("version_time", versionTime);
        //configs.put("profile", profile);
        setConfigs(configs);
    }

    @EventListener
    public void handleRefreshConfig(RefreshConfigEvent event) {
        setConfigs(event.getSource());
    }

}
