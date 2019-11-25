package com.subwaymonitor.repositories;

import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.models.Line;

public interface LineRepository {

    Line findByNumber(Integer number) throws NotFoundException;

}
