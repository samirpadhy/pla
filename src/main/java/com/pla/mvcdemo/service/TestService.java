package com.pla.mvcdemo.service;

import com.pla.mvcdemo.domain.TestEntity;
import org.nthdimenzion.common.crud.ICrudEntity;
import org.nthdimenzion.common.service.JpaRepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Samir
 * @since 1.0
 */
@Service
public class TestService {

    @Autowired
    private JpaRepositoryFactory jpaRepositoryFactory;

    @Transactional
    public void testMethod(){
        ICrudEntity person = new TestEntity();
        CrudRepository crudRepository = jpaRepositoryFactory.getCrudRepository(person);
        crudRepository.save(person);
    }

}
