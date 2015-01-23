/*
 * Copyright (c) 1/23/15 5:06 PM.Nth Dimenzion, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package org.nthdimenzion.security.service;


import org.nthdimenzion.presentation.LoggedInUserHolder;
import org.nthdimenzion.security.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Samir
 * @since 1.0 23/01/2015
 */
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private IAuthentication authenticationService;

    @Autowired
    private UserDetailsService userService;


    @Autowired
    private UserLoginRepository userLoginRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LoggedInUserHolder.clearLoggedInUser();
        request.getSession().invalidate();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        LoggedInUserHolder.clearLoggedInUser();
        final String username = request.getParameter("username");
        LoggedInUserHolder.setUserName(username);
        super.onAuthenticationSuccess(request, response, auth);
    }

}
