/*
 * Copyright (c) 1/23/15 3:47 PM.Nth Dimenzion, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package org.nthdimenzion.security.domain;

import com.google.common.collect.Sets;
import lombok.*;
import org.nthdimenzion.common.crud.ICrudEntity;
import org.nthdimenzion.utils.UtilValidator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

/**
 * @author: Samir
 * @since 1.0 23/01/2015
 */
@Entity
@ToString(of = "username")
@EqualsAndHashCode(of = "username")
@Getter(AccessLevel.PACKAGE)
@NamedQuery(name = "findUserLoginByUserName", query = "from UserLogin where username = :username ")
public class UserLogin implements ICrudEntity {

    public static Long ACTIVATOR_ID = 2L;

    @Getter(AccessLevel.NONE)
    private transient Integer MAX_INCORRECT_LOGIN_ATTEMPTS = Integer.valueOf(2);

    @Id
    private String id;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<SecurityGroup> securityGroups = Sets.newHashSet();

    private Boolean isEnabled = Boolean.FALSE;

    private Boolean isAccountNonLocked = Boolean.TRUE;

    private Integer numberOfFailedLoginAttempts = Integer.valueOf(0);

    private LocalDate validUptoDate;

    @Getter
    @Setter
    private Boolean isEmailVerified = Boolean.FALSE;

    protected UserLogin() {
        numberOfFailedLoginAttempts = 0;
    }

    public UserLogin(String userName, String password, String id) {
        this.username = userName;
        this.password = password;
        this.id = id;
    }

    public UserLogin add(SecurityGroup securityGroup) {
        this.securityGroups.add(securityGroup);
        return this;
    }

    public UserLogin updateSecurityGroup(SecurityGroup securityGroup) {
        this.securityGroups.clear();
        this.securityGroups.add(securityGroup);
        return this;
    }

    public UserLogin addAll(Set<SecurityGroup> securityGroups) {
        if (UtilValidator.isNotEmpty(securityGroups)) {
            for (SecurityGroup securityGroup : securityGroups) {
                add(securityGroup);
            }
        }
        return this;
    }

    public void disableLogin() {
        this.isEnabled = Boolean.FALSE;
    }

    public UserLogin changePassword(String password) {
        this.password = password;
        return this;
    }

    public void failedLoginAttempt() {
        numberOfFailedLoginAttempts = numberOfFailedLoginAttempts == null ? 0 : numberOfFailedLoginAttempts;
        numberOfFailedLoginAttempts = numberOfFailedLoginAttempts + 1;
        if (numberOfFailedLoginAttempts > MAX_INCORRECT_LOGIN_ATTEMPTS) {
            isAccountNonLocked = Boolean.FALSE;
        }
    }

    public void successfullLogin() {
        enableUserLogin();
    }

    public void enableUserLogin() {
        numberOfFailedLoginAttempts = 0;
        isEnabled = Boolean.TRUE;
        isAccountNonLocked = Boolean.TRUE;
    }

    public void unLockUserAccount() {
        enableUserLogin();

    }

    public boolean isValid() {
        return isValid(LocalDate.now());
    }

    boolean isValid(LocalDate date) {
        if (validUptoDate == null || validUptoDate.isAfter(date)) {
            return true;
        } else {
            return false;
        }
    }

    @Transient
    public boolean isEnabled() {
        return isEnabled;
    }

    @Transient
    public boolean isEmailNotVerified() {
        return !isEmailVerified;
    }
}
