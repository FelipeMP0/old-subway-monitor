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

import java.util.ArrayList;
import java.util.List;

@Service
public class MonitorService {

    private final LineStatusService lineStatusService;
    private final LineRepository lineRepository;
    private final LinesService privateLinesService;
    private final LinesService metroLinesService;
    private final LinesService cptmLinesService;
    private final StatusRepository statusRepository;

    @Autowired
    public MonitorService(LineStatusService lineStatusService,
                          LineRepository lineRepository,
                          @Qualifier("privateLinesService") LinesService privateLinesService,
                          @Qualifier("metroLinesService") LinesService metroLinesService,
                          @Qualifier("cptmLinesService") LinesService cptmLinesService,
                          StatusRepository statusRepository) {
        this.lineStatusService = lineStatusService;
        this.lineRepository = lineRepository;
        this.privateLinesService = privateLinesService;
        this.metroLinesService = metroLinesService;
        this.cptmLinesService = cptmLinesService;
        this.statusRepository = statusRepository;
    }

    public void importAndSaveData() throws NotFoundException {
        List<LineStatus> lineStatuses = new ArrayList<>();

        this.executeForCptmLines(lineStatuses);

        this.executeForPrivateLines(lineStatuses);

        this.executeForMetroLines(lineStatuses);

        lineStatuses.forEach(this.lineStatusService::save);
    }

    private void executeForMetroLines(List<LineStatus> lineStatuses) throws NotFoundException {
        List<LineCurrentStatus> lineCurrentStatuses = this.metroLinesService.findStatuses();

        this.addIntoList(lineCurrentStatuses, lineStatuses);
    }

    private void executeForPrivateLines(List<LineStatus> lineStatuses) throws NotFoundException {
        List<LineCurrentStatus> lineCurrentStatuses = this.privateLinesService.findStatuses();

        this.addIntoList(lineCurrentStatuses, lineStatuses);
    }

    private void executeForCptmLines(List<LineStatus> lineStatuses) throws NotFoundException {
        List<LineCurrentStatus> lineCurrentStatuses = this.cptmLinesService.findStatuses();

        this.addIntoList(lineCurrentStatuses, lineStatuses);
    }

    private void addIntoList(List<LineCurrentStatus> lineCurrentStatuses, List<LineStatus> lineStatuses) throws NotFoundException {
        for (LineCurrentStatus lineCurrentStatus : lineCurrentStatuses) {
            Line line = this.lineRepository.findByNumber(lineCurrentStatus.getLineNumber());

            Status status = this.statusRepository.findBySlug(lineCurrentStatus.getStatus());

            LineStatus lineStatus = new LineStatus(line, status);

            lineStatuses.add(lineStatus);
        }
    }

}
