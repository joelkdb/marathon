/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.entities.Test;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author joelkdb
 */
@Remote
public interface TestServiceBeanRemote extends GenericServiceBeanRemote<Test, String> {

    void update(Test t);

    /**
     * Méthode renvoyant le code généré pour un test
     *
     * @return
     */
    String generateCode();

    /**
     * Permet de trouver le test en cours pour une catégorie donnée
     *
     * @param categorieEquipe
     * @return
     * @throws java.lang.Exception
     */
    public Test findCurrentTest(CategorieEquipe categorieEquipe) throws Exception;

    /**
     * Permet de trouver les tests en cours pour toutes les catégories
     *
     * @return
     * @throws java.lang.Exception
     */
    public List<Test> findCurrentTests() throws Exception;

    /**
     * Permet de trouver le dernier test passé pour une catégorie donnée
     *
     * @param categorieEquipe
     * @return
     * @throws java.lang.Exception
     */
    public Test findLastTest(CategorieEquipe categorieEquipe) throws Exception;

    /**
     * Permet de trouver les derniers tests passés pour toutes les catégories
     *
     * @return
     * @throws java.lang.Exception
     */
    public List<Test> findLastTests() throws Exception;

    /**
     * Permet de calculer le temps restant pour le test
     *
     * @param test
     * @return
     * @throws java.lang.Exception
     */
    public Date calculateRemainingTime(Test test) throws Exception;

    /**
     * Permet de calculer le nombre total de points pondéré au test
     *
     * @param test
     * @return
     * @throws java.lang.Exception
     */
    public Long calculateTotalPoints(Test test) throws Exception;

    /**
     * Permet de récupérer les tests qui sont déjà passé pour le marathon
     *
     * @param marathon
     * @param categorieEquipe
     * @return
     * @throws Exception
     */
    public List<Test> findAllLastTests(Marathon marathon, CategorieEquipe categorieEquipe) throws Exception;
}
