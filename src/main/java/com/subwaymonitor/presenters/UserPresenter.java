package com.subwaymonitor.presenters;

import com.subwaymonitor.models.User;

public class UserPresenter {

    private String username;

    public UserPresenter(User user) {
        if (user != null) {
            this.username = user.getUsername();
        }
    }

    public String getUsername() {
        return username;
    }

}
