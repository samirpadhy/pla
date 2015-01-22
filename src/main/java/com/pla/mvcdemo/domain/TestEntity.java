package com.pla.mvcdemo.domain;

import org.nthdimenzion.common.crud.ICrudEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author: Samir
 * @since 1.0
 */
@Entity
public class TestEntity implements ICrudEntity{

    @Id
    private String name="kk111";



}
