/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.EquipeExercice;
import com.jsoft.marathon.entities.EquipeExercicePK;
import com.jsoft.marathon.entities.Exercice;
import com.jsoft.marathon.entities.MarathonEquipe;
import com.jsoft.marathon.entities.Test;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author joelkdb
 */
@Remote
public interface EquipeExerciceServiceBeanRemote extends
        GenericServiceBeanRemote<EquipeExercice, EquipeExercicePK> {

    /**
     * Permet de vérifier la validité de la solution proposée par une équipe
     *
     * @param ee
     * @return
     * @throws java.lang.Exception
     */
    public Boolean isSolutionCorrect(EquipeExercice ee) throws Exception;
    
    /**
     * Permet de compiler et exécuté un fichier
     * @param chemin
     * @param fileName
     * @param langage
     * @return le resultat de la compilation
     */
    //public String compilerService(String chemin, String fileName, String langage);

    /**
     * Permet de valider un exercie (cohérence et augmenter le nombre de jokers
     * de l'équipe)
     *
     * @param equipeExercice
     * @param marathonEquipe
     * @return
     * @throws java.lang.Exception
     */
    public MarathonEquipe validateExercice(EquipeExercice equipeExercice, MarathonEquipe marathonEquipe) 
            throws Exception;

    /**
     * Permet d'activer un joker (activer et diminuer le nombre de jokers de
     * l'équipe)
     *
     * @param equipeExercice
     * @param marathonEquipe
     * @return
     * @throws java.lang.Exception
     */
    public MarathonEquipe activateJoker(EquipeExercice equipeExercice, MarathonEquipe marathonEquipe) throws Exception;

    /**
     * Permet de créer des lignes de EquipeExercice si elles ne sont pas créées
     * auparavant
     *
     * @param equipe
     * @param test
     * @return
     * @throws java.lang.Exception
     */
    public Equipe createEquipeExercicesIfNot(Equipe equipe, Test test) throws Exception;

    /**
     * Permet de trouver les exercices auxquels doit être confrontée l'équipe
     *
     * @param equipe
     * @param test
     * @return
     */
    public List<EquipeExercice> findByEquipeTest(Equipe equipe, Test test);

    /**
     * @param equipe
     * @param exercice
     * @return
     * @throws Exception
     */
    public EquipeExercice create(Equipe equipe, Exercice exercice) throws Exception;
}
