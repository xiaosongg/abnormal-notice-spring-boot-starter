package com.ws.an.config.conditions;

import org.springframework.core.annotation.Order;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/7 11:34
 * @description
 */
@Order(0)
public class AbnormalNoticeCommonCondition extends AbnormalNoticeTypeCondition {

    public AbnormalNoticeCommonCondition(){
        super("abnormal.notice.listen-type","common");
    }
}
