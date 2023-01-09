package com.ws.an.config.abnormalnotice;

import com.ws.an.abnormalnoticehandle.components.DefaultAbnormalNoticeStatisticsRepository;
import com.ws.an.abnormalnoticehandle.event.AbnormalNoticeAsyncSendListener;
import com.ws.an.abnormalnoticehandle.event.AbnormalNoticeSendListener;
import com.ws.an.abnormalnoticehandle.event.AbstractNoticeSendListener;
import com.ws.an.abnormalnoticehandle.interfaces.AbnormalNoticeStatisticsRepository;
import com.ws.an.config.annos.ConditionalOnAbnormalNotice;
import com.ws.an.message.INoticeSendComponent;
import com.ws.an.pojos.Notice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;

import java.util.List;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:19
 * @description 通知策略自动装配
 */
@Configuration
@ConditionalOnAbnormalNotice
public class AbnormalNoticeSendAutoConfig {

    @Autowired
    private List<INoticeSendComponent<Notice>> list;

    private final Log logger = LogFactory.getLog(AbnormalNoticeSendAutoConfig.class);

    @Bean
    @ConditionalOnMissingBean
    public AbnormalNoticeStatisticsRepository exceptionNoticeStatisticsRepository() {
        logger.debug("创建默认异常统计仓库");
        AbnormalNoticeStatisticsRepository repository = new DefaultAbnormalNoticeStatisticsRepository();
        return repository;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "abnormal.notice.enable-async", havingValue = "false", matchIfMissing = true)
    public AbstractNoticeSendListener abnormalNoticeSendListener() {
        logger.info("开始创建同步发送监听器");
        AbstractNoticeSendListener listener = new AbnormalNoticeSendListener(list);
        return listener;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "abnormal.notice.enable-async", havingValue = "true")
    public AbstractNoticeSendListener abnormalNoticeAsyncSendListener(AsyncTaskExecutor applicationTaskExecutor) {
        logger.info("开始创建异步发送监听器");
        AbstractNoticeSendListener listener = new AbnormalNoticeAsyncSendListener(applicationTaskExecutor,list);
        return listener;
    }

}
