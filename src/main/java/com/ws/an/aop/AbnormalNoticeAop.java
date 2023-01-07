package com.ws.an.aop;

import com.ws.an.abnormalnoticehandle.AbnormalNoticeHandler;
import com.ws.an.anno.MessageNotice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 15:38
 * @description 异常通知切面
 */
@Aspect
public class AbnormalNoticeAop {

    private AbnormalNoticeHandler abnormalNoticeHandler;

    private final Log logger = LogFactory.getLog(getClass());

    public AbnormalNoticeAop(AbnormalNoticeHandler abnormalNoticeHandler){
        this.abnormalNoticeHandler = abnormalNoticeHandler;
    }

    @AfterThrowing(value = "@within(listener)", throwing = "e", argNames = "listener,e")
    public void abnormalNotice(JoinPoint joinPoint, MessageNotice listener, RuntimeException e) {
        handleException(e, joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterThrowing(value = "@annotation(listener)", throwing = "e", argNames = "listener,e")
    public void abnormalNoticeWithMethod(JoinPoint joinPoint, MessageNotice listener, RuntimeException e) {
        handleException(e, joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    private void handleException(RuntimeException exception, String methodName, Object[] args) {
        logger.debug("出现异常：" + methodName
                + String.join(",", Arrays.stream(args).map(x -> x.toString()).toArray(String[]::new)));

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        abnormalNoticeHandler.createNotice(exception, methodName, args,request);
    }

}
