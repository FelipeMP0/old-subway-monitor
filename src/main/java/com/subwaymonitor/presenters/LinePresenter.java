package com.subwaymonitor.presenters;

import com.subwaymonitor.models.Line;

public class LinePresenter {

  private Integer number;

  private String name;

  private CompanyPresenter company;

  public LinePresenter(Line line) {
    if (line != null) {
      this.number = line.getNumber();
      this.name = line.getName();

      if (line.getCompany() != null) {
        this.company = new CompanyPresenter(line.getCompany());
      }
    }
  }

  public Integer getNumber() {
    return number;
  }

  public String getName() {
    return name;
  }

  public CompanyPresenter getCompany() {
    return company;
  }
}
