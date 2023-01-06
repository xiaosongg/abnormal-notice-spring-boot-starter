package com.ws.an.config.abnormalnotice;

import com.ws.an.config.conditions.AbnormalNoticeEnabledCondition;
import com.ws.an.properties.AbnormalNoticeProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 16:54
 * @description
 */
@Configuration
@Conditional(AbnormalNoticeEnabledCondition.class)
@EnableConfigurationProperties(AbnormalNoticeProperties.class)
public class AbnormalNoticeConfig {
}
