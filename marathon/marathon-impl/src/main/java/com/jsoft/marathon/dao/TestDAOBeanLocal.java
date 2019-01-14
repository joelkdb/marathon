/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.entities.Test;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author joelkdb
 */
@Local
public interface TestDAOBeanLocal extends GenericDAOBeanLocal<Test, String> {

    /**
     * Permet de rechercher tous les tests actuels (en cours) d'une catégorie
     * d'équipe donnée.
     *
     * @param categorieEquipe
     * @return une liste de <Test>
     * @throws Exception
     */
    public List<Test> findCurrentTests(CategorieEquipe categorieEquipe) throws Exception;

    /**
     * Permet de rechercher tous les tests précédents d'une catégorie d'équipe
     * donnée.
     *
     * @param categorieEquipe
     * @return une liste de <Test>
     * @throws Exception
     */
    public List<Test> findLastTests(CategorieEquipe categorieEquipe) throws Exception;

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
