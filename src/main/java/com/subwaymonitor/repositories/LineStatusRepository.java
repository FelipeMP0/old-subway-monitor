package com.subwaymonitor.repositories;

import com.subwaymonitor.models.LineStatus;

public interface LineStatusRepository {

    LineStatus save(LineStatus lineStatus);

}
