package com.ws.an.config.abnormalnotice;

import com.ws.an.config.annos.ConditionalOnAbnormalNotice;
import com.ws.an.properties.NoticeProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:53
 * @description 创建异常处理器自动装配
 */
@Configuration
@ConditionalOnAbnormalNotice
@EnableConfigurationProperties({ NoticeProperties.class })
public class NoticeAutoConfig {
}
