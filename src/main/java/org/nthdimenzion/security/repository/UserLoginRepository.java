/*
 * Copyright (c) 1/23/15 4:09 PM.Nth Dimenzion, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package org.nthdimenzion.security.repository;

import org.nthdimenzion.security.domain.SecurityGroup;
import org.nthdimenzion.security.domain.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author: Samir
 * @since 1.0 23/01/2015
 */
@Service
@Transactional(readOnly = true)
public class UserLoginRepository {

    @PersistenceContext
    private EntityManager entityManager;

    UserLoginRepository(){

    }

    public UserLogin findUserLoginWithUserName(String username) {
        UserLogin userLogin = entityManager.createNamedQuery("findUserLoginByUserName", UserLogin.class).setParameter("username",username).getSingleResult();
        return userLogin;
    }

    public SecurityGroup findSecurityGroup(String name){
        SecurityGroup securityGroup = entityManager.createNamedQuery("findSecurityGroupByName", SecurityGroup.class).setParameter("name",name).getSingleResult();
        return securityGroup;
    }
}
