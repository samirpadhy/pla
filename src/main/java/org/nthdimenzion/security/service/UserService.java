/*
 * Copyright (c) 1/23/15 2:46 PM.Nth Dimenzion, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package org.nthdimenzion.security.service;

import org.nthdimenzion.security.domain.SystemUser;
import org.nthdimenzion.security.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author: Samir
 * @since 1.0 23/01/2015
 */
@Service
public class UserService implements UserDetailsService, IAuthentication {

    private UserDetailsService userDetailsService;

    private ShaPasswordEncoder passwordEncoder;

    private SystemWideSaltSource saltSource;

    @PersistenceContext
    private EntityManager entityManager;

    private UserLoginRepository userLoginRepository;

    UserService() {
    }

    @Autowired
    public UserService(UserDetailsService userValidationService, ShaPasswordEncoder passwordEncoder, SystemWideSaltSource saltSource,UserLoginRepository userLoginRepository) {
        this.userDetailsService = userValidationService;
        this.passwordEncoder = passwordEncoder;
        this.saltSource = saltSource;
        this.userLoginRepository = userLoginRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        SystemUser systemUser = new SystemUser(userDetails);
        return systemUser;
    }


    @Override
    public void failedLoginAttempt(String username) {
    }

    @Override
    public void successFullLogin(String username) {
    }

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encodePassword(password, saltSource.getSystemWideSalt());
    }

}
