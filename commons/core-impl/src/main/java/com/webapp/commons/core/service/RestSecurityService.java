/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;


import javax.ejb.EJB;
import javax.ejb.Stateless;
/**
 *
 * @author komilo
 */
@Stateless
public class RestSecurityService implements RestSecurityServiceRemote {
    
    @EJB
    private UserServiceBeanRemote userService;
    
    @EJB
    private UserSessionServiceBeanRemote userSessionService;

    @Override
    public String login(String username, String password) {
        if (this.userService.login(username, password)) {
            this.userSessionService.getToken(username);
        }
        return null;
    }

    @Override
    public boolean isAuthTokenValid(String authToken) {
        return this.userSessionService.isTokenValid(authToken);
    }

    
    @Override
    public void logout(String authToken) {
        this.userService.logout(authToken);
    }
    
}
