package com.ws.an.abnormalnoticehandle;

import com.ws.an.abnormalnoticehandle.event.AbnormalNoticeEvent;
import com.ws.an.pojos.Notice;
import com.ws.an.properties.AbnormalNoticeProperties;
import com.ws.an.properties.NoticeProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEventPublisher;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 19:52
 * @description
 */
public class AbnormalNoticeHandler {


    private final AbnormalNoticeProperties abnormalNoticeProperties;

    private final NoticeProperties noticeProperties;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final Log logger = LogFactory.getLog(getClass());

    public AbnormalNoticeHandler(AbnormalNoticeProperties abnormalNoticeProperties, NoticeProperties noticeProperties, ApplicationEventPublisher applicationEventPublisher) {
        super();
        this.abnormalNoticeProperties = abnormalNoticeProperties;
        this.noticeProperties = noticeProperties;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    private boolean containsException(RuntimeException exception) {
        List<Class<? extends Throwable>> thisEClass = getAllExceptionClazz(exception);
        List<Class<? extends Exception>> list = noticeProperties.getExcludeExceptions();
        for (Class<? extends Exception> clazz : list) {
            if (thisEClass.stream().anyMatch(c -> clazz.isAssignableFrom(c)))
                return true;
        }
        return false;
    }

    private List<Class<? extends Throwable>> getAllExceptionClazz(RuntimeException exception) {
        List<Class<? extends Throwable>> list = new LinkedList<Class<? extends Throwable>>();
        list.add(exception.getClass());
        Throwable cause = exception.getCause();
        while (cause != null) {
            list.add(cause.getClass());
            cause = cause.getCause();
        }
        return list;
    }

    /**
     * 反射方式获取方法中出现的异常进行的通知
     *
     * @param ex     异常信息
     * @param method 方法名
     * @param args   参数信息
     * @return
     */
    public Notice createNotice(RuntimeException ex, String method, Object[] args, HttpServletRequest request) {
        if (containsException(ex)) {
            return null;
        }

        Notice notice = new Notice(ex, noticeProperties.getIncludedTracePackage(),
                args, abnormalNoticeProperties.getProjectEnviroment(),
                String.format("%s的异常通知", abnormalNoticeProperties.getProjectName()),request);

        logger.debug("创建异常通知：" + method);
        notice.setProject(abnormalNoticeProperties.getProjectName());

        applicationEventPublisher.publishEvent(new AbnormalNoticeEvent(this, notice));
        return notice;

    }
}
