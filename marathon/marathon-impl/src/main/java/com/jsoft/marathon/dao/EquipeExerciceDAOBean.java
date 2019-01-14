/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.EquipeExercice;
import com.jsoft.marathon.entities.EquipeExercicePK;
import com.jsoft.marathon.entities.Test;
import com.webapp.commons.core.dao.GenericDAOBean;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class EquipeExerciceDAOBean extends GenericDAOBean<EquipeExercice, EquipeExercicePK>
        implements EquipeExerciceDAOBeanLocal {

    public EquipeExerciceDAOBean() {
        super(EquipeExercice.class);
    }

    @Override
    public List<EquipeExercice> findByEquipeTest(Equipe equipe, Test test) {
        String query
                = "SELECT ee\n"
                + "FROM EquipeExercice ee\n"
                + "WHERE ee.equipe = :p1\n"
                + "AND ee.exercice.test = :p2";
        List<EquipeExercice> list = (List<EquipeExercice>) this.em
                .createQuery(query)
                .setParameter("p1", equipe)
                .setParameter("p2", test)
                .getResultList();
        return list;
    }
}
