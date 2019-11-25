package com.subwaymonitor.enums;

import java.util.Arrays;

public enum StatusEnum {

    NORMAL(new String[]{"Operação Normal"}),
    SLOW(new String[]{"Velocidade Reduzida"}),
    INTERRUPTED(new String[]{"Operação Interrompida"}),
    PARTIAL(new String[]{"Operação Parcial"}),
    FINISHED(new String[]{"Operação Encerrada", "Operações Encerradas"});

    private String[] text;

    StatusEnum(String[] text) {
        this.text = text;
    }

    public static StatusEnum fromText(String value) {
        return Arrays.stream(StatusEnum.values())
                     .filter(statusEnum -> {
                         for (String text : statusEnum.getText()) {
                             if (text.equalsIgnoreCase(value)) {
                                 return true;
                             }
                         }
                         return false;
                     })
                     .findFirst()
                     .orElse(NORMAL);
    }

    public String[] getText() {
        return text;
    }
}
