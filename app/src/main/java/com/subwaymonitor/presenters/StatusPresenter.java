package com.subwaymonitor.presenters;

import com.subwaymonitor.models.Status;

public class StatusPresenter {

  private String slug;

  private String name;

  public StatusPresenter(Status status) {
    if (status != null) {
      this.slug = status.getSlug();
      this.name = status.getName();
    }
  }

  public String getSlug() {
    return slug;
  }

  public String getName() {
    return name;
  }
}
