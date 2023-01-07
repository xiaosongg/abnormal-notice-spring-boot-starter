package com.ws.an.abnormalnoticehandle.event;

import com.ws.an.message.INoticeSendComponent;
import com.ws.an.pojos.Notice;
import org.springframework.context.ApplicationListener;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:28
 * @description 抽象通知发送
 */
public abstract class AbstractNoticeSendListener implements ApplicationListener<AbnormalNoticeEvent> {

    private final List<INoticeSendComponent<Notice>> noticeSendComponents;

    /**
     * @param noticeSendComponents
     */
    public AbstractNoticeSendListener(List<INoticeSendComponent<Notice>> noticeSendComponents) {

        this.noticeSendComponents = noticeSendComponents;
    }

    public void send(Notice notice) {

        notice.setCreateTime(LocalDateTime.now());
        noticeSendComponents.forEach(x -> x.send(notice));

    }
}
