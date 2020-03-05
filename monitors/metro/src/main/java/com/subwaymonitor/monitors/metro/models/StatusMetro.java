package com.subwaymonitor.monitors.metro.models;

import java.util.List;

public class StatusMetro {

  private List<MetroLineStatus> listLineStatus;

  public List<MetroLineStatus> getListLineStatus() {
    return listLineStatus;
  }

  public void setListLineStatus(List<MetroLineStatus> listLineStatus) {
    this.listLineStatus = listLineStatus;
  }
}
