package com.ws.an.properties.notice;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/7 22:15
 * @description
 */
@ConfigurationProperties(prefix = "abnormal.notice.wechat")
public class WechatNoticeProperty {
    /**
     * 是否开启企业微信机器人通知
     */
    private boolean enabled = false;

    /**
     * 企业微信机器人唯一key
     */
    private String key;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
