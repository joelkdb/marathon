/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import javax.ejb.Remote;
import com.webapp.commons.core.entities.User;
import com.webapp.commons.core.entities.UserSession;

/**
 *
 * @author komilo
 */
@Remote
public interface UserSessionServiceBeanRemote extends GenericServiceBeanRemote<UserSession, Long> {
    
    void initSession(User user);
    
    void initSession(String username);
    
    void closeSession(User user);
    
    boolean isTokenValid(String token);
    
    String getToken(String username);
}
