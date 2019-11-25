package com.subwaymonitor.services.impl;

import com.subwaymonitor.models.generic.LineCurrentStatus;
import com.subwaymonitor.services.LinesService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("privateLinesService")
public class PrivateLinesServiceImpl implements LinesService {

    @Override
    public List<LineCurrentStatus> findStatuses() {
        return null;
    }

}
