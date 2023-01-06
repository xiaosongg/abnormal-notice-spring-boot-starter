package com.ws.an.config.conditions;

import org.springframework.core.annotation.Order;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 16:30
 * @description 总控开关
 */
@Order(0)
public class AbnormalNoticeEnabledCondition extends PropertiesEnabledCondition {
    public AbnormalNoticeEnabledCondition() {
        // 默认为开启
        super("abnormal.enabled", true);
    }
}
