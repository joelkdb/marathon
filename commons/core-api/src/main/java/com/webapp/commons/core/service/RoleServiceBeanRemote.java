/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import java.util.List;
import javax.ejb.Remote;
import com.webapp.commons.core.entities.Role;
import com.webapp.commons.core.entities.User;

/**
 *
 * @author persistence
 */
@Remote
public interface RoleServiceBeanRemote extends GenericServiceBeanRemote<Role, Long> {
    
    /**
     * Renvoie les roles associés à un utilisateur.
     * @param user L'utilisateur.
     * @return La liste des rôles.
     */
    List<Role> getAll(User user);
}
