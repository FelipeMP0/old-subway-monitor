package com.subwaymonitor.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class ValidationMessagesReader {

  private Properties properties;

  public ValidationMessagesReader() {
    loadValidationMessages();
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public String getProperty(String key) {
    String property = key;
    if (key != null && this.properties != null && this.properties.containsKey(key)) {
      property = this.properties.getProperty(key);
    }
    return property;
  }

  private void loadValidationMessages() {
    this.properties = new Properties();
    InputStream in = getClass().getResourceAsStream("/ValidationMessages.properties");
    try {
      this.properties.load(new InputStreamReader(in, StandardCharsets.UTF_8));
      in.close();
    } catch (IOException ignored) {
    }
  }
}
