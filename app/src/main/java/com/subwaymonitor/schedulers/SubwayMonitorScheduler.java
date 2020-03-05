package com.subwaymonitor.schedulers;

import com.subwaymonitor.services.impl.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SubwayMonitorScheduler {

  private static final long THREE_MINUTES = 180000;

  private final MonitorService monitorService;

  @Autowired
  public SubwayMonitorScheduler(MonitorService monitorService) {
    this.monitorService = monitorService;
  }

  @Scheduled(fixedRate = THREE_MINUTES)
  public void monitor() {
    this.monitorService.importAndSaveData();
  }
}
