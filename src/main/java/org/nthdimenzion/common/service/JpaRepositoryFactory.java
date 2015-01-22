package org.nthdimenzion.common.service;

import com.google.common.base.Preconditions;
import org.nthdimenzion.common.crud.ICrudEntity;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author: Samir
 * @since 1.0
 */
@Service
public class JpaRepositoryFactory {

    @PersistenceContext
    private EntityManager entityManager;

    public CrudRepository<ICrudEntity, ?> getCrudRepository(ICrudEntity crudEntity) {
        Preconditions.checkNotNull(entityManager);
        CrudRepository<ICrudEntity, ?> crudRepository = new SimpleJpaRepository(crudEntity.getClass(), entityManager);
        return crudRepository;
    }

}
