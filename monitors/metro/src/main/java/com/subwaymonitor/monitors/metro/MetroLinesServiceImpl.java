package com.subwaymonitor.monitors.metro;

// TODO rewrite without spring web
public class MetroLinesServiceImpl {
  //
  //  private final MetroConfigProperties metroConfigProperties;
  //  private final RestTemplate httpClient;
  //  private final Logger logger;
  //
  //  public MetroLinesServiceImpl(MetroConfigProperties metroConfigProperties) {
  //    this.metroConfigProperties = metroConfigProperties;
  //    this.httpClient = new RestTemplateBuilder().setReadTimeout(Duration.ofMinutes(3)).build();
  //    this.logger = Logger.getLogger(this.getClass());
  //  }
  //
  //  @Override
  //  public List<LineCurrentStatus> findStatuses() {
  //    URI uri =
  //        UriComponentsBuilder.fromHttpUrl(this.metroConfigProperties.getLineStatusUrl())
  //            .path("/generic/Main/LineStatus")
  //            .build()
  //            .toUri();
  //
  //    List<LineCurrentStatus> lineCurrentStatuses = new ArrayList<>();
  //
  //    try {
  //      MetroLinesStatusResponse response =
  //          this.httpClient.getForObject(uri, MetroLinesStatusResponse.class);
  //
  //      if (response != null
  //          && response.getStatusMetro() != null
  //          && !CollectionUtils.isEmpty(response.getStatusMetro().getListLineStatus())) {
  //        response
  //            .getStatusMetro()
  //            .getListLineStatus()
  //            .forEach(
  //                metroLineStatus -> {
  //                  LineCurrentStatus lineCurrentStatus =
  //                      new LineCurrentStatus(
  //                          Integer.parseInt(metroLineStatus.getId()),
  //                          StatusEnum.fromText(metroLineStatus.getStatusMetro()).name());
  //
  //                  lineCurrentStatuses.add(lineCurrentStatus);
  //                });
  //      }
  //    } catch (HttpClientErrorException | HttpServerErrorException e) {
  //      this.logger.error("Error while trying to get metro current lines status", e);
  //    }
  //
  //    return lineCurrentStatuses;
  //  }
}
