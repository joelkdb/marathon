/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.entities.MarathonEquipe;
import com.jsoft.marathon.entities.MarathonEquipePK;
import com.jsoft.marathon.exception.DisqualifiedTeamException;
import com.webapp.commons.core.dao.GenericDAOBean;
import com.webapp.commons.core.entities.User;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class MarathonEquipeDAOBean extends GenericDAOBean<MarathonEquipe, MarathonEquipePK>
        implements MarathonEquipeDAOBeanLocal {

    public MarathonEquipeDAOBean() {
        super(MarathonEquipe.class);
    }

    @Override
    public MarathonEquipe findActualMarathonEquipe(User user, Marathon marathon) throws Exception {
        String jpql
                = "SELECT me\n"
                + "FROM   MarathonEquipe me\n"
                + "WHERE  me.equipe.user = :p1\n"
                + "AND    me.marathon = :p2";
        MarathonEquipe marathonEquipe = (MarathonEquipe) this.em.createQuery(jpql)
                .setParameter("p1", user)
                .setParameter("p2", marathon)
                .getSingleResult();
        if (!marathonEquipe.isAuthorized()) {
            throw new DisqualifiedTeamException();
        }
        return marathonEquipe;
    }
}
