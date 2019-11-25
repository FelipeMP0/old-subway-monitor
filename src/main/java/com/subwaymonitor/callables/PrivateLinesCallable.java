package com.subwaymonitor.callables;

import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.models.generic.LineCurrentStatus;
import com.subwaymonitor.services.LinesService;
import com.subwaymonitor.services.impl.MonitorService;

import java.util.List;
import java.util.concurrent.Callable;

public class PrivateLinesCallable implements Callable<List<LineCurrentStatus>> {

    private final LinesService privateLinesService;
    private final MonitorService monitorService;

    public PrivateLinesCallable(LinesService privateLinesService, MonitorService monitorService) {
        this.privateLinesService = privateLinesService;
        this.monitorService = monitorService;
    }

    @Override
    public List<LineCurrentStatus> call() throws NotFoundException, InterruptedException {
        System.out.println("BUSCA POR INFORMAÇÕES DAS LINHAS PRIVADAS INICIADA");
        this.monitorService.verifyPauseFlag("LINHAS PRIVADAS");
        this.monitorService.setPauseFlag(true, "LINHAS PRIVADAS");

        List<LineCurrentStatus> statuses = this.privateLinesService.findStatuses();

        this.monitorService.setPauseFlag(false, "LINHAS PRIVADAS");

        return statuses;
    }

}
