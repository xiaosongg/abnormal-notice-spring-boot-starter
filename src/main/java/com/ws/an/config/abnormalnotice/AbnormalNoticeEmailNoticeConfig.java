package com.ws.an.config.abnormalnotice;

import com.ws.an.properties.notice.EmailNoticeProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:09
 * @description
 */
@Configuration
@ConditionalOnProperty(value = "abnormal.notice.email.enabled", havingValue = "true")
@EnableConfigurationProperties({ EmailNoticeProperty.class })
public class AbnormalNoticeEmailNoticeConfig {


}
