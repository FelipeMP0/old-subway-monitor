package com.subwaymonitor.entities;

import com.subwaymonitor.constants.DatabaseSchemas;
import com.subwaymonitor.models.Status;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;
import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "status", catalog = DatabaseSchemas.SUBWAY_MONITOR)
public class StatusEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "slug", nullable = false)
  private String slug;

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
  private Set<LineStatusEntity> lineStatus;

  @Column(name = "creation_date", nullable = false, updatable = false)
  private ZonedDateTime creationDate;

  @Column(name = "update_date", nullable = false)
  private ZonedDateTime updateDate;

  public StatusEntity() {}

  public StatusEntity(Status status) {
    if (status != null) {
      this.id = status.getId();
      this.slug = status.getSlug();
      this.name = status.getName();

      if (!CollectionUtils.isEmpty(status.getLineStatus())) {
        this.lineStatus =
            status.getLineStatus().stream().map(LineStatusEntity::new).collect(Collectors.toSet());
        this.lineStatus.forEach(lineStatusEntity -> lineStatusEntity.setStatus(this));
      }

      this.creationDate = status.getCreationDate();
      this.updateDate = status.getUpdateDate();
    }
  }

  public Status convert() {
    Status status = new Status();

    status.setId(this.id);
    status.setSlug(this.slug);
    status.setName(this.name);
    status.setCreationDate(this.creationDate);
    status.setUpdateDate(this.updateDate);

    return status;
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

  public void setName(String name) {
    this.name = name;
  }

  public Set<LineStatusEntity> getLineStatus() {
    return lineStatus;
  }

  public void setLineStatus(Set<LineStatusEntity> lineStatus) {
    this.lineStatus = lineStatus;
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
