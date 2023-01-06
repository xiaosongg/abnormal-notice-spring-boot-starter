package com.ws.an.config.abnormalnotice;

import com.ws.an.config.annos.ConditionalOnAbnormalNotice;
import com.ws.an.message.EmailNoticeSendComponent;
import com.ws.an.message.INoticeSendComponent;
import com.ws.an.pojos.Notice;
import com.ws.an.properties.notice.EmailNoticeProperty;
import com.ws.an.text.NoticeTextResolver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2023/1/6 22:52
 * @description
 */
@Configuration
@AutoConfigureAfter({ MailSenderAutoConfiguration.class })
@ConditionalOnBean({ MailSender.class, MailProperties.class })
@ConditionalOnAbnormalNotice
@ConditionalOnProperty(value = "abnormal.notice.email.enabled", havingValue = "true")
public class AbnormalNoticeEmailSendingAutoConfig {

    @Autowired
    private EmailNoticeProperty emailExceptionNoticeProperty;

    private final static Log logger = LogFactory.getLog(AbnormalNoticeEmailSendingAutoConfig.class);

    @Bean("emailSendingComponent")
    @ConditionalOnMissingBean(name = "emailSendingComponent")
    public INoticeSendComponent<Notice> emailNoticeSendComponent(MailProperties mailProperties,
                                                                 MailSender mailSender) {
        logger.debug("创建邮件异常通知");
        INoticeSendComponent<Notice> component = new EmailNoticeSendComponent<Notice>(
                mailSender, mailProperties, emailExceptionNoticeProperty, AbnormalNoticeTextResolver());
        return component;
    }

    @Bean
    @ConditionalOnMissingBean
    public NoticeTextResolver<Notice> AbnormalNoticeTextResolver() {
        return x -> x.createText();
    }

}
