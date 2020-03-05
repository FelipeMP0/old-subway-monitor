package com.subwaymonitor.repositories.jpa;

import com.subwaymonitor.appcommon.exceptions.DatabaseException;
import com.subwaymonitor.entities.StatusEntity;
import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.models.Status;
import com.subwaymonitor.repositories.StatusRepository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class StatusRepositoryImpl implements StatusRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public Status findBySlug(String slug) throws NotFoundException {
    try {
      String query = "FROM StatusEntity WHERE slug = :slug";

      StatusEntity status =
          this.entityManager
              .createQuery(query, StatusEntity.class)
              .setParameter("slug", slug)
              .getSingleResult();

      return status.convert();
    } catch (NoResultException e) {
      throw new NotFoundException("status", "status.notFound");
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }
}
