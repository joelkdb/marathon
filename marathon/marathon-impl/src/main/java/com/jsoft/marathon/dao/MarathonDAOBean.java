/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.Marathon;
import com.webapp.commons.core.dao.GenericDAOBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author joelkdb
 */
@Stateless
public class MarathonDAOBean extends GenericDAOBean<Marathon, String>
        implements MarathonDAOBeanLocal {

    public MarathonDAOBean() {
        super(Marathon.class);
    }

    @Override
    public Marathon findCurrentMarathon() {
        String jpql
                = "SELECT m\n"
                + "FROM   Marathon m\n"
                + "WHERE    (CURRENT_TIMESTAMP BETWEEN m.dateDebut AND m.dateFin)";
        Query query = this.em.createQuery(jpql);
        Marathon actual = (Marathon) query.getSingleResult();
        if (actual != null) {
            return actual;
        } else {
            return null;
        }
    }
}
