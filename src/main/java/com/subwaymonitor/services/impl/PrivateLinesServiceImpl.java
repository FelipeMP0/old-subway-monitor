package com.subwaymonitor.services.impl;

import com.subwaymonitor.enums.StatusEnum;
import com.subwaymonitor.models.generic.LineCurrentStatus;
import com.subwaymonitor.services.LinesService;
import com.subwaymonitor.utils.WebDriverHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("privateLinesService")
public class PrivateLinesServiceImpl implements LinesService {

    private static final String VIA_QUATRO_URL = "https://www.viamobilidade.com.br/";

    @Override
    public List<LineCurrentStatus> findStatuses() {
        WebDriverHandler webDriverHandler = new WebDriverHandler();

        List<LineCurrentStatus> lineCurrentStatuses = new ArrayList<>();
        try {
            webDriverHandler.connect(VIA_QUATRO_URL);

            LineCurrentStatus line4CurrentStatus = this.findLine4Information(webDriverHandler.getDriver());

            LineCurrentStatus line5CurrentStatus = this.findLine5Information(webDriverHandler.getDriver());

            lineCurrentStatuses.add(line4CurrentStatus);
            lineCurrentStatuses.add(line5CurrentStatus);
        } finally {
            webDriverHandler.disconnect();
        }

        return lineCurrentStatuses;
    }

    private LineCurrentStatus findLine4Information(WebDriver driver) {
        WebElement main = driver.findElement(By.className("operacao-home"))
                                .findElement(By.className("card-group"))
                                .findElement(By.className("outras-linhas"))
                                .findElement(By.className("card-body"));

        String lineNumber = main.findElement(By.className("linha-cor-amarela")).getText().trim();
        String lineStatus = main.findElement(By.className("text-status")).getText().trim();

        return new LineCurrentStatus(Integer.parseInt(lineNumber), StatusEnum.fromText(lineStatus).name());
    }

    private LineCurrentStatus findLine5Information(WebDriver driver) {
        WebElement main = driver.findElement(By.className("operacao-home"))
                                .findElement(By.className("operacao-linhas"))
                                .findElement(By.tagName("ul"))
                                .findElement(By.className("linha-cor-lilas"));

        String lineNumber = main.findElement(By.className("badge-linha")).getText().trim();
        String lineStatus = main.findElement(By.className("badge-status")).getText().trim();

        return new LineCurrentStatus(Integer.parseInt(lineNumber), StatusEnum.fromText(lineStatus).name());
    }

}
