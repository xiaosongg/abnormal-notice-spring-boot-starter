package com.ws.an.config.annos;

import com.ws.an.config.conditions.AbnormalNoticeEnabledCondition;
import com.ws.an.config.conditions.OnAbnormalNoticeContition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 16:20
 * @description 通知开关
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional({ AbnormalNoticeEnabledCondition.class, OnAbnormalNoticeContition.class })
public @interface ConditionalOnAbnormalNotice {
}
