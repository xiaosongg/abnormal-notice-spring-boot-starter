package com.ws.an.config.conditions;

import org.springframework.core.annotation.Order;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 16:30
 * @description 异常通知开关
 */
@Order(10)
public class OnAbnormalNoticeContition extends PropertiesEnabledCondition {

    public OnAbnormalNoticeContition() {
        // 默认为开启
        super("abnormal.notice.enabled", true);
    }
}
