package com.subwaymonitor.models.metro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MetroLinesStatusResponse {

  @JsonProperty("StatusMetro")
  private StatusMetro statusMetro;

  public StatusMetro getStatusMetro() {
    return statusMetro;
  }

  public void setStatusMetro(StatusMetro statusMetro) {
    this.statusMetro = statusMetro;
  }
}
