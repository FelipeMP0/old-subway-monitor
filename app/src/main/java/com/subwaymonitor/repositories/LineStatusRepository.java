package com.subwaymonitor.repositories;

import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.models.LineStatus;
import java.util.List;

public interface LineStatusRepository {

  LineStatus save(LineStatus lineStatus);

  List<LineStatus> listAllLinesByVerificationNumber(Integer verificationNumber);

  Integer findLastVerification() throws NotFoundException;
}
