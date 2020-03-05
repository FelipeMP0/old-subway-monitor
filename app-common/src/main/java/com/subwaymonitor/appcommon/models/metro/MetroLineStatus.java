package com.subwaymonitor.appcommon.models.metro;

public class MetroLineStatus {

  private String id;

  private String color;

  private String line;

  private String lineRaw;

  private String statusMetro;

  private String status;

  private String description;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getLine() {
    return line;
  }

  public void setLine(String line) {
    this.line = line;
  }

  public String getLineRaw() {
    return lineRaw;
  }

  public void setLineRaw(String lineRaw) {
    this.lineRaw = lineRaw;
  }

  public String getStatusMetro() {
    return statusMetro;
  }

  public void setStatusMetro(String statusMetro) {
    this.statusMetro = statusMetro;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
