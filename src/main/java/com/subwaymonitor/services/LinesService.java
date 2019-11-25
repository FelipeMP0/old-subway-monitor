package com.subwaymonitor.services;

import com.subwaymonitor.models.generic.LineCurrentStatus;

import java.util.List;

public interface LinesService {

    List<LineCurrentStatus> findStatuses();

}
