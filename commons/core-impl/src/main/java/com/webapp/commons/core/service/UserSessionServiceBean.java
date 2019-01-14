/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.dao.UserSessionDAOBeanLocal;
import com.webapp.commons.core.entities.User;
import com.webapp.commons.core.entities.UserSession;
import com.webapp.commons.core.exception.BusinessException;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author komilo
 */
@Stateless
public class UserSessionServiceBean extends GenericServiceBean<UserSession, Long>
        implements UserSessionServiceBeanRemote {

    @EJB
    private UserSessionDAOBeanLocal dao;

    @EJB
    private UserServiceBeanRemote userService;

    @Override
    protected GenericDAOBeanLocal<UserSession, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(UserSession e) {
        return e.getId();
    }

    @Override
    public void initSession(User user) {
        this.initSession(user.getUsername());
    }

    @Override
    public void initSession(String username) {
        User user = this.userService.findByUsername(username);
        if (user == null) {
            throw new BusinessException("L'utilisateur '" + username + "' n'existe pas.");
        }
        UserSession session = new UserSession();
        session.setStartDate(new Date());
        session.setHost(this.userService.getClientIPAdress());
        session.setUser(user);
        session.setToken(UUID.randomUUID().toString());
        this.dao.addOne(session);
    }

    @Override
    public void closeSession(User user) {
        UserSession session = this.dao.getLastByUserId(user.getId());
        if (session == null) {
            this.logger.log(Level.WARNING, "Aucune session n'existe pour l'utilisateur ''{0}''.", 
                    user.getUsername());
        } else {
            session.setEndDate(new Date());
            this.dao.updateOne(session);
        }
    }

    @Override
    public boolean isTokenValid(String token) {
        return this.dao.isTokenValid(token);
    }

    @Override
    public String getToken(String username) {
        return this.dao.getToken(username);
    }
}
