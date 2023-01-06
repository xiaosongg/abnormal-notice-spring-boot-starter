package com.ws.an.properties.enums;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:02
 * @description
 */
public enum ProjectEnviroment {

    DEVELOP("dev"), TEST("test"), PREVIEW("pre"), RELEASE("release"), ROLLBACK("roll_back");

    private final String name;

    private ProjectEnviroment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
