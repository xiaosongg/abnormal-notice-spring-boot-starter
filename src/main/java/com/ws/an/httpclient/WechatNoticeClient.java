package com.ws.an.httpclient;

import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Var;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/7 23:28
 * @description 企业微信机器人通知client
 */
public interface WechatNoticeClient {
    @Post(
            url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key={key}",
            headers = {
                    "Accept-Charset: utf-8",
                    "Content-Type: application/json"
            },
            dataType = "json")
    void sendWechatMsg(@Var("key") String key, @JSONBody Map<String, Object> body);


}
