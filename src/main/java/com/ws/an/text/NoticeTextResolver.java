package com.ws.an.text;

import com.ws.an.pojos.AbnormalNotice;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 23:50
 * @description
 */
@FunctionalInterface
public interface NoticeTextResolver<T extends AbnormalNotice> {

    public String resolve(T object);
}

