/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.dao.EquipeDAOBeanLocal;
import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.EquipeExercice;
import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.entities.Test;
import com.jsoft.marathon.utils.Maps;
import com.jsoft.marathon.utils.MarathonConstants;
import com.jsoft.marathon.utils.MarathonUtils;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.exception.BusinessException;
import com.webapp.commons.core.service.GenericServiceBean;
import com.webapp.commons.core.service.SequenceServiceBeanRemote;
import com.webapp.commons.core.service.UserServiceBeanRemote;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class EquipeServiceBean extends GenericServiceBean<Equipe, String>
        implements EquipeServiceBeanRemote {

    @EJB
    private EquipeDAOBeanLocal dao;

    @EJB
    private UserServiceBeanRemote userService;

    @EJB
    private SequenceServiceBeanRemote sequenceService;

    @EJB
    private EquipeExerciceServiceBeanRemote ees;

    @EJB
    private TestServiceBeanRemote testService;

    @Override
    protected GenericDAOBeanLocal<Equipe, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Equipe e) {
        return e.getCode();
    }

    @Override
    public void deleteOne(Equipe e) {
        try {
            super.deleteOne(e);
            this.logService.info("Suppression d'une equipe.", MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }

    }

    @Override
    public void addOne(Equipe e) {
        try {
            e.setCode(this.generateCode());
            super.addOne(e);
            this.logService.info("Enregistrement d'une nouvelle équipe.", MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }

    }

    @Override
    public void update(Equipe e) {
        try {
            this.dao.updateOne(e);
            this.logService.info("Modification d'une équipe.", MarathonConstants.LOG_PARAMETRAGE);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public String generateCode() {
        Long count = this.sequenceService.getNextValue(Equipe.SEQUENCE);
        return MarathonUtils.generateProjectCode("E/", count.intValue());
    }

    @Override
    public String getCategoryOfCurrentTeam() {
        if (this.userService.getCurrent().getId() == 1) {
            return "Junior/Senior";
        } else {
            return this.dao.getCategoryOfCurrentTeam(this.userService.getCurrent().getId());
        }
    }

    @Override
    public List<Equipe> findParticipants(Test test) throws Exception {
        // C'est l'ensemble des participants du marathon en cours pour la catégorie impliquée par le test
        return this.findParticipants(test.getMarathon(), test.getCategorieEquipe());
    }

    @Override
    public List<Equipe> findParticipants(Marathon marathon, CategorieEquipe categorieEquipe) throws Exception {
        return this.dao.findParticipants(marathon, categorieEquipe);
    }

    @Override
    public Map<Equipe, Long> findEquipesClassement(Test test) throws Exception {
        List<Equipe> equipeList = this.findParticipants(test);
        Map<Equipe, Long> map = new HashMap<>();
        for (Equipe equipe : equipeList) {
            map.put(equipe, this.calculateEquipeScore(equipe, test));
        }
        return Maps.sortByValueDesc(map);
    }

    @Override
    public Map<Equipe, Long> findEquipesClassement(Marathon marathon, CategorieEquipe categorieEquipe)
            throws Exception {
        List<Equipe> equipeList = this.findParticipants(marathon, categorieEquipe);
        Map<Equipe, Long> map = new HashMap<>();
        for (Equipe equipe : equipeList) {
            map.put(equipe, this.calculateEquipeScore(equipe, marathon));
        }
        return Maps.sortByValueDesc(map);
    }

    @Override
    public Long calculateEquipeScore(Equipe equipe, Test test) throws Exception {
        List<EquipeExercice> eeList = this.dao.calculateEquipeScore(equipe, test);
        // Le score est la somme des points cummulés lors de la résolution des exercices du test
        Long score = 0L;
        //EquipeExerciceServiceBean ees = CDI.current().select(EquipeExerciceServiceBean.class).get();
        for (EquipeExercice ee : eeList) {
//            if (this.ees.isSolutionCorrect(ee)) {
//                score += ee.getExercice().getPoint();
//            }
            if (ee.isResolu()) {
                score += ee.getExercice().getPoint();
            }
        }
        return score;
    }

    @Override
    public Long calculateEquipeScore(Equipe equipe, Marathon marathon) throws Exception {
        //TestServiceBean testServiceBean = CDI.current().select(TestServiceBean.class).get();
        List<Test> testList = this.testService.findAllLastTests(marathon, equipe.getCategorieEquipe());
        // Le score est la somme des points cummulés lors des tests (déjà passés) du marathon
        Long score = 0L;
        for (Test test : testList) {
            score += calculateEquipeScore(equipe, test);
        }
        return score;
    }

    @Override
    public Double calculateEquipeScorePercent(Equipe equipe, Test test) throws Exception {
        // Le pourcentage du score est le rapport entre le score de l'équipe et le nombre de points du test
        //TestServiceBean testServiceBean = CDI.current().select(TestServiceBean.class).get();
        return this.calculateEquipeScore(equipe, test).doubleValue() / this.testService.calculateTotalPoints(test)
                .doubleValue();
    }

    public UserServiceBeanRemote getUserService() {
        return userService;
    }

    public SequenceServiceBeanRemote getSequenceService() {
        return sequenceService;
    }

    public EquipeExerciceServiceBeanRemote getEes() {
        return ees;
    }

    public TestServiceBeanRemote getTestService() {
        return testService;
    }

}
