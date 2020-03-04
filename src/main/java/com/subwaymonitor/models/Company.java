package com.subwaymonitor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.ZonedDateTime;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {

  private Integer id;

  private String name;

  private Set<Line> lines;

  private ZonedDateTime creationDate;

  private ZonedDateTime updateDate;

  public Company() {
    this.creationDate = ZonedDateTime.now();
    this.updateDate = ZonedDateTime.now();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Line> getLines() {
    return lines;
  }

  public void setLines(Set<Line> lines) {
    this.lines = lines;
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
