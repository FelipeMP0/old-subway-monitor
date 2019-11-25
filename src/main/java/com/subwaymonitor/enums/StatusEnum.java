package com.subwaymonitor.enums;

import java.util.Arrays;

public enum StatusEnum {

    NORMAL("Operação Normal");

    private String text;

    StatusEnum(String text) {
        this.text = text;
    }

    public static StatusEnum fromText(String value) {
        return Arrays.stream(StatusEnum.values())
                     .filter(statusEnum -> statusEnum.getText().equalsIgnoreCase(value))
                     .findFirst()
                     .orElse(null);
    }

    public String getText() {
        return text;
    }
}
