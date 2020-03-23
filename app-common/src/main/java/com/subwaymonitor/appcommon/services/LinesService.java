package com.subwaymonitor.appcommon.services;

import com.subwaymonitor.appcommon.models.LineCurrentStatus;
import java.util.List;

public interface LinesService {

  List<LineCurrentStatus> findStatuses();
}
