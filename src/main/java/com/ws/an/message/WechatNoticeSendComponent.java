package com.ws.an.message;

import com.dtflys.forest.converter.json.GsonEncoder;
import com.google.gson.Gson;
import com.ws.an.config.abnormalnotice.AbnormalNoticeWechatSendingAutoConfig;
import com.ws.an.httpclient.WechatNoticeClient;
import com.ws.an.pojos.AbnormalNotice;
import com.ws.an.properties.notice.WechatNoticeProperty;
import com.ws.an.text.NoticeTextResolver;
import feign.*;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.slf4j.Slf4jLogger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
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

    private final Gson gson;

    private final WechatNoticeClient wechatNoticeClient;

    public WechatNoticeSendComponent(WechatNoticeClient wechatNoticeClient,Gson gson,WechatNoticeProperty wechatNoticeProperty, NoticeTextResolver<T> exceptionNoticeResolver){
        this.wechatNoticeClient = wechatNoticeClient;
        this.gson = gson;
        this.exceptionNoticeResolver = exceptionNoticeResolver;
        this.wechatNoticeProperty = wechatNoticeProperty;
    }

    @Override
    public void send(T notice) {
        logger.info("开始发送微信机器人通知");
        Map<String, Object> sendMap = new HashMap<>();
        //设置消息类型 txt文本
        sendMap.put("msgtype", "text");
        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("content", "你好，我是ws的机器人，项目出bug了，bug具体报文：" + exceptionNoticeResolver.resolve(notice));
        sendMap.put("text", contentMap);

        wechatNoticeClient.sendWechatMsg(wechatNoticeProperty.getKey(), sendMap);
    }

    class GsonDecoder implements Decoder {

        @Override
        public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
            return gson.fromJson(response.body().asReader(StandardCharsets.UTF_8), type);
        }

    }

    class GsonEncoder implements Encoder {

        @Override
        public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
            template.body(gson.toJson(object).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        }

    }
}
