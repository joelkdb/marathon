/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.dao.ExerciceDAOBeanLocal;
import com.jsoft.marathon.entities.Exercice;
import com.jsoft.marathon.utils.MarathonConstants;
import com.jsoft.marathon.utils.MarathonUtils;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.exception.BusinessException;
import com.webapp.commons.core.service.GenericServiceBean;
import com.webapp.commons.core.service.SequenceServiceBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class ExerciceServiceBean extends GenericServiceBean<Exercice, String>
        implements ExerciceServiceBeanRemote {

    @EJB
    private ExerciceDAOBeanLocal dao;

    @EJB
    private SequenceServiceBeanRemote sequenceService;

    @EJB
    private ParametreServiceBeanRemote paramService;

    @Override
    protected GenericDAOBeanLocal<Exercice, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Exercice e) {
        return e.getCode();
    }

    @Override
    public void deleteOne(Exercice e) {
        try {
            super.deleteOne(e);
            this.logService.info("Suppression d'un exercice.", MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }

    }

    @Override
    public void addOne(Exercice e) {
        try {
            e.setCode(this.generateCode());
            super.addOne(e);
            this.logService.info("Enregistrement d'un nouveau exercice.", MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }

    }

    @Override
    public void update(Exercice e) {
        try {
            this.dao.updateOne(e);
            this.logService.info("Modification d'un exercice.", MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public String generateCode() {
        Long count = this.sequenceService.getNextValue(Exercice.SEQUENCE);
        return MarathonUtils.generateProjectCode("Ex/", count.intValue());
    }

    @Override
    public Integer findNbJokersToWin(Exercice exercice) throws Exception {
        Integer nbJokers = 0;
        try {
            Integer minJokers = Integer.parseInt(this.paramService.getValeurParametre("MINJOKE"));
            Integer intJokers = Integer.parseInt(this.paramService.getValeurParametre("INTJOKE"));
            Integer diff = exercice.getPoint() - minJokers;
            if (diff >= 0) {
                nbJokers = 1;
                nbJokers += (diff / intJokers);
            }
        } catch (Exception ex) {
            Logger.getLogger(ExerciceServiceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbJokers;
    }

    @Override
    public Integer findNbJokersToLoose(Exercice exercice) throws Exception {
        // Il faut perdre au moins un joker pour voir les indications des exercices
        Integer nbJokers = 1;
        try {
            Integer minJokers = Integer.parseInt(this.paramService.getValeurParametre("MINJOKE"));
            Integer intJokers = Integer.parseInt(this.paramService.getValeurParametre("INTJOKE"));
            Integer diff = exercice.getPoint() - minJokers;
            if (diff > 0) {
                nbJokers += (diff / intJokers);
            }
        } catch (Exception ex) {
            Logger.getLogger(ExerciceServiceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbJokers;
    }

}
