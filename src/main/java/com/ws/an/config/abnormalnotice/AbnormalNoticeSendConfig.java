package com.ws.an.config.abnormalnotice;

import com.ws.an.abnormalnoticehandle.event.AbnormalNoticeAsyncSendListener;
import com.ws.an.abnormalnoticehandle.event.AbnormalNoticeSendListener;
import com.ws.an.abnormalnoticehandle.event.AbstractNoticeSendListener;
import com.ws.an.config.annos.ConditionalOnAbnormalNotice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:19
 * @description
 */
@Configuration
@ConditionalOnAbnormalNotice
public class AbnormalNoticeSendConfig {

    private final Log logger = LogFactory.getLog(AbnormalNoticeSendConfig.class);

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "abnormal.notice.enable-async", havingValue = "false", matchIfMissing = true)
    public AbstractNoticeSendListener abnormalNoticeSendListener() {
        logger.info("开始创建同步发送监听器");
        AbstractNoticeSendListener listener = new AbnormalNoticeSendListener();
        return listener;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "abnormal.notice.enable-async", havingValue = "true")
    public AbstractNoticeSendListener abnormalNoticeAsyncSendListener(AsyncTaskExecutor applicationTaskExecutor) {
        logger.info("开始创建异步发送监听器");
        AbstractNoticeSendListener listener = new AbnormalNoticeAsyncSendListener(applicationTaskExecutor);
        return listener;
    }

}
