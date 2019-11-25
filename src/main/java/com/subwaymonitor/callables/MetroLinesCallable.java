package com.subwaymonitor.callables;

import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.models.generic.LineCurrentStatus;
import com.subwaymonitor.services.LinesService;
import com.subwaymonitor.services.impl.MonitorService;

import java.util.List;
import java.util.concurrent.Callable;

public class MetroLinesCallable implements Callable<List<LineCurrentStatus>> {

    private final LinesService metroLinesService;
    private final MonitorService monitorService;

    public MetroLinesCallable(LinesService metroLinesService, MonitorService monitorService) {
        this.metroLinesService = metroLinesService;
        this.monitorService = monitorService;
    }

    @Override
    public List<LineCurrentStatus> call() throws NotFoundException {
        return this.metroLinesService.findStatuses();
    }

}
