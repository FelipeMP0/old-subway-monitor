package com.subwaymonitor.models;

import java.time.ZonedDateTime;

public class LineStatus {

  private LineStatus() {}

  private Integer id;

  private Line line;

  private Status status;

  private Integer verificationNumber;

  private ZonedDateTime creationDate;

  private ZonedDateTime updateDate;

  public static class Builder {

    private Integer id;

    private Line line;

    private Status status;

    private Integer verificationNumber = 1;

    private ZonedDateTime creationDate;

    private ZonedDateTime updateDate;

    public Builder() {
      this.creationDate = ZonedDateTime.now();
      this.updateDate = ZonedDateTime.now();
    }

    public Builder id(Integer id) {
      this.id = id;

      return this;
    }

    public Builder line(Line line) {
      this.line = line;

      return this;
    }

    public Builder status(Status status) {
      this.status = status;

      return this;
    }

    public Builder verificationNumber(Integer verificationNumber) {
      this.verificationNumber = verificationNumber;

      return this;
    }

    public Builder creationDate(ZonedDateTime creationDate) {
      this.creationDate = creationDate;

      return this;
    }

    public Builder updateDate(ZonedDateTime updateDate) {
      this.updateDate = updateDate;

      return this;
    }

    public LineStatus build() {
      LineStatus lineStatus = new LineStatus();

      lineStatus.id = this.id;
      lineStatus.line = this.line;
      lineStatus.status = this.status;
      lineStatus.verificationNumber = this.verificationNumber;
      lineStatus.creationDate = this.creationDate;
      lineStatus.updateDate = this.updateDate;

      return lineStatus;
    }
  }

  public Integer getId() {
    return id;
  }

  public Line getLine() {
    return line;
  }

  public Status getStatus() {
    return status;
  }

  public Integer getVerificationNumber() {
    return verificationNumber;
  }

  public ZonedDateTime getCreationDate() {
    return creationDate;
  }

  public ZonedDateTime getUpdateDate() {
    return updateDate;
  }
}
