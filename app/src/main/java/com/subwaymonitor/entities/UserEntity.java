package com.subwaymonitor.entities;

import com.subwaymonitor.models.UserModel;
import javax.persistence.*;

@Entity
@Table(name = "user", catalog = "subway_monitor")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "username", unique = true, nullable = false, updatable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  public UserEntity() {}

  public UserEntity(UserModel userModel) {
    this.id = userModel.getId();
    this.username = userModel.getUsername();
    this.password = userModel.getPassword();
  }

  public UserModel convert() {
    UserModel userModel = new UserModel();

    userModel.setId(this.id);
    userModel.setUsername(this.username);
    userModel.setPassword(this.password);

    return userModel;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
