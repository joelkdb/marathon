/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.dao;

import com.webapp.commons.core.entities.Role;
import com.webapp.commons.core.utils.SortParams;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author komilo
 */
@Local
public interface RoleDAOBeanLocal extends GenericDAOBeanLocal<Role, Long> {

    public List<Role> getAll(Long UserId, SortParams sortParams);
}
