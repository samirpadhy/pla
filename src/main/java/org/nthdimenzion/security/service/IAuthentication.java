/*
 * Copyright (c) 1/23/15 2:47 PM.Nth Dimenzion, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package org.nthdimenzion.security.service;

/**
 * @author: Samir
 * @since 1.0 23/01/2015
 */
public interface IAuthentication {

    void failedLoginAttempt(String username);

    void successFullLogin(String username);

    String encryptPassword(String password);
}
