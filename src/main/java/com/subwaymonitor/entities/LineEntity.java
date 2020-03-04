package com.subwaymonitor.entities;

import com.subwaymonitor.models.Line;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static com.subwaymonitor.constants.DatabaseSchemas.SUBWAY_MONITOR;

@Entity
@Table(name = "line", catalog = SUBWAY_MONITOR)
public class LineEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "number", unique = true, nullable = false)
  private Integer number;

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "line", cascade = CascadeType.ALL)
  private Set<LineStatusEntity> status;

  @JoinColumn(name = "id_company")
  @ManyToOne(fetch = FetchType.LAZY)
  private CompanyEntity company;

  @Column(name = "creation_date", nullable = false, updatable = false)
  private ZonedDateTime creationDate;

  @Column(name = "update_date", nullable = false)
  private ZonedDateTime updateDate;

  public LineEntity() {}

  public LineEntity(Line line) {
    if (line != null) {
      this.id = line.getId();
      this.number = line.getNumber();
      this.name = line.getName();

      if (!CollectionUtils.isEmpty(line.getStatus())) {
        this.status =
            line.getStatus().stream().map(LineStatusEntity::new).collect(Collectors.toSet());
        this.status.forEach(statusEntity -> statusEntity.setLine(this));
      }

      this.company = new CompanyEntity(line.getCompany());
      this.creationDate = line.getCreationDate();
      this.updateDate = line.getUpdateDate();
    }
  }

  public Line convert() {
    Line line = new Line();

    line.setId(this.id);
    line.setNumber(this.number);
    line.setName(this.name);
    line.setCompany(this.company.convert());
    line.setCreationDate(this.creationDate);
    line.setUpdateDate(this.updateDate);

    return line;
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

  public Set<LineStatusEntity> getStatus() {
    return status;
  }

  public void setStatus(Set<LineStatusEntity> status) {
    this.status = status;
  }

  public CompanyEntity getCompany() {
    return company;
  }

  public void setCompany(CompanyEntity company) {
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
