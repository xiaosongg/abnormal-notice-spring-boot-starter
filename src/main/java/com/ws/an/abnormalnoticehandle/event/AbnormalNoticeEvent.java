package com.ws.an.abnormalnoticehandle.event;

import com.ws.an.pojos.Notice;
import org.springframework.context.ApplicationEvent;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:31
 * @description
 */
public class AbnormalNoticeEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private final Notice notice;

    public AbnormalNoticeEvent(Object source, Notice notice) {
        super(source);
        this.notice = notice;
    }

    /**
     * @return the notice
     */
    public Notice getNotice() {
        return notice;
    }

}
