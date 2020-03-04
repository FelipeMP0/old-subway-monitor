package com.subwaymonitor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.ZonedDateTime;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

  private Integer id;

  private String slug;

  private String name;

  private Set<LineStatus> lineStatus;

  private ZonedDateTime creationDate;

  private ZonedDateTime updateDate;

  public Status() {
    this.creationDate = ZonedDateTime.now();
    this.updateDate = ZonedDateTime.now();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getName() {
    return name;
  }

  public Set<LineStatus> getLineStatus() {
    return lineStatus;
  }

  public void setLineStatus(Set<LineStatus> lineStatus) {
    this.lineStatus = lineStatus;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ZonedDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(ZonedDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public ZonedDateTime getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(ZonedDateTime updateDate) {
    this.updateDate = updateDate;
  }
}
