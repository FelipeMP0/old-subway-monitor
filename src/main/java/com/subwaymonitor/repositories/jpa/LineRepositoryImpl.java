package com.subwaymonitor.repositories.jpa;

import com.subwaymonitor.entities.LineEntity;
import com.subwaymonitor.exceptions.DatabaseException;
import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.models.Line;
import com.subwaymonitor.repositories.LineRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class LineRepositoryImpl implements LineRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public Line findByNumber(Integer number) throws NotFoundException {
    try {
      String query = "FROM LineEntity l JOIN FETCH l.company WHERE l.number = :number";

      LineEntity lineEntity =
          this.entityManager
              .createQuery(query, LineEntity.class)
              .setParameter("number", number)
              .getSingleResult();

      return lineEntity.convert();
    } catch (NoResultException e) {
      throw new NotFoundException("line", "line.notFound");
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }
}
