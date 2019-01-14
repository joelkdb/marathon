/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.ParticipationMarathon;
import com.jsoft.marathon.entities.ParticipationMarathonPK;
import com.webapp.commons.core.dao.GenericDAOBean;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class ParticipationMarathonDAOBean extends GenericDAOBean<ParticipationMarathon, ParticipationMarathonPK>
        implements ParticipationMarathonDAOBeanLocal {

    public ParticipationMarathonDAOBean() {
        super(ParticipationMarathon.class);
    }
}
