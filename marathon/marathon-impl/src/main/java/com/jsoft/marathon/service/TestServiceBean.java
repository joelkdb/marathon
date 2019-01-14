/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.dao.TestDAOBeanLocal;
import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Exercice;
import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.entities.Test;
import com.jsoft.marathon.exception.NoCurrentTestException;
import com.jsoft.marathon.exception.NoCurrentTestsException;
import com.jsoft.marathon.exception.NoLastTestException;
import com.jsoft.marathon.exception.NoLastTestsException;
import com.jsoft.marathon.utils.Dates;
import com.jsoft.marathon.utils.MarathonConstants;
import com.jsoft.marathon.utils.MarathonUtils;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.exception.BusinessException;
import com.webapp.commons.core.service.GenericServiceBean;
import com.webapp.commons.core.service.SequenceServiceBeanRemote;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author joelkdb
 */
@Stateless
public class TestServiceBean extends GenericServiceBean<Test, String>
        implements TestServiceBeanRemote {

    @EJB
    private TestDAOBeanLocal dao;

    @EJB
    private SequenceServiceBeanRemote sequenceService;

    @Override
    protected GenericDAOBeanLocal<Test, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Test e) {
        return e.getCode();
    }

    @Override
    public void deleteOne(Test t) {
        try {
            super.deleteOne(t);
            this.logService.info("Suppression d'un test.", MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }

    }

    @Override
    public void addOne(Test t) {
        try {
            t.setCode(this.generateCode());
            super.addOne(t);
            this.logService.info("Enregistrement d'un nouveau test.", MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }

    }

    @Override
    public void update(Test t) {
        try {
            this.dao.updateOne(t);
            this.logService.info("Modification d'un test.", MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public String generateCode() {
        Long count = this.sequenceService.getNextValue(Test.SEQUENCE);
        return MarathonUtils.generateProjectCode("T/", count.intValue());
    }

    @Override
    public Test findCurrentTest(CategorieEquipe categorieEquipe) throws Exception {
        try {
            return this.dao.findCurrentTests(categorieEquipe).get(0);
        } catch (NoResultException ex) {
            throw new NoCurrentTestException();
        }
    }

    @Override
    public List<Test> findCurrentTests() throws Exception {
        try {
            return this.dao.findCurrentTests(null);
        } catch (NoResultException ex) {
            throw new NoCurrentTestsException();
        }
    }

    @Override
    public Test findLastTest(CategorieEquipe categorieEquipe) throws Exception {
        try {
            return this.dao.findLastTests(categorieEquipe).get(0);
        } catch (NoResultException exception) {
            throw new NoLastTestException();
        }
    }

    @Override
    public List<Test> findLastTests() throws Exception {
        try {
            return this.dao.findLastTests(null);
        } catch (NoResultException exception) {
            throw new NoLastTestsException();
        }
    }

    @Override
    public Date calculateRemainingTime(Test test) throws Exception {
        Date current = Dates.today();
        // Si on est pas à la même date (jour, mois, année) donc il ne reste aucun temps
        if (Dates.comparteTwoDates(test.getDateTest(), current) != 0) {
            return null;
        }
//        // Si on est à la même date (jour, mois, année) mais que le test n'a pas encore commencé donc il ne reste aucun temps
        if (Dates.comparteTimeOfSameDate(current, test.getHeureDebut()) < 0) {
            return null;
        }
//        // On retourne le temps restant (heure, minute, seconde, miliseconde)
        return Dates.getTimeDiffOfDate(new Date(), test.getHeureFin());
    }

    @Override
    public Long calculateTotalPoints(Test test) throws Exception {
        Long total = 0L;
        for (Exercice exercice : test.getExercices()) {
            total += exercice.getPoint();
        }
        return total;
    }

    @Override
    public List<Test> findAllLastTests(Marathon marathon, CategorieEquipe categorieEquipe) throws Exception {
        return this.dao.findAllLastTests(marathon, categorieEquipe);
    }

    @Override
    public Test getOne(String id) {
        Test t = super.getOne(id);
        t.getExercices().size();
        return t; //To change body of generated methods, choose Tools | Templates.
    }
}
