/*
 * Copyright (c) 1/22/15 8:50 PM.Nth Dimenzion, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package org.nthdimenzion.security.configuration;

import org.nthdimenzion.security.service.AuthenticationFailureHandler;
import org.nthdimenzion.security.service.AuthenticationSuccessHandler;
import org.nthdimenzion.security.service.Http401UnauthorizedEntryPoint;
import org.nthdimenzion.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 * @author: Samir
 * @since 1.0 23/01/2015
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcDaoImpl userValidationService;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @Autowired
    private SystemWideSaltSource saltSource;

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated()
                .and().formLogin().usernameParameter("username").passwordParameter("password")
                .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
                .failureUrl("/login?error=1").loginPage("/login").permitAll().and().logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider);
    }


    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder();
    }


    @Bean
    public SystemWideSaltSource saltSource() {
        SystemWideSaltSource systemWideSaltSource = new SystemWideSaltSource();
        systemWideSaltSource.setSystemWideSalt("pla");
        return systemWideSaltSource;
    }

    @Bean
    public Http401UnauthorizedEntryPoint authenticationEntryPoint() {
        return new Http401UnauthorizedEntryPoint();
    }

    @Bean
    public JdbcDaoImpl userValidationService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setUsersByUsernameQuery(USERS_BY_USERNAME_QUERY);
        jdbcDao.setDataSource(dataSource);
        jdbcDao.setEnableGroups(true);
        jdbcDao.setEnableAuthorities(false);
        jdbcDao.setGroupAuthoritiesByUsernameQuery(GROUP_AUTHORITIES_BY_USERNAME_QUERY);
        return jdbcDao;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setSaltSource(saltSource);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

    private static final String GROUP_AUTHORITIES_BY_USERNAME_QUERY = "SELECT sg.id group_id,sg.name group_name,sp.PERMISSION_ID permission" +
            " from USER_LOGIN ul,SECURITY_GROUP sg,SECURITY_PERMISSION sp,\n" +
            " USER_LOGIN_SECURITY_GROUPS ulsg,SECURITY_GROUP_SECURITY_PERMISSIONS sgsp\n" +
            " where ul.username =? and ul.id = ulsg.USER_LOGIN_ID and ulsg.SECURITY_GROUPS_ID = sg.id and sgsp.SECURITY_GROUP_ID = sg.id" +
            " and sgsp.SECURITY_PERMISSIONS_ID = sp.id";

    private static final String USERS_BY_USERNAME_QUERY = "select ul.username,ul.password,ul.is_enabled from USER_LOGIN ul where ul.username = ?";
}
