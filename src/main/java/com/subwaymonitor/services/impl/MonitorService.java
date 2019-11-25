package com.subwaymonitor.services.impl;

import com.subwaymonitor.callables.CptmLinesCallable;
import com.subwaymonitor.callables.MetroLinesCallable;
import com.subwaymonitor.callables.PrivateLinesCallable;
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
import java.util.concurrent.*;

@Service
public class MonitorService {

    private final LineStatusService lineStatusService;
    private final LineRepository lineRepository;
    private final LinesService privateLinesService;
    private final LinesService metroLinesService;
    private final LinesService cptmLinesService;
    private final StatusRepository statusRepository;

    private boolean pauseFlag = false;

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

    public void importAndSaveData() throws NotFoundException, InterruptedException, ExecutionException {
        List<LineStatus> lineStatuses = new ArrayList<>();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        Callable<List<LineCurrentStatus>> cptmCallable = new CptmLinesCallable(this.cptmLinesService, this);

        Callable<List<LineCurrentStatus>> metroCallable = new MetroLinesCallable(this.metroLinesService, this);

        Callable<List<LineCurrentStatus>> privateCallable = new PrivateLinesCallable(this.privateLinesService, this);

        Future<List<LineCurrentStatus>> cptmThread = threadPool.submit(cptmCallable);

        Future<List<LineCurrentStatus>> metroThread = threadPool.submit(metroCallable);

        Future<List<LineCurrentStatus>> privateThread = threadPool.submit(privateCallable);

        while (!cptmThread.isDone() && !metroThread.isDone() && !privateThread.isDone()) {
            System.out.println("BUSCA POR INFORMAÇÕES AINDA EM EXECUÇÃO");
            Thread.sleep(1000);
        }

        List<LineCurrentStatus> cptmLinesCurrentStatus = cptmThread.get();
        List<LineCurrentStatus> metroLinesCurrentStatus = metroThread.get();
        List<LineCurrentStatus> privateLinesCurrentStatus = privateThread.get();

        this.addIntoList(cptmLinesCurrentStatus, lineStatuses);
        this.addIntoList(metroLinesCurrentStatus, lineStatuses);
        this.addIntoList(privateLinesCurrentStatus, lineStatuses);

        lineStatuses.forEach(this.lineStatusService::save);
    }

    private void addIntoList(List<LineCurrentStatus> lineCurrentStatuses, List<LineStatus> lineStatuses) throws NotFoundException {
        for (LineCurrentStatus lineCurrentStatus : lineCurrentStatuses) {
            Integer lastVerificationNumber = this.lineStatusService.findLastVerification();

            Line line = this.lineRepository.findByNumber(lineCurrentStatus.getLineNumber());

            Status status = this.statusRepository.findBySlug(lineCurrentStatus.getStatus());

            LineStatus lineStatus = new LineStatus(line, status);

            lineStatus.setVerificationNumber(lastVerificationNumber + 1);

            lineStatuses.add(lineStatus);
        }
    }

    public synchronized void verifyPauseFlag(String text) throws InterruptedException {
        while (pauseFlag) {
            System.out.println(text + " - EXECUÇÃO PAUSADA: Arquivo não disponível");
            wait();
        }
    }

    public synchronized void setPauseFlag(boolean pause, String text) {
        this.pauseFlag = pause;

        if (!this.pauseFlag) {
            System.out.println(text + " - NOVA EXECUÇÃO: Arquivo disponível");
            notifyAll();
        } else {
            System.out.println(text + " - está usando o arquivo");
        }
    }

}
