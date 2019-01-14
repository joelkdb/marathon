/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.dao;

import com.webapp.commons.core.entities.User;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;


/**
 *
 * @author komilo
 */
@Stateless
public class UserDAOBean extends GenericDAOBean<User, Long> implements UserDAOBeanLocal {

    public UserDAOBean() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        try {
            String jpql = "SELECT u FROM User u WHERE u.username = :username";
            Query query = this.em.createQuery(jpql);
            query.setParameter("username", username);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void addOne(User e) {
//        List<Role> roles = new LinkedList<>();
//        for (Role role : e.getRoles()) {
//            roles.add(this.em.merge(role));
//        }
//        e.setRoles(roles);
        e.getRoles().size();
        super.addOne(e);
    }

    @Override
    public boolean exists(Long id, String userName) {
        try {
            String jpql = "SELECT COUNT(u) FROM User u WHERE UPPER(u.username) LIKE UPPER(:username) " + (id == null ? " " : "AND u.id!=:id");
            Query query = this.em.createQuery(jpql);
            query.setParameter("username", userName);
            if (id != null) {
                query.setParameter("id", id);
            }
            Long l = ((Long) query.getSingleResult());
            return l >= 1L;
        } catch (NoResultException e) {
            return false;
        }
    }
}
