/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.dao.PermissionDAOBeanLocal;
import com.webapp.commons.core.entities.Permission;
import com.webapp.commons.core.entities.PermissionCategory;
import com.webapp.commons.core.utils.EqualsFilterParams;
import com.webapp.commons.core.utils.SortDirection;
import com.webapp.commons.core.utils.SortParams;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author persistence
 */
@Stateless
public class PermissionServiceBean extends GenericServiceBean<Permission, String>
        implements PermissionServiceBeanRemote {

    @EJB
    private PermissionDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<Permission, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Permission e) {
        return e.getCode();
    }

    @Override
    public List<Permission> getAll(PermissionCategory category) {
        return this.getAll(category, SortParams.from("label", SortDirection.ASCENDING));
    }

    @Override
    public List<Permission> getAll(PermissionCategory category, SortParams sortParams) {
//        return this.dao.getAll(category.getCode(), sortParams);
        return this.dao.getAll(sortParams, EqualsFilterParams.from("category.code", category.getCode()));
    }

}
