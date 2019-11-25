package com.subwaymonitor.services.impl;

import com.subwaymonitor.models.LineStatus;
import com.subwaymonitor.repositories.LineStatusRepository;
import com.subwaymonitor.services.LineStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LineStatusServiceImpl implements LineStatusService {

    private final LineStatusRepository repository;

    public LineStatusServiceImpl(LineStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public LineStatus save(LineStatus lineStatus) {
        return repository.save(lineStatus);
    }

}
