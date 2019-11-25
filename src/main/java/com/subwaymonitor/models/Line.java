package com.subwaymonitor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.ZonedDateTime;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Line {

    private Integer id;

    private Integer number;

    private String name;

    private Set<LineStatus> status;

    private Company company;

    private ZonedDateTime creationDate;

    private ZonedDateTime updateDate;

    public Line() {
        this.creationDate = ZonedDateTime.now();
        this.updateDate = ZonedDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LineStatus> getStatus() {
        return status;
    }

    public void setStatus(Set<LineStatus> status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
