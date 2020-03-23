package com.subwaymonitor.appcommon.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "metro")
public class MetroConfigProperties {

  private String lineStatusUrl;

  public String getLineStatusUrl() {
    return lineStatusUrl;
  }

  public void setLineStatusUrl(String lineStatusUrl) {
    this.lineStatusUrl = lineStatusUrl;
  }
}
