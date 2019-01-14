/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.dao;

import com.webapp.commons.core.entities.UserSession;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;


/**
 *
 * @author komilo
 */
@Stateless
public class UserSessionDAOBean extends GenericDAOBean<UserSession, Long> 
        implements UserSessionDAOBeanLocal {
    
    public UserSessionDAOBean() {
        super(UserSession.class);
    }

    @Override
    public UserSession getLastByUserId(Long userId) {
        String jpql = "SELECT e FROM UserSession e "
                + "WHERE e.endDate IS NULL "
                + "AND e.user.id = :userId "
                + "ORDER BY e.startDate DESC";
        Query query = this.em.createQuery(jpql)
                .setParameter("userId", userId)
                .setFirstResult(0)
                .setMaxResults(1);
        List<UserSession> sessions = query.getResultList();
        return sessions.isEmpty() ? null : sessions.get(0);
////        try {
////            return (UserSession) query.getSingleResult();
////        } catch (Exception e) {
////            return null;
////        }
    }

    @Override
    public boolean isTokenValid(String token) {
        String jpql = "SELECT e FROM UserSession e "
                + "WHERE e.token = :token AND e.endDate IS NULL";
        Query query = this.em.createQuery(jpql);
        query.setParameter("token", token);
        try {
            Object object = query.getSingleResult();
            return object != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getToken(String username) {
        String jpql = "SELECT e.token FROM UserSession e "
                + "WHERE e.user.username = :username";
        Query query = this.em.createQuery(jpql);
        query.setParameter("username", username);
        try {
            return (String) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
