package com.ws.an.properties.notice;

import com.ws.an.properties.enums.EmailTextType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:11
 * @description
 */
@ConfigurationProperties(prefix = "abnormal.notice.email")
public class EmailNoticeProperty {
    /**
     * 是否开启邮件通知
     */
    private boolean enabled = false;

    /**
     * 收件人
     */
    private String[] to;

    /**
     * 抄送
     */
    private String[] cc;

    /**
     * 密抄送
     */
    private String[] bcc;

    /**
     * 邮件的通知文本类型
     */
    private EmailTextType emailTextType = EmailTextType.TEXT;

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the to
     */
    public String[] getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String[] to) {
        this.to = to;
    }

    /**
     * @return the cc
     */
    public String[] getCc() {
        return cc;
    }

    /**
     * @param cc the cc to set
     */
    public void setCc(String[] cc) {
        this.cc = cc;
    }

    /**
     * @return the bcc
     */
    public String[] getBcc() {
        return bcc;
    }

    /**
     * @param bcc the bcc to set
     */
    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    /**
     * @return the emailTextType
     */
    public EmailTextType getEmailTextType() {
        return emailTextType;
    }

    /**
     * @param emailTextType the emailTextType to set
     */
    public void setEmailTextType(EmailTextType emailTextType) {
        this.emailTextType = emailTextType;
    }

}
