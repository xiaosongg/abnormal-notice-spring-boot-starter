package com.ws.an.properties.enums;

/**
 * @author WuSong
 * @version 1.0
 * @date 2023/1/6 17:12
 * @description
 */
public enum EmailTextType {

    TEXT("text");

    private final String value;

    public String getValue() {
        return value;
    }

    private EmailTextType(String value) {
        this.value = value;
    }
}
