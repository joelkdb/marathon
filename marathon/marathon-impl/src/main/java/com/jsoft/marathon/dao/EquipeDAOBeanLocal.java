/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.EquipeExercice;
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
public interface EquipeDAOBeanLocal extends GenericDAOBeanLocal<Equipe, String> {

    /**
     * Renvoie la catégorie d'équipe de l'équipe connecté
     *
     * @param id
     * @return
     */
    public String getCategoryOfCurrentTeam(Long id);

    /**
     * Permet de trouver la liste des équipes participantes à un marathon
     * <pre>La catégorie doit être explicite</pre>
     *
     * @param marathon
     * @param categorieEquipe
     * @return
     * @throws java.lang.Exception
     */
    public List<Equipe> findParticipants(Marathon marathon, CategorieEquipe categorieEquipe) throws Exception;

    /**
     * Permet de calculer le score de l'équipe pour un test donné
     * <pre>La catégorie est implicite</pre>
     *
     * @param equipe
     * @param test
     * @return
     * @throws java.lang.Exception
     */
    public List<EquipeExercice> calculateEquipeScore(Equipe equipe, Test test) throws Exception;
}
