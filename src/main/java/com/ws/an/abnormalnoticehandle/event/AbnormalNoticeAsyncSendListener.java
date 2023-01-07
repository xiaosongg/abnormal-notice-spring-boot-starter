package com.ws.an.abnormalnoticehandle.event;

import com.ws.an.message.INoticeSendComponent;
import com.ws.an.pojos.Notice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.AsyncTaskExecutor;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:35
 * @description s
 */
public class AbnormalNoticeAsyncSendListener extends AbstractNoticeSendListener {

    private static final Log logger = LogFactory.getLog(AbnormalNoticeAsyncSendListener.class);

    private final Executor executor;

    public AbnormalNoticeAsyncSendListener(AsyncTaskExecutor applicationTaskExecutor, List<INoticeSendComponent<Notice>> noticeSendComponents) {

        super(noticeSendComponents);

        this.executor = applicationTaskExecutor;
    }

    @Override
    public void onApplicationEvent(AbnormalNoticeEvent event) {

        logger.debug("消息异步发送");

        executor.execute(() -> send(event.getNotice()));

    }
}
