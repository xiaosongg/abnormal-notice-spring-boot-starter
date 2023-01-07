package com.ws.an.message;

import com.ws.an.client.WechatNoticeClient;
import com.ws.an.pojos.AbnormalNotice;
import com.ws.an.properties.notice.WechatNoticeProperty;
import com.ws.an.text.NoticeTextResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/7 22:55
 * @description
 */
public class WechatNoticeSendComponent<T extends AbnormalNotice> implements INoticeSendComponent<T>  {

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
        Map<String, Object> sendMap = new HashMap<>();
        //设置消息类型 txt文本
        sendMap.put("msgtype", "text");
        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("content", "你好，我是ws的机器人，项目出bug了，bug具体报文：" + exceptionNoticeResolver.resolve(notice));
        sendMap.put("text", contentMap);
        wechatNoticeClient.sendWechatMsg(wechatNoticeProperty.getKey(), sendMap);
    }
}
