package com.subwaymonitor.repositories;

import com.subwaymonitor.models.User;

public interface UserRepository {

    User save(User user);

    User findByUsername(String username);

}
