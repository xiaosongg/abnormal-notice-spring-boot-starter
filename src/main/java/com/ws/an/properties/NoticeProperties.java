package com.ws.an.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:57
 * @description
 */
@ConfigurationProperties(prefix = "abnormal.notice")
public class NoticeProperties {

    /**
     * 是否开启异常通知
     */
    private boolean enabled = false;

    /**
     * 追踪信息的包含的包名
     */
    private String includedTracePackage;

    /**
     * 排除的需要统计的异常
     */
    private List<Class<? extends Exception>> excludeExceptions = new LinkedList<>();

    /**
     * 是否开启异步通知
     */
    private boolean enableAsync = false;

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the includedTracePackage
     */
    public String getIncludedTracePackage() {
        return includedTracePackage;
    }

    /**
     * @param includedTracePackage the includedTracePackage to set
     */
    public void setIncludedTracePackage(String includedTracePackage) {
        this.includedTracePackage = includedTracePackage;
    }

    public List<Class<? extends Exception>> getExcludeExceptions() {
        return excludeExceptions;
    }

    public void setExcludeExceptions(List<Class<? extends Exception>> excludeExceptions) {
        this.excludeExceptions = excludeExceptions;
    }

    /**
     * @return the enableAsync
     */
    public boolean isEnableAsync() {
        return enableAsync;
    }

    /**
     * @param enableAsync the enableAsync to set
     */
    public void setEnableAsync(boolean enableAsync) {
        this.enableAsync = enableAsync;
    }


}
