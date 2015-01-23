/*
 * Copyright (c) 1/23/15 4:18 PM.Nth Dimenzion, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package org.nthdimenzion.security.domain;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;
import org.nthdimenzion.common.crud.ICrudEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: Samir
 * @since 1.0 23/01/2015
 */
@Entity
@Immutable
@EqualsAndHashCode(of = "permissionId")
public class SecurityPermission implements ICrudEntity{

    @Id
    private Long id;

    @Column(unique = true)
    private String permissionId;

    private String description;

    SecurityPermission() {

    }

    public SecurityPermission(String permissionId,String description) {
        this.permissionId = permissionId;
        this.description = description;
    }


    @Override
    public String toString() {
        return permissionId;
    }
}
