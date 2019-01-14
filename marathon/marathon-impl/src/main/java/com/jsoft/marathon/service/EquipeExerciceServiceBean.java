/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.compiler.CompilateurImpl;
import com.jsoft.marathon.dao.EquipeExerciceDAOBeanLocal;
import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.EquipeExercice;
import com.jsoft.marathon.entities.EquipeExercicePK;
import com.jsoft.marathon.entities.Exercice;
import com.jsoft.marathon.entities.MarathonEquipe;
import com.jsoft.marathon.entities.Test;
import com.jsoft.marathon.exception.InsufficientJokersNumberException;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.service.GenericServiceBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class EquipeExerciceServiceBean extends GenericServiceBean<EquipeExercice, EquipeExercicePK>
        implements EquipeExerciceServiceBeanRemote {

    @EJB
    private EquipeExerciceDAOBeanLocal dao;
    @EJB
    private ExerciceServiceBeanRemote exerciceService;
    @EJB
    private MarathonEquipeServiceBeanRemote marathonEquipeService;
    @EJB
    private EquipeServiceBeanRemote equipeService;

    @Override
    protected GenericDAOBeanLocal<EquipeExercice, EquipeExercicePK> getDAO() {
        return this.dao;
    }

    @Override
    public EquipeExercicePK getId(EquipeExercice e) {
        return e.getEquipeExercicePK();
    }

    @Override
    public Boolean isSolutionCorrect(EquipeExercice ee) {
        if (ee.getSolutionEnvoye() == null || ee.getSolutionEnvoye().isEmpty()) {
            return false;
        } else if (CompilateurImpl.result.equalsIgnoreCase(ee.getSolutionEnvoye())) {
            return ee.getSolutionEnvoye().equalsIgnoreCase(ee.getExercice().getSolution());
        }
        return false;
    }

//    @Override
//    public String compilerService(String chemin, String fileName, String langage) {
//        return Compilateur.getCompilerResult(chemin, fileName, langage);
//    }
    @Override
    public MarathonEquipe validateExercice(EquipeExercice equipeExercice, MarathonEquipe marathonEquipe)
            throws Exception {
        equipeExercice = this.dao.updateOne(equipeExercice);//mise à jour de l'entité equipeExercice
        if (isSolutionCorrect(equipeExercice)) { // On leur ajoute des jokers s'il le faut
            equipeExercice.setResolu(true);
            equipeExercice = this.dao.updateOne(equipeExercice);
            marathonEquipe.setNombreJockers(marathonEquipe.getNombreJockers()
                    + exerciceService.findNbJokersToWin(equipeExercice.getExercice()));
            marathonEquipe = marathonEquipeService.updateOne(marathonEquipe);
        }
        //}
        return marathonEquipe;
    }

    @Override
    public MarathonEquipe activateJoker(EquipeExercice equipeExercice, MarathonEquipe marathonEquipe)
            throws Exception {
        equipeExercice = this.dao.updateOne(equipeExercice);
        Integer remaining = marathonEquipe.getNombreJockers() - exerciceService.findNbJokersToLoose(equipeExercice.getExercice());
        if (remaining < 0) {
            throw new InsufficientJokersNumberException();
        }
        marathonEquipe.setNombreJockers(remaining);
        return marathonEquipeService.updateOne(marathonEquipe);
    }

    @Override
    public Equipe createEquipeExercicesIfNot(Equipe equipe, Test test) throws Exception {
        List<EquipeExercice> list = findByEquipeTest(equipe, test);
        if (list.isEmpty() || list == null) {
            // Les lignes n'ont pas été créées auparavant donc on les crée
            for (Exercice exercice : test.getExercices()) {
                equipe.getEquipeExercices().add(create(equipe, exercice));
            }
            equipe = this.equipeService.updateOne(equipe);
        }
        return equipe;
    }

    @Override
    public List<EquipeExercice> findByEquipeTest(Equipe equipe, Test test) {
        return this.dao.findByEquipeTest(equipe, test);
    }

    @Override
    public EquipeExercice create(Equipe equipe, Exercice exercice) throws Exception {
        EquipeExercice ee = new EquipeExercice();
        EquipeExercicePK equipeExercicePK = new EquipeExercicePK();
        equipeExercicePK.setCodeEquipe(equipe.getCode());
        equipeExercicePK.setCodeExercice(exercice.getCode());
        ee.setEquipeExercicePK(equipeExercicePK);
        ee.setEquipe(equipe);
        ee.setExercice(exercice);
        this.dao.addOne(ee);
        return ee;
    }

}
