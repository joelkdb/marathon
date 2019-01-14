/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.dao.RoleDAOBeanLocal;
import com.webapp.commons.core.entities.Role;
import com.webapp.commons.core.entities.User;
import com.webapp.commons.core.utils.SortDirection;
import com.webapp.commons.core.utils.SortParams;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author persistence
 */
@Stateless
public class RoleServiceBean extends GenericServiceBean<Role, Long> 
        implements RoleServiceBeanRemote {
    
    @EJB
    private RoleDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<Role, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Role e) {
        return e.getId();
    }

    @Override
    public List<Role> getAll(User user) {
        if (user == null) {
            return Collections.EMPTY_LIST;
        }
        return this.dao.getAll(user.getId(), SortParams.from("label", SortDirection.ASCENDING));
    }
  
}
