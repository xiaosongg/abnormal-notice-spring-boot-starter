package com.ws.an.message;

import com.ws.an.pojos.AbnormalNotice;
import com.ws.an.properties.notice.EmailNoticeProperty;
import com.ws.an.text.NoticeTextResolver;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.regex.Pattern;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 23:47
 * @description
 */
public class EmailNoticeSendComponent<T extends AbnormalNotice> implements INoticeSendComponent<T> {

    private final MailSender mailSender;

    private final NoticeTextResolver<T> exceptionNoticeResolver;

    private final MailProperties mailProperties;

    private final EmailNoticeProperty emailExceptionNoticeProperty;

    public EmailNoticeSendComponent(MailSender mailSender, MailProperties mailProperties,
                                    EmailNoticeProperty emailExceptionNoticeProperty, NoticeTextResolver<T> exceptionNoticeResolver) {
        this.mailSender = mailSender;
        this.mailProperties = mailProperties;
        this.emailExceptionNoticeProperty = emailExceptionNoticeProperty;
        this.checkAllEmails(emailExceptionNoticeProperty);
        this.exceptionNoticeResolver = exceptionNoticeResolver;
    }

    @Override
    public void send(T notice) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String fromEmail = mailProperties.getUsername();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(emailExceptionNoticeProperty.getTo());
        String[] cc = emailExceptionNoticeProperty.getCc();
        if (cc != null && cc.length > 0)
            mailMessage.setCc(cc);
        String[] bcc = emailExceptionNoticeProperty.getBcc();
        if (bcc != null && bcc.length > 0)
            mailMessage.setBcc(bcc);
        mailMessage.setText(exceptionNoticeResolver.resolve(notice));

        mailMessage
                .setSubject(String.format("一个来自%s的提醒（%s）", notice.getTitle(), notice.getProjectEnviroment().getName()));
        mailSender.send(mailMessage);
    }

    private void checkAllEmails(EmailNoticeProperty emailExceptionNoticeProperty) {
        String fromEmail = mailProperties.getUsername();
        if (fromEmail != null && !isEmail(fromEmail))
            throw new IllegalArgumentException("发件人邮箱错误");
        String[] toEmail = emailExceptionNoticeProperty.getTo();
        if (toEmail != null) {
            for (String email : toEmail) {
                if (!isEmail(email))
                    throw new IllegalArgumentException("收件人邮箱错误");
            }
        }
        String[] ccEmail = emailExceptionNoticeProperty.getCc();
        if (ccEmail != null) {
            for (String email : ccEmail) {
                if (!isEmail(email))
                    throw new IllegalArgumentException("抄送人邮箱错误");
            }
        }
        String[] bccEmail = emailExceptionNoticeProperty.getBcc();
        if (bccEmail != null) {
            for (String email : bccEmail) {
                if (!isEmail(email))
                    throw new IllegalArgumentException("秘密抄送人邮箱错误");
            }
        }
    }

    private boolean isEmail(String email) {
        if (email != null)
            return Pattern.matches("^[A-Za-z0-9_\\-]+@[a-zA-Z0-9_\\-]+(\\.[a-zA-Z]{2,4})+$", email);
        return false;
    }

}
