/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.dao;

import com.webapp.commons.core.entities.UserSession;
import javax.ejb.Local;


/**
 *
 * @author komilo
 */
@Local
public interface UserSessionDAOBeanLocal extends GenericDAOBeanLocal<UserSession, Long> {
    
    UserSession getLastByUserId(Long userId);
    
    boolean isTokenValid(String token);
    
    String getToken(String username);
}
