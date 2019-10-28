package com.subwaymonitor.repositories.jpa;

import com.subwaymonitor.entities.UserEntity;
import com.subwaymonitor.models.User;
import com.subwaymonitor.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity(user);

        return this.entityManager.merge(userEntity).convert();
    }

    @Override
    public User findByUsername(String username) {
        String query = "FROM UserEntity WHERE username = :username";

        UserEntity userEntity = this.entityManager.createQuery(query, UserEntity.class)
                .setParameter("username", username)
                .getSingleResult();

        return userEntity.convert();
    }
}
