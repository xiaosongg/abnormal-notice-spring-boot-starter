package com.ws.an.config.abnormalnotice;

import com.ws.an.config.annos.ConditionalOnAbnormalNotice;
import com.ws.an.pojos.Notice;
import com.ws.an.text.NoticeTextResolver;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/7 22:45
 * @description
 */
@Configuration
@ConditionalOnAbnormalNotice
@ConditionalOnProperty(value = "abnormal.notice.wechat.enabled", havingValue = "true")
public class AbnormalNoticeWechatSendingAutoConfig {






    @Bean
    @ConditionalOnMissingBean
    public NoticeTextResolver<Notice> AbnormalNoticeTextResolver() {
        return x -> x.createText();
    }
}
