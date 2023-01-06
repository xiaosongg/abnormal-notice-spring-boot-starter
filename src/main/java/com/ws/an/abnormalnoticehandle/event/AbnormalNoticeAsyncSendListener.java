package com.ws.an.abnormalnoticehandle.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.AsyncTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:35
 * @description s
 */
public class AbnormalNoticeAsyncSendListener extends AbstractNoticeSendListener{

    private static final Log logger = LogFactory.getLog(AbnormalNoticeAsyncSendListener.class);

    private final Executor executor;

    public AbnormalNoticeAsyncSendListener(AsyncTaskExecutor applicationTaskExecutor){

        this.executor = applicationTaskExecutor;
    }

    @Override
    public void onApplicationEvent(AbnormalNoticeEvent abnormalNoticeEvent) {

    }
}
