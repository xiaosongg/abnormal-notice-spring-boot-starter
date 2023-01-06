package com.ws.an.config.abnormalnotice;

import com.ws.an.abnormalnoticehandle.ExceptionHandler;
import com.ws.an.config.annos.ConditionalOnAbnormalNotice;
import com.ws.an.properties.AbnormalNoticeProperties;
import com.ws.an.properties.NoticeProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
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

    private final Log logger = LogFactory.getLog(NoticeAutoConfig.class);

    @Bean
    public ExceptionHandler exceptionHandler(AbnormalNoticeProperties abnormalNoticeProperties,
                                             NoticeProperties noticeProperties, ApplicationEventPublisher applicationEventPublisher) {
        logger.debug("创建异常处理器");
        ExceptionHandler exceptionHandler = new ExceptionHandler(abnormalNoticeProperties, noticeProperties,
                applicationEventPublisher);
        return exceptionHandler;
    }
}
