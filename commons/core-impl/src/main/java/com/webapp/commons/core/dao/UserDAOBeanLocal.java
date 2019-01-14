/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.dao;

import com.webapp.commons.core.entities.User;
import javax.ejb.Local;


/**
 *
 * @author komilo
 */
@Local
public interface UserDAOBeanLocal extends GenericDAOBeanLocal<User, Long> {

    User findByUsername(String username);

    public boolean exists(Long id, String userName);
}
