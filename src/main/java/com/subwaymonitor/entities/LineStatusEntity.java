package com.subwaymonitor.entities;

import javax.persistence.*;

import java.time.ZonedDateTime;

import static com.subwaymonitor.constants.DatabaseSchemas.SUBWAY_MONITOR;

@Entity
@Table(name = "line_status", catalog = SUBWAY_MONITOR)
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

    @Column(name = "creation_date", nullable = false, updatable = false)
    private ZonedDateTime creationDate;

    @Column(name = "update_date", nullable = false)
    private ZonedDateTime updateDate;

    public LineStatusEntity() {
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
