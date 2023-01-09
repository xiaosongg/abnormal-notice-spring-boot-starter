package com.ws.an.config.abnormalnotice;

import com.ws.an.properties.notice.WechatNoticeProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/7 22:06
 * @description 企业微信机器人通知自动装配
 */
@Configuration
@ConditionalOnProperty(value = "abnormal.notice.wechat.enabled", havingValue = "true")
@EnableConfigurationProperties({ WechatNoticeProperty.class })
public class AbnormalNoticeWechatNoticeAutoConfig {

}
