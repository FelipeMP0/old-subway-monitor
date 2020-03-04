package com.subwaymonitor.repositories;

import com.subwaymonitor.models.UserModel;

public interface UserRepository {

  UserModel save(UserModel userModel);

  UserModel findByUsername(String username);
}
