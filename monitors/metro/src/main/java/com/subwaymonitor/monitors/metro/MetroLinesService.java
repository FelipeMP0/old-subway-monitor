package com.subwaymonitor.monitors.metro;

import com.subwaymonitor.appcommon.models.LineCurrentStatus;
import com.subwaymonitor.appcommon.services.LinesService;
import com.subwaymonitor.monitors.metro.MetroLinesStatusResponse.StatusMetro;
import com.subwaymonitor.monitors.metro.MetroLinesStatusResponse.StatusMetro.MetroLineStatus;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Qualifier("MetroLinesService")
public class MetroLinesService implements LinesService {

  private LineStatusApiService lineStatusApiService;

  @Autowired
  public MetroLinesService(LineStatusApiService lineStatusApiService) {
    this.lineStatusApiService = lineStatusApiService;
  }

  @Override
  public List<LineCurrentStatus> findStatuses() {
    MetroLinesStatusResponse lineStatusResponse = this.lineStatusApiService.getLineStatus();

    return this.mapMetroLineStatusToLineCurrentStatus(lineStatusResponse);
  }

  private List<LineCurrentStatus> mapMetroLineStatusToLineCurrentStatus(
      MetroLinesStatusResponse response) {
    List<LineCurrentStatus> lineCurrentStatuses = new ArrayList<>();

    if (response != null) {
      StatusMetro statusMetro = response.statusMetro;

      if (statusMetro != null) {
        List<MetroLineStatus> listLineStatus = statusMetro.listLineStatus;

        if (!CollectionUtils.isEmpty(listLineStatus)) {
          listLineStatus.forEach(
              lineStatus ->
                  lineCurrentStatuses.add(
                      new LineCurrentStatus(
                          Integer.parseInt(lineStatus.id), lineStatus.statusMetro)));
        }
      }
    }

    return lineCurrentStatuses;
  }
}
