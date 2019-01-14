/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.dao;

import com.webapp.commons.core.entities.LogCategory;
import javax.ejb.Stateless;
import javax.persistence.Query;


/**
 *
 * @author Ramses
 */
@Stateless
public class LogCategoryDAOBean extends GenericDAOBean<LogCategory, String> implements LogCategoryDAOBeanLocal {

    public LogCategoryDAOBean() {
        super(LogCategory.class);
    }

    @Override
    public LogCategory findByLabelCategory(String label) {
        try {
            String jpql = "SELECT c FROM LogCategory c WHERE c.label = :label";
            Query query = this.em.createQuery(jpql)
                    .setParameter("label", label);
            return (LogCategory) query.getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }
}
