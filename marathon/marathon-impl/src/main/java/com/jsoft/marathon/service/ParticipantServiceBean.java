/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.dao.ParticipantDAOBeanLocal;
import com.jsoft.marathon.entities.Participant;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.service.GenericServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class ParticipantServiceBean extends GenericServiceBean<Participant, Long>
        implements ParticipantServiceBeanRemote {

    @EJB
    private ParticipantDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<Participant, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(Participant e) {
        return e.getCode();
    }

}