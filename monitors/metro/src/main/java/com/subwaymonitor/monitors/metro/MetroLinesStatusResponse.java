package com.subwaymonitor.monitors.metro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
class MetroLinesStatusResponse {

  @JsonProperty("StatusMetro")
  StatusMetro statusMetro;

  static class StatusMetro {

    @JsonProperty("ListLineStatus")
    List<MetroLineStatus> listLineStatus;

    static class MetroLineStatus {

      @JsonProperty("Id")
      String id;

      @JsonProperty("Color")
      String color;

      @JsonProperty("Line")
      String line;

      @JsonProperty("LineRaw")
      String lineRaw;

      @JsonProperty("StatusMetro")
      String statusMetro;

      @JsonProperty("Status")
      String status;

      @JsonProperty("Description")
      String description;
    }
  }
}
