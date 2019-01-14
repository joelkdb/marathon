/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.dao.MarathonEquipeDAOBeanLocal;
import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.entities.MarathonEquipe;
import com.jsoft.marathon.entities.MarathonEquipePK;
import com.jsoft.marathon.exception.DisqualifiedTeamException;
import com.jsoft.marathon.utils.MarathonConstants;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.entities.User;
import com.webapp.commons.core.exception.BusinessException;
import com.webapp.commons.core.service.GenericServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class MarathonEquipeServiceBean extends GenericServiceBean<MarathonEquipe, MarathonEquipePK>
        implements MarathonEquipeServiceBeanRemote {

    @EJB
    private MarathonEquipeDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<MarathonEquipe, MarathonEquipePK> getDAO() {
        return this.dao;
    }

    @Override
    public MarathonEquipePK getId(MarathonEquipe e) {
        return e.getMarathonEquipePK();
    }

    @Override
    public void deleteOne(MarathonEquipe me) {
        try {
            super.deleteOne(me);
            this.logService.info("Suppression d'une occurence de MarathonEquipe.",
                    MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }

    }

    @Override
    public void addOne(MarathonEquipe me) {
        try {
            me.setNombreJockers(0);
            super.addOne(me);
            this.logService.info("Création d'une nouvelle occurence de MarathonEquipe.",
                    MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }

    }

    @Override
    public void update(MarathonEquipe me) {
        try {
            this.dao.updateOne(me);
            this.logService.info("Modification d'une occurence de MarathonEquipe.",
                    MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public MarathonEquipe findActualMarathonEquipe(User user, Marathon marathon) throws Exception {
        try {
            return this.dao.findActualMarathonEquipe(user, marathon);
        } catch (DisqualifiedTeamException e) {
            throw new DisqualifiedTeamException();
        }
    }

}
