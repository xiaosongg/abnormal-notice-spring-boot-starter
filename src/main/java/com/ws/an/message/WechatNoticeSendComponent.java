package com.ws.an.message;

import com.ws.an.httpclient.WechatNoticeClient;
import com.ws.an.pojos.AbnormalNotice;
import com.ws.an.properties.notice.WechatNoticeProperty;
import com.ws.an.text.NoticeTextResolver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/7 22:55
 * @description
 */
public class WechatNoticeSendComponent<T extends AbnormalNotice> implements INoticeSendComponent<T>  {

    private final static Log logger = LogFactory.getLog(WechatNoticeSendComponent.class);

    private final NoticeTextResolver<T> exceptionNoticeResolver;

    private final WechatNoticeProperty wechatNoticeProperty;

    private final WechatNoticeClient wechatNoticeClient;

    public WechatNoticeSendComponent(WechatNoticeClient wechatNoticeClient,WechatNoticeProperty wechatNoticeProperty, NoticeTextResolver<T> exceptionNoticeResolver){
        this.wechatNoticeClient = wechatNoticeClient;
        this.exceptionNoticeResolver = exceptionNoticeResolver;
        this.wechatNoticeProperty = wechatNoticeProperty;
    }

    @Override
    public void send(T notice) {
        logger.debug("开始发送微信机器人通知");
        Map<String, Object> sendMap = new HashMap<>();
        //设置消息类型 txt文本
        sendMap.put("msgtype", "text");
        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("content", "你好，我是异常通知机器人，有项目出bug了，bug具体报文：\r\n" + exceptionNoticeResolver.resolve(notice));
        sendMap.put("text", contentMap);

        wechatNoticeClient.sendWechatMsg(wechatNoticeProperty.getKey(), sendMap);
    }

}
