package com.subwaymonitor.callables;

import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.models.generic.LineCurrentStatus;
import com.subwaymonitor.services.LinesService;
import com.subwaymonitor.services.impl.MonitorService;

import java.util.List;
import java.util.concurrent.Callable;

public class CptmLinesCallable implements Callable<List<LineCurrentStatus>> {

    private final LinesService cptmLinesService;
    private final MonitorService monitorService;

    public CptmLinesCallable(LinesService cptmLinesService, MonitorService monitorService) {
        this.cptmLinesService = cptmLinesService;
        this.monitorService = monitorService;
    }

    @Override
    public List<LineCurrentStatus> call() throws NotFoundException, InterruptedException {
        System.out.println("BUSCA POR INFORMAÇÕES DAS LINHAS DA CPTM INICIADA");
        this.monitorService.verifyPauseFlag("CPTM");
        this.monitorService.setPauseFlag(true, "CPTM");

        List<LineCurrentStatus> statuses = this.cptmLinesService.findStatuses();

        this.monitorService.setPauseFlag(false, "CPTM");

        return statuses;
    }

}
