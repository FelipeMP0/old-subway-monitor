package com.subwaymonitor.entities;

import com.subwaymonitor.constants.DatabaseSchemas;
import com.subwaymonitor.models.LineStatus;
import com.subwaymonitor.models.LineStatus.Builder;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "line_status", catalog = DatabaseSchemas.SUBWAY_MONITOR)
public class LineStatusEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @JoinColumn(name = "id_line")
  @ManyToOne(fetch = FetchType.LAZY)
  private LineEntity line;

  @JoinColumn(name = "id_status")
  @ManyToOne(fetch = FetchType.LAZY)
  private StatusEntity status;

  @Column(name = "verification_number")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer verificationNumber;

  @Column(name = "creation_date", nullable = false, updatable = false)
  private ZonedDateTime creationDate;

  @Column(name = "update_date", nullable = false)
  private ZonedDateTime updateDate;

  public LineStatusEntity() {}

  public LineStatusEntity(LineStatus lineStatus) {
    if (lineStatus != null) {
      this.id = lineStatus.getId();

      if (lineStatus.getLine() != null) {
        this.line = new LineEntity(lineStatus.getLine());
      }

      if (lineStatus.getStatus() != null) {
        this.status = new StatusEntity(lineStatus.getStatus());
      }

      this.verificationNumber = lineStatus.getVerificationNumber();
      this.creationDate = lineStatus.getCreationDate();
      this.updateDate = lineStatus.getUpdateDate();
    }
  }

  public LineStatus convert() {
    Builder builder = new Builder().id(this.id);

    if (this.line != null) {
      builder.line(this.line.convert());
    }

    if (this.status != null) {
      builder.status(this.status.convert());
    }

    builder.verificationNumber(this.verificationNumber);
    builder.creationDate(this.creationDate);
    builder.updateDate(this.updateDate);

    return builder.build();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LineEntity getLine() {
    return line;
  }

  public void setLine(LineEntity line) {
    this.line = line;
  }

  public StatusEntity getStatus() {
    return status;
  }

  public void setStatus(StatusEntity status) {
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
