/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author persistence
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    protected static final long serialVersionUID = 1L;
    
    @Column(name = "version", nullable = false)
    private int version = 1;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
