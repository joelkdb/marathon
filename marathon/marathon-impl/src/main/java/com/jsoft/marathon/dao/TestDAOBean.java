/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.entities.Test;
import com.webapp.commons.core.dao.GenericDAOBean;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author joelkdb
 */
@Stateless
public class TestDAOBean extends GenericDAOBean<Test, String>
        implements TestDAOBeanLocal {

    public TestDAOBean() {
        super(Test.class);
    }

    @Override
    public List<Test> findCurrentTests(CategorieEquipe categorieEquipe) throws Exception {
        String jpql
                = "SELECT t\n"
                + "FROM   Test t\n"
                + "WHERE  t.dateTest = CURRENT_DATE\n"
                + "AND    (CURRENT_TIME BETWEEN t.heureDebut AND t.heureFin) ";
        if (categorieEquipe != null) {
            jpql += "AND t.categorieEquipe = :p";
        }
        Query query = this.em.createQuery(jpql);
        query = (categorieEquipe != null) ? query.setParameter("p", categorieEquipe) : query;
        List<Test> currents = (List<Test>) query.getResultList();
        if (currents == null || currents.isEmpty()) {
            throw new NoResultException();
        }
        return currents;
    }

    @Override
    public List<Test> findLastTests(CategorieEquipe categorieEquipe) throws Exception {
        String jpql
                = "SELECT t1\n"
                + "FROM   Test t1\n"
                + "WHERE  t1.dateTest <= CURRENT_DATE\n";
        if (categorieEquipe != null) {
            jpql += "AND t1.categorieEquipe = :p1\n";
        }
        jpql += "AND t1.dateTest = (SELECT MAX(t2.dateTest) FROM Test t2 WHERE t2.dateTest <= CURRENT_DATE AND t1.categorieEquipe = t2.categorieEquipe)"; // Qui soit le plus grand parmis eux!
        Query query = this.em.createQuery(jpql);
        query = (categorieEquipe != null) ? query.setParameter("p1", categorieEquipe) : query;
        List<Test> lasts = (List<Test>) query.getResultList();
        if (lasts == null || lasts.isEmpty()) {
            throw new NoResultException();
        }
        return lasts;
    }

    @Override
    public List<Test> findAllLastTests(Marathon marathon, CategorieEquipe categorieEquipe) throws Exception {
        String query
                = "SELECT t\n"
                + "FROM   Test t\n"
                + "WHERE  t.marathon = :p1\n"
                + "AND    t.categorieEquipe = :p2\n"
                + "AND    t.dateTest <= CURRENT_DATE\n"
                + "AND    t.heureFin <= CURRENT_TIME";
        List<Test> list = (List<Test>) this.em.createQuery(query).setParameter("p1", marathon)
                .setParameter("p2", categorieEquipe).getResultList();
        return list;
    }
}
