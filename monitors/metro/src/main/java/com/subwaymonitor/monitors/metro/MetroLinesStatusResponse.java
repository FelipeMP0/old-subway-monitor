package com.subwaymonitor.monitors.metro;

import java.util.List;

class MetroLinesStatusResponse {

  StatusMetro statusMetro;

  static class StatusMetro {
    List<MetroLineStatus> listLineStatus;

    static class MetroLineStatus {
      String id;

      String color;

      String line;

      String lineRaw;

      String statusMetro;

      String status;

      String description;
    }
  }
}
