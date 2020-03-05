package com.subwaymonitor.controllers;

import com.subwaymonitor.models.LineStatus;
import com.subwaymonitor.presenters.LineStatusPresenter;
import com.subwaymonitor.services.LineStatusService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("line-status")
public class LineStatusController {

  private final LineStatusService service;

  @Autowired
  public LineStatusController(LineStatusService service) {
    this.service = service;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> listAllLines() {
    List<LineStatus> lineStatuses = this.service.listLastVerificatinForAllLines();

    List<LineStatusPresenter> lineStatusPresenters =
        lineStatuses.stream().map(LineStatusPresenter::new).collect(Collectors.toList());

    return new ResponseEntity<>(lineStatusPresenters, HttpStatus.OK);
  }
}
