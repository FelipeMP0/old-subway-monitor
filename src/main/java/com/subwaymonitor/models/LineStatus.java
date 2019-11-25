package com.subwaymonitor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LineStatus {

    private Integer id;

    private Line line;

    private Status status;

    private Integer verificationNumber;

    private ZonedDateTime creationDate;

    private ZonedDateTime updateDate;

    public LineStatus() {
        this.creationDate = ZonedDateTime.now();
        this.updateDate = ZonedDateTime.now();
    }

    public LineStatus(Line line, Status status) {
        this();

        this.line = line;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getVerificationNumber() {
        return verificationNumber;
    }

    public void setVerificationNumber(Integer verificationNumber) {
        this.verificationNumber = verificationNumber;
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
