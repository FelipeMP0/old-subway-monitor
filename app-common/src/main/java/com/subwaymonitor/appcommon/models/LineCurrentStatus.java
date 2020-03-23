package com.subwaymonitor.appcommon.models;

public class LineCurrentStatus {

  private Integer lineNumber;

  private String status;

  public LineCurrentStatus(Integer lineNumber, String status) {
    this.lineNumber = lineNumber;
    this.status = status;
  }

  public Integer getLineNumber() {
    return lineNumber;
  }

  public String getStatus() {
    return status;
  }
}
