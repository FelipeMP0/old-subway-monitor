package com.subwaymonitor.appcommon.enums;

import java.util.Arrays;

public enum StatusEnum {
  NORMAL(new String[] {"Operação Normal"}),
  SLOW(new String[] {"Velocidade Reduzida"}),
  INTERRUPTED(new String[] {"Operação Interrompida"}),
  PARTIAL(new String[] {"Operação Parcial"}),
  FINISHED(new String[] {"Operação Encerrada", "Operações Encerradas"}),
  UNKNOWN();

  private String[] text;

  StatusEnum() {}

  StatusEnum(String[] text) {
    this.text = text;
  }

  public static StatusEnum fromText(String value) {
    return Arrays.stream(StatusEnum.values())
        .filter(
            statusEnum ->
                Arrays.stream(statusEnum.getText()).anyMatch(text -> text.equalsIgnoreCase(value)))
        .findFirst()
        .orElse(UNKNOWN);
  }

  public String[] getText() {
    return text;
  }
}
