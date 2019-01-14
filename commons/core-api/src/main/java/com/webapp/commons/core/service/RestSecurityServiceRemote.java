/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import java.io.Serializable;
import javax.ejb.Remote;

/**
 *
 * @author komilo
 */
@Remote
public interface RestSecurityServiceRemote extends Serializable {
    
    /**
     * Authentifie et renvoie un token.
     * 
     * @param username
     * @param password
     * @return Un token.
     */
    String login(String username, String password);
    
    boolean isAuthTokenValid(String authToken);
    
    void logout(String authToken);
}
