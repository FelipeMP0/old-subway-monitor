package com.subwaymonitor.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SubwayMonitorScheduler {

    private final long THREE_MINUTES = 180000;

    @Scheduled(fixedRate = THREE_MINUTES)
    public void monitor() {

    }

}
