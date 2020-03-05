package com.subwaymonitor.repositories.jpa;

import com.subwaymonitor.appcommon.exceptions.DatabaseException;
import com.subwaymonitor.entities.LineStatusEntity;
import com.subwaymonitor.exceptions.NotFoundException;
import com.subwaymonitor.models.LineStatus;
import com.subwaymonitor.repositories.LineStatusRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class LineStatusRepositoryImpl implements LineStatusRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public LineStatus save(LineStatus lineStatus) {
    try {
      LineStatusEntity lineStatusEntity = new LineStatusEntity(lineStatus);

      return this.entityManager.merge(lineStatusEntity).convert();
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public List<LineStatus> listAllLinesByVerificationNumber(Integer verificationNumber) {
    try {
      StringBuilder query =
          new StringBuilder("FROM LineStatusEntity ls ")
              .append("JOIN FETCH ls.line l ")
              .append("JOIN FETCH ls.status s ")
              .append("WHERE ls.verificationNumber = :verificationNumber ")
              .append("ORDER BY l.number DESC");

      List<LineStatusEntity> lineStatusEntities =
          this.entityManager
              .createQuery(query.toString(), LineStatusEntity.class)
              .setParameter("verificationNumber", verificationNumber)
              .getResultList();

      return lineStatusEntities
          .stream()
          .map(LineStatusEntity::convert)
          .collect(Collectors.toList());
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public Integer findLastVerification() throws NotFoundException {
    try {
      StringBuilder query =
          new StringBuilder("SELECT verificationNumber FROM LineStatusEntity ")
              .append("ORDER BY verificationNumber DESC");

      return this.entityManager
          .createQuery(query.toString(), Integer.class)
          .setMaxResults(1)
          .setLockMode(LockModeType.PESSIMISTIC_WRITE)
          .getSingleResult();
    } catch (NoResultException e) {
      throw new NotFoundException("verificationNumber", "verificationNumber.notFound");
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }
}
