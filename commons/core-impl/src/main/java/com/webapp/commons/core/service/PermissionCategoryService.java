/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.dao.PermissionCategoryDAOBeanLocal;
import com.webapp.commons.core.entities.PermissionCategory;
import javax.ejb.EJB;
import javax.ejb.Stateless;



/**
 *
 * @author persistence
 */
@Stateless
public class PermissionCategoryService extends GenericServiceBean<PermissionCategory, String> 
        implements PermissionCategoryServiceBeanRemote {
    
    @EJB
    private PermissionCategoryDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<PermissionCategory, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(PermissionCategory e) {
        return e.getCode();
    }
    
}
