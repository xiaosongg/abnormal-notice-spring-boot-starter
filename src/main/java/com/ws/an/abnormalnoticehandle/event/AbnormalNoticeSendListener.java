package com.ws.an.abnormalnoticehandle.event;

import com.ws.an.message.INoticeSendComponent;
import com.ws.an.pojos.Notice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:34
 * @description
 */
public class AbnormalNoticeSendListener extends AbstractNoticeSendListener {

    private final static Log logger = LogFactory.getLog(AbnormalNoticeSendListener.class);

    public AbnormalNoticeSendListener(List<INoticeSendComponent<Notice>> noticeSendComponents){
        super(noticeSendComponents);
    }

    @Override
    public void onApplicationEvent(AbnormalNoticeEvent event) {
        logger.debug("消息同步发送");
        send(event.getNotice());
    }
}
