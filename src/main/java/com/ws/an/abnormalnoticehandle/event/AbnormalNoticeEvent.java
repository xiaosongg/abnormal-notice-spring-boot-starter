package com.ws.an.abnormalnoticehandle.event;

import javafx.application.Application;
import org.springframework.context.ApplicationEvent;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:31
 * @description
 */
public class AbnormalNoticeEvent extends ApplicationEvent {
    public AbnormalNoticeEvent(Object source) {
        super(source);
    }
}
