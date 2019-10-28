package com.subwaymonitor.services;

import com.subwaymonitor.config.JwtTokenUtil;
import com.subwaymonitor.models.User;
import com.subwaymonitor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserService(UserRepository repository, JwtTokenUtil jwtTokenUtil) {
        this.repository = repository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String generateToken(String username) {
        UserDetails userDetails = this.loadUserByUsername(username);

        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public User save(User user) {
        return repository.save(user);
    }
}
