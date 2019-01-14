/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author persistence
 */
@Entity
@Table(name = "core_permissions")
public class Permission extends BaseEntity {
    
    @Id
    @Column(name = "code", nullable = false)
    private String code;
    
    @Size(max = 250)
    @Column(name = "label")
    private String label;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_code", nullable = false)
    private PermissionCategory category;

    public Permission() {
    }

    public Permission(String code) {
        this.code = code;
    }

    public Permission(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public Permission(String code, String label, PermissionCategory category) {
        this.code = code;
        this.label = label;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public PermissionCategory getCategory() {
        return category;
    }

    public void setCategory(PermissionCategory category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Permission other = (Permission) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return "Permission{" + "code=" + code + '}';
    }
}
