package com.ws.an.config.conditions;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/7 11:37
 * @description
 */
public class AbnormalNoticeTypeCondition extends SpringBootCondition {

    protected final String propName;

    protected final String abnormalNoticeType;

    public AbnormalNoticeTypeCondition(String propName,String abnormalNoticeType){
        this.propName = propName;
        this.abnormalNoticeType = abnormalNoticeType;
    }

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 通过application.propeties传入配置优先级比较高，在上下文当中可以获取到对应配置的值
        String type = context.getEnvironment().getProperty(propName, String.class);

        if(StringUtils.isEmpty(type) || type.equals(abnormalNoticeType)){
            return ConditionOutcome.match("开启通知类型");
        }

        return ConditionOutcome.noMatch("不开启通知类型");
    }
}
