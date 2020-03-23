package com.subwaymonitor.services.impl;

import com.subwaymonitor.appcommon.enums.StatusEnum;
import com.subwaymonitor.appcommon.models.LineCurrentStatus;
import com.subwaymonitor.appcommon.services.LinesService;
import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.models.Line;
import com.subwaymonitor.models.LineStatus;
import com.subwaymonitor.models.LineStatus.Builder;
import com.subwaymonitor.models.Status;
import com.subwaymonitor.repositories.LineRepository;
import com.subwaymonitor.repositories.StatusRepository;
import com.subwaymonitor.services.LineStatusService;
import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MonitorService {

  private final Logger logger;

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
    this.logger = Logger.getLogger(this.getClass());
  }

  public void importAndSaveData() throws NotFoundException {
    List<LineCurrentStatus> metroStatuses = this.metroLinesService.findStatuses();

    Integer lastVerification = this.lineStatusService.findLastVerification();

    for (LineCurrentStatus lineCurrentStatus : metroStatuses) {
      StatusEnum currentStatus = StatusEnum.fromText(lineCurrentStatus.getStatus());

      if (currentStatus.equals(StatusEnum.UNKNOWN)) {
        this.logger.warn("Unknown status received: " + lineCurrentStatus.getStatus());
      }

      Status status = this.statusRepository.findBySlug(currentStatus.name());

      LineStatus lineStatus =
          new Builder()
              .line(new Line(lineCurrentStatus.getLineNumber()))
              .status(status)
              .verificationNumber(lastVerification)
              .build();

      this.lineStatusService.save(lineStatus);
    }
  }
}
