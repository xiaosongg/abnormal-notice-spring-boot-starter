package com.ws.an.message;

import com.ws.an.pojos.AbnormalNotice;
import com.ws.an.properties.notice.WechatNoticeProperty;
import com.ws.an.text.NoticeTextResolver;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/7 22:55
 * @description
 */
public class WechatNoticeSendComponent<T extends AbnormalNotice> implements INoticeSendComponent<T>  {

    private final NoticeTextResolver<T> exceptionNoticeResolver;

    private final WechatNoticeProperty wechatNoticeProperty;

    public WechatNoticeSendComponent(WechatNoticeProperty wechatNoticeProperty,NoticeTextResolver<T> exceptionNoticeResolver){
        this.exceptionNoticeResolver = exceptionNoticeResolver;
        this.wechatNoticeProperty = wechatNoticeProperty;
    }

    @Override
    public void send(T notice) {

    }
}
