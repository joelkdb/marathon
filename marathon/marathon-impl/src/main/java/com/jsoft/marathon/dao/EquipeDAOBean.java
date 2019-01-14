/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.EquipeExercice;
import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.entities.Test;
import com.webapp.commons.core.dao.GenericDAOBean;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author joelkdb
 */
@Stateless
public class EquipeDAOBean extends GenericDAOBean<Equipe, String>
        implements EquipeDAOBeanLocal {

    public EquipeDAOBean() {
        super(Equipe.class);
    }

    @Override
    public String getCategoryOfCurrentTeam(Long id) {
        String jpql = "SELECT c.libelle FROM CategorieEquipe c, Equipe e, User u "
                + "WHERE e.categorieEquipe.code = c.code AND e.user.id = u.id AND u.id = :id";
        Query query = this.em.createQuery(jpql);
        query.setParameter("id", id);
        String category = (String) query.getSingleResult();
        if (category != null) {
            return category;
        } else {
            return "";
        }
    }

    @Override
    public List<Equipe> findParticipants(Marathon marathon, CategorieEquipe categorieEquipe) throws Exception {
        String query
                = "SELECT me.equipe e\n"
                + "FROM MarathonEquipe me\n"
                + "WHERE me.equipe.categorieEquipe = :p1\n"
                + "AND me.marathon = :p2\n"
                + "AND me.authorized = 1";
        List<Equipe> result = (List<Equipe>) this.em
                .createQuery(query)
                .setParameter("p1", categorieEquipe)
                .setParameter("p2", marathon)
                .getResultList();
        return result;
    }

    @Override
    public List<EquipeExercice> calculateEquipeScore(Equipe equipe, Test test) throws Exception {
        String query
                = "SELECT ee\n"
                + "FROM EquipeExercice ee\n"
                + "WHERE ee.equipe = :p1\n"
                + "AND ee.exercice.test = :p2";
        List<EquipeExercice> eeList = (List<EquipeExercice>) this.em
                .createQuery(query)
                .setParameter("p1", equipe)
                .setParameter("p2", test)
                .getResultList();
        return eeList;
    }
}
