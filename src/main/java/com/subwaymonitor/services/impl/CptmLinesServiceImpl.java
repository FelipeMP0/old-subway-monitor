package com.subwaymonitor.services.impl;

import com.subwaymonitor.enums.StatusEnum;
import com.subwaymonitor.models.generic.LineCurrentStatus;
import com.subwaymonitor.services.LinesService;
import com.subwaymonitor.utils.WebDriverHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("cptmLinesService")
public class CptmLinesServiceImpl implements LinesService {

    private static final String VIA_QUATRO_URL = "https://www.viamobilidade.com.br/";

    @Override
    public List<LineCurrentStatus> findStatuses() {
        WebDriverHandler webDriverHandler = new WebDriverHandler();

        List<LineCurrentStatus> lineCurrentStatuses = new ArrayList<>();
        try {
            webDriverHandler.connect(VIA_QUATRO_URL);

            LineCurrentStatus line9CurrentStatus = this.findLine9Information(webDriverHandler.getDriver());

            LineCurrentStatus line11CurrentStatus = this.findLine11Information(webDriverHandler.getDriver());

            lineCurrentStatuses.add(line9CurrentStatus);
            lineCurrentStatuses.add(line11CurrentStatus);
        } finally {
            webDriverHandler.disconnect();
        }

        return lineCurrentStatuses;
    }

    private LineCurrentStatus findLine11Information(WebDriver driver) {
        WebElement lineNumberElement = driver.findElement(By.className("linha-cor-esmeralda"));

        WebElement parent = (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].parentNode;", lineNumberElement);

        String lineNumber = lineNumberElement.getText().trim();
        String lineStatus = parent.findElement(By.className("text-status")).getText().trim();

        return new LineCurrentStatus(Integer.parseInt(lineNumber), StatusEnum.fromText(lineStatus).name());
    }

    private LineCurrentStatus findLine9Information(WebDriver driver) {
        WebElement lineNumberElement = driver.findElement(By.className("linha-cor-coral"));

        WebElement parent = (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].parentNode;", lineNumberElement);

        String lineNumber = lineNumberElement.getText().trim();
        String lineStatus = parent.findElement(By.className("text-status")).getText().trim();

        return new LineCurrentStatus(Integer.parseInt(lineNumber), StatusEnum.fromText(lineStatus).name());
    }

}
