package com.subwaymonitor.services.impl;

import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.models.LineStatus;
import com.subwaymonitor.repositories.LineStatusRepository;
import com.subwaymonitor.services.LineStatusService;
import java.util.List;
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
    return this.repository.save(lineStatus);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public List<LineStatus> listLastVerificatinForAllLines() {
    Integer lastVerificationNumber = this.findLastVerification();

    return this.repository.listAllLinesByVerificationNumber(lastVerificationNumber);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
  public Integer findLastVerification() {
    try {
      return this.repository.findLastVerification();
    } catch (NotFoundException e) {
      e.printStackTrace();
    }
    return 1;
  }
}
