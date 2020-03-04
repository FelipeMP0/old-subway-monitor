package com.subwaymonitor.repositories;

import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.models.Status;

public interface StatusRepository {

  Status findBySlug(String slug) throws NotFoundException;
}
