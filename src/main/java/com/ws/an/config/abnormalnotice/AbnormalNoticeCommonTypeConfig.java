package com.ws.an.config.abnormalnotice;

import com.ws.an.abnormalnoticehandle.AbnormalNoticeHandler;
import com.ws.an.aop.AbnormalNoticeAop;
import com.ws.an.config.annos.ConditionalOnAbnormalNotice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/7 11:17
 * @description 装配异常监听切面
 */
@Configuration
@ConditionalOnAbnormalNotice
@ConditionalOnProperty(name = "abnormal.notice.listen-type", havingValue = "common", matchIfMissing = true)
public class AbnormalNoticeCommonTypeConfig {

    private final Log logger = LogFactory.getLog(AbnormalNoticeCommonTypeConfig.class);

    @Bean
    @ConditionalOnMissingBean
    public AbnormalNoticeAop exceptionNoticeAop(AbnormalNoticeHandler abnormalNoticeHandler) {
        logger.debug("创建异常监听切面");
        AbnormalNoticeAop aop = new AbnormalNoticeAop(abnormalNoticeHandler);
        return aop;
    }
}
