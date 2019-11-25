package com.subwaymonitor.services.impl;

import com.subwaymonitor.config.properties.MetroConfigProperties;
import com.subwaymonitor.enums.StatusEnum;
import com.subwaymonitor.models.generic.LineCurrentStatus;
import com.subwaymonitor.models.metro.MetroLinesStatusResponse;
import com.subwaymonitor.services.LinesService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("metroLinesService")
public class MetroLinesServiceImpl implements LinesService {

    private final MetroConfigProperties metroConfigProperties;
    private final RestTemplate httpClient;
    private final Logger logger;

    public MetroLinesServiceImpl(MetroConfigProperties metroConfigProperties) {
        this.metroConfigProperties = metroConfigProperties;
        this.httpClient = new RestTemplateBuilder().setReadTimeout(Duration.ofMinutes(3)).build();
        this.logger = Logger.getLogger(this.getClass());
    }

    @Override
    public List<LineCurrentStatus> findStatuses() {
        System.out.println("BUSCA POR INFORMAÇÕES DAS LINHAS DO METRÔ INICIADA");
        URI uri = UriComponentsBuilder
                .fromHttpUrl(this.metroConfigProperties.getLineStatusUrl())
                .path("/generic/Main/LineStatus")
                .build()
                .toUri();

        List<LineCurrentStatus> lineCurrentStatuses = new ArrayList<>();

        try {
            MetroLinesStatusResponse response = this.httpClient.getForObject(uri, MetroLinesStatusResponse.class);

            if (response != null &&
                    response.getStatusMetro() != null &&
                    !CollectionUtils.isEmpty(response.getStatusMetro().getListLineStatus())) {
                response.getStatusMetro().getListLineStatus().forEach(metroLineStatus -> {
                    LineCurrentStatus lineCurrentStatus
                            = new LineCurrentStatus(Integer.parseInt(metroLineStatus.getId()),
                            StatusEnum.fromText(metroLineStatus.getStatusMetro()).name());

                    lineCurrentStatuses.add(lineCurrentStatus);
                });
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            this.logger.error("Error while trying to get metro current lines status", e);
        }

        return lineCurrentStatuses;
    }

}
