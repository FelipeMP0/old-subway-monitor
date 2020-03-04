package com.subwaymonitor.services;

import com.subwaymonitor.models.LineStatus;

import java.util.List;

public interface LineStatusService {

  LineStatus save(LineStatus lineStatus);

  List<LineStatus> listLastVerificatinForAllLines();

  Integer findLastVerification();
}
