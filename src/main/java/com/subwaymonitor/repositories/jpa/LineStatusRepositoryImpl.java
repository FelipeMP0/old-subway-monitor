package com.subwaymonitor.repositories.jpa;

import com.subwaymonitor.entities.LineStatusEntity;
import com.subwaymonitor.exceptions.DatabaseException;
import com.subwaymonitor.models.LineStatus;
import com.subwaymonitor.repositories.LineStatusRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LineStatusRepositoryImpl implements LineStatusRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LineStatus save(LineStatus lineStatus) {
        try {
            LineStatusEntity lineStatusEntity = new LineStatusEntity(lineStatus);

            return this.entityManager.merge(lineStatusEntity).convert();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

}
