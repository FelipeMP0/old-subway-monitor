package com.subwaymonitor.services.impl;

import com.subwaymonitor.appcommon.models.LineCurrentStatus;
import com.subwaymonitor.appcommon.services.LinesService;
import com.subwaymonitor.models.Line;
import com.subwaymonitor.models.LineStatus;
import com.subwaymonitor.models.LineStatus.Builder;
import com.subwaymonitor.models.Status;
import com.subwaymonitor.repositories.LineRepository;
import com.subwaymonitor.repositories.StatusRepository;
import com.subwaymonitor.services.LineStatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MonitorService {

  private LinesService metroLinesService;
  private LineStatusService lineStatusService;
  private LineRepository lineRepository;
  private StatusRepository statusRepository;

  @Autowired
  public MonitorService(
      @Qualifier("MetroLinesService") LinesService metroLinesService,
      LineStatusService lineStatusService,
      LineRepository lineRepository,
      StatusRepository statusRepository) {
    this.metroLinesService = metroLinesService;
    this.lineStatusService = lineStatusService;
    this.lineRepository = lineRepository;
    this.statusRepository = statusRepository;
  }

  public void importAndSaveData() {
    List<LineCurrentStatus> metroStatuses = this.metroLinesService.findStatuses();

    Integer lastVerification = this.lineStatusService.findLastVerification();

    metroStatuses.forEach(
        lineCurrentStatus -> {
          LineStatus lineStatus =
              new Builder()
                  .line(new Line())
                  .status(new Status())
                  .verificationNumber(lastVerification)
                  .build();

          this.lineStatusService.save(lineStatus);
        });
  }
}
