package com.ws.an.message;

import com.ws.an.pojos.AbnormalNotice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 23:35
 * @description
 */
@FunctionalInterface
public interface INoticeSendComponent<T extends AbnormalNotice> {

    public void send(T notice);

}
