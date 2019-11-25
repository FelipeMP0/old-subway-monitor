package com.subwaymonitor.services.impl;

import com.subwaymonitor.config.security.JwtTokenUtil;
import com.subwaymonitor.models.User;
import com.subwaymonitor.repositories.UserRepository;
import com.subwaymonitor.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Qualifier("UserService")
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

        return this.jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public User save(User user) {
        user.setPassword(EncryptionUtil.BCryptEncode(user.getPassword()));
        return this.repository.save(user);
    }

}
