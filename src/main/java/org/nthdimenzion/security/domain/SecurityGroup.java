/*
 * Copyright (c) 1/23/15 4:11 PM.Nth Dimenzion, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package org.nthdimenzion.security.domain;

import com.google.common.collect.Sets;
import org.nthdimenzion.common.crud.ICrudEntity;
import org.nthdimenzion.utils.UtilValidator;

import javax.persistence.*;
import java.util.Set;

/**
 * @author: Samir
 * @since 1.0 23/01/2015
 */
@Entity
@NamedQuery(name = "findSecurityGroupByName", query = "from  SecurityGroup where name = :name")
public class SecurityGroup implements ICrudEntity {

    @Id
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<SecurityPermission> securityPermissions = Sets.newHashSet();

    @Column(unique = true)
    private String name;


    SecurityGroup() {
    }


    public SecurityGroup add(SecurityPermission securityPermission) {
        this.securityPermissions.add(securityPermission);
        return this;
    }

    public SecurityGroup addAll(Set<SecurityPermission> securityPermissions) {
        if (UtilValidator.isNotEmpty(securityPermissions)) {
            for (SecurityPermission securityPermission : securityPermissions) {
                add(securityPermission);
            }
        }
        return this;
    }


    @Override
    public String toString() {
        return name;
    }
}
