package com.subwaymonitor.appcommon.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverHandler {

  private WebDriver driver = null;

  public void connect(String url) {
    String driverPath = this.getChromeDriverPath();

    System.setProperty("webdriver.chrome.driver", driverPath);

    ChromeOptions options = new ChromeOptions();

    options.setHeadless(true);

    this.driver = new ChromeDriver(options);

    this.driver.get(url);
  }

  public void disconnect() {
    if (this.driver != null) {
      this.driver.quit();
    }
  }

  private String getChromeDriverPath() {
    return "./chromedriver";
  }

  public WebDriver getDriver() {
    return driver;
  }
}
