package com.subwaymonitor.monitors.metro;

import com.subwaymonitor.appcommon.config.MetroConfigProperties;
import java.net.URI;
import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
class LineStatusApiService {

  private final RestTemplate httpClient;
  private final MetroConfigProperties configProperties;

  LineStatusApiService(MetroConfigProperties configProperties) {
    this.httpClient = new RestTemplateBuilder().setReadTimeout(Duration.ofSeconds(10)).build();
    this.configProperties = configProperties;
  }

  public MetroLinesStatusResponse getLineStatus() {
    URI uri =
        UriComponentsBuilder.fromHttpUrl(this.configProperties.getLineStatusUrl())
            .path("/generic/Main/LineStatus")
            .build()
            .toUri();

    ResponseEntity<MetroLinesStatusResponse> response =
        this.httpClient.getForEntity(uri, MetroLinesStatusResponse.class);

    return response.getBody();
  }
}
