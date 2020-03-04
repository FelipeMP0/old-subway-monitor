package com.subwaymonitor.presenters;

import com.subwaymonitor.models.UserModel;

public class UserPresenter {

  private String username;

  public UserPresenter(UserModel userModel) {
    if (userModel != null) {
      this.username = userModel.getUsername();
    }
  }

  public String getUsername() {
    return username;
  }
}
