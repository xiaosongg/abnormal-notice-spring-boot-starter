package com.ws.an.pojos;

import com.ws.an.properties.enums.ProjectEnviroment;

import java.time.LocalDateTime;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 23:35
 * @description
 */
public class AbnormalNotice {

    /**
     * 通知标题
     */
    protected String title;

    /**
     * 工程环境
     */
    protected ProjectEnviroment projectEnviroment;

    /**
     * 通知时间
     */
    protected LocalDateTime createTime = LocalDateTime.now();

    /**
     * @param title
     * @param projectEnviroment
     */
    public AbnormalNotice(String title, ProjectEnviroment projectEnviroment) {
        this.title = title;
        this.projectEnviroment = projectEnviroment;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the projectEnviroment
     */
    public ProjectEnviroment getProjectEnviroment() {
        return projectEnviroment;
    }

    /**
     * @param projectEnviroment the projectEnviroment to set
     */
    public void setProjectEnviroment(ProjectEnviroment projectEnviroment) {
        this.projectEnviroment = projectEnviroment;
    }

    /**
     * @return the createTime
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
