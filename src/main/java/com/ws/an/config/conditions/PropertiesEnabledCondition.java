package com.ws.an.config.conditions;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 16:32
 * @description
 */
public abstract class PropertiesEnabledCondition extends SpringBootCondition {

    protected final String propName;

    protected final boolean matchIfMissingBl;

    public PropertiesEnabledCondition(String propName, boolean matchIfMissingBl) {
        this.propName = propName;
        this.matchIfMissingBl = matchIfMissingBl;
    }

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 通过application.propeties传入配置优先级比较高，在上下文当中可以获取到对应配置的值
        Boolean enabled = context.getEnvironment().getProperty(propName, Boolean.class);

        if ((enabled == null && matchIfMissingBl) || (enabled != null && enabled)) {
            return ConditionOutcome.match("开启异常通知");
        }

        return ConditionOutcome.noMatch("不开启异常通知");
    }

}
