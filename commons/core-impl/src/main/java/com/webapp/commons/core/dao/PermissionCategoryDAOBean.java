/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.dao;

import com.webapp.commons.core.entities.PermissionCategory;
import javax.ejb.Stateless;


/**
 *
 * @author komilo
 */
@Stateless
public class PermissionCategoryDAOBean extends GenericDAOBean<PermissionCategory, String>
        implements PermissionCategoryDAOBeanLocal {

    public PermissionCategoryDAOBean() {
        super(PermissionCategory.class);
    }
}
