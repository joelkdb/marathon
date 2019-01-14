/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.dao.MarathonDAOBeanLocal;
import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.entities.Test;
import com.jsoft.marathon.utils.MarathonConstants;
import com.jsoft.marathon.utils.MarathonUtils;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.exception.BusinessException;
import com.webapp.commons.core.service.GenericServiceBean;
import com.webapp.commons.core.service.SequenceServiceBeanRemote;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.CDI;

/**
 *
 * @author joelkdb
 */
@Stateless
public class MarathonServiceBean extends GenericServiceBean<Marathon, String>
        implements MarathonServiceBeanRemote {

    @EJB
    private MarathonDAOBeanLocal dao;

    @EJB
    private SequenceServiceBeanRemote sequenceService;

    @EJB
    private TestServiceBeanRemote testService;

    @Override
    protected GenericDAOBeanLocal<Marathon, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Marathon e) {
        return e.getCode();
    }

    @Override
    public void deleteOne(Marathon e) {
        try {
            super.deleteOne(e);
            this.logService.info("Suppression d'un marathon.", MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }

    }

    @Override
    public void addOne(Marathon e) {
        try {
            e.setCode(this.generateCode());
            super.addOne(e);
            this.logService.info("Enregistrement d'un nouveau marathon.", MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }

    }

    @Override
    public void update(Marathon m) {
        try {
            this.dao.updateOne(m);
            this.logService.info("Modification d'un marathon.", MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public String generateCode() {
        Long count = this.sequenceService.getNextValue(Marathon.SEQUENCE);
        return MarathonUtils.generateProjectCode("M/", count.intValue());
    }

    @Override
    public Marathon findCurrentMarathon() {
        return this.dao.findCurrentMarathon();
    }

    @Override
    public Long calculateTotalPoints(Marathon marathon, CategorieEquipe categorieEquipe) throws Exception {
        List<Test> testList = this.testService.findAllLastTests(marathon, categorieEquipe);
        Long total = 0L;
        for (Test test : testList) {
            total += this.testService.calculateTotalPoints(test);
        }
        return total;
    }

}
