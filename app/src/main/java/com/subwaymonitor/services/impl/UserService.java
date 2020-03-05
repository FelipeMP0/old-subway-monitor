package com.subwaymonitor.services.impl;

import com.subwaymonitor.config.security.JwtTokenUtil;
import com.subwaymonitor.models.UserModel;
import com.subwaymonitor.repositories.UserRepository;
import com.subwaymonitor.utils.EncryptionUtil;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    UserModel userModel = this.repository.findByUsername(username);

    return new User(userModel.getUsername(), userModel.getPassword(), new ArrayList<>());
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public UserModel save(UserModel userModel) {
    userModel.setPassword(EncryptionUtil.BCryptEncode(userModel.getPassword()));
    return this.repository.save(userModel);
  }
}
