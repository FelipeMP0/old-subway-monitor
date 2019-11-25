package com.subwaymonitor.services.impl;

import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.models.Line;
import com.subwaymonitor.models.LineStatus;
import com.subwaymonitor.models.Status;
import com.subwaymonitor.models.generic.LineCurrentStatus;
import com.subwaymonitor.repositories.LineRepository;
import com.subwaymonitor.repositories.StatusRepository;
import com.subwaymonitor.services.LineStatusService;
import com.subwaymonitor.services.LinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {

    private final LineStatusService lineStatusService;
    private final LineRepository lineRepository;
    private final LinesService metroLinesService;
    private final StatusRepository statusRepository;

    @Autowired
    public MonitorService(LineStatusService lineStatusService,
                          LineRepository lineRepository, @Qualifier("metroLinesService") LinesService metroLinesService,
                          StatusRepository statusRepository) {
        this.lineStatusService = lineStatusService;
        this.lineRepository = lineRepository;
        this.metroLinesService = metroLinesService;
        this.statusRepository = statusRepository;
    }

    public void importAndSaveData() throws NotFoundException {
        List<LineCurrentStatus> lineCurrentStatuses = this.metroLinesService.findStatuses();

        for (LineCurrentStatus lineCurrentStatus : lineCurrentStatuses) {
            Line line = this.lineRepository.findByNumber(lineCurrentStatus.getLineNumber());

            Status status = this.statusRepository.findBySlug(lineCurrentStatus.getStatus());

            LineStatus lineStatus = new LineStatus(line, status);

            this.lineStatusService.save(lineStatus);
        }
    }

}
