package com.subwaymonitor.repositories.jpa;

import com.subwaymonitor.entities.UserEntity;
import com.subwaymonitor.exceptions.DatabaseException;
import com.subwaymonitor.models.UserModel;
import com.subwaymonitor.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public UserModel save(UserModel userModel) {
    try {
      UserEntity userEntity = new UserEntity(userModel);

      return this.entityManager.merge(userEntity).convert();
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public UserModel findByUsername(String username) {
    try {
      String query = "FROM UserEntity WHERE username = :username";

      UserEntity userEntity =
          this.entityManager
              .createQuery(query, UserEntity.class)
              .setParameter("username", username)
              .getSingleResult();

      return userEntity.convert();
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }
}
