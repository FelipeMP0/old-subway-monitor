package com.subwaymonitor.entities;

import com.subwaymonitor.models.Company;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static com.subwaymonitor.constants.DatabaseSchemas.SUBWAY_MONITOR;

@Entity
@Table(name = "company", catalog = SUBWAY_MONITOR)
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.ALL)
    private Set<LineEntity> lines;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private ZonedDateTime creationDate;

    @Column(name = "update_date", nullable = false)
    private ZonedDateTime updateDate;

    public CompanyEntity() {
    }

    public CompanyEntity(Company company) {
        if (company != null) {
            this.id = company.getId();
            this.name = company.getName();

            if (!CollectionUtils.isEmpty(company.getLines())) {
                this.lines = company.getLines().stream().map(LineEntity::new).collect(Collectors.toSet());
                ;
                this.lines.forEach(lineEntity -> lineEntity.setCompany(this));
            }

            this.creationDate = company.getCreationDate();
            this.updateDate = company.getUpdateDate();
        }
    }

    public Company convert() {
        Company company = new Company();

        company.setId(this.id);
        company.setName(this.name);
        company.setCreationDate(this.creationDate);
        company.setUpdateDate(this.updateDate);

        return company;
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

    public Set<LineEntity> getLines() {
        return lines;
    }

    public void setLines(Set<LineEntity> lines) {
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
