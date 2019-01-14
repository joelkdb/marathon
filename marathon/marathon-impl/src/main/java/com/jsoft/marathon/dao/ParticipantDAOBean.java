/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.Participant;
import com.webapp.commons.core.dao.GenericDAOBean;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class ParticipantDAOBean extends GenericDAOBean<Participant, Long>
        implements ParticipantDAOBeanLocal {

    public ParticipantDAOBean() {
        super(Participant.class);
    }
}
