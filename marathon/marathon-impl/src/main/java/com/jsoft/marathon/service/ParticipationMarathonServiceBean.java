/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.dao.ParticipationMarathonDAOBeanLocal;
import com.jsoft.marathon.entities.ParticipationMarathon;
import com.jsoft.marathon.entities.ParticipationMarathonPK;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.service.GenericServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class ParticipationMarathonServiceBean extends GenericServiceBean<ParticipationMarathon, ParticipationMarathonPK>
        implements ParticipationMarathonServiceBeanRemote {

    @EJB
    private ParticipationMarathonDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<ParticipationMarathon, ParticipationMarathonPK> getDAO() {
        return this.dao;
    }

    @Override
    public ParticipationMarathonPK getId(ParticipationMarathon e) {
        return e.getParticipationMarathonPK();
    }

}