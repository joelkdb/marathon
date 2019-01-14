/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.entities.Test;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author joelkdb
 */
@Remote
public interface EquipeServiceBeanRemote extends GenericServiceBeanRemote<Equipe, String> {
    
    void update(Equipe e);
    
    /**
     * Méthode renvoyant le code généré pour un marathon
     *
     * @return
     */
    String generateCode();
    
    /**
     * Renvoi la catégorie de l'équipe actuellement connecté
     * @return
     */
    public String getCategoryOfCurrentTeam();
    
        /**
     * Permet de trouver la liste des équipes participantes à un test
     * <pre>La catégorie est implicite</pre>
     * @param test
     * @return 
     * @throws java.lang.Exception 
     */
    public List<Equipe> findParticipants(Test test) throws Exception;
    
    /**
     * Permet de trouver la liste des équipes participantes à un marathon
     * <pre>La catégorie doit être explicite</pre>
     * @param marathon
     * @param categorieEquipe
     * @return 
     * @throws java.lang.Exception 
     */
    public List<Equipe> findParticipants(Marathon marathon, CategorieEquipe categorieEquipe) throws Exception;
    
    /**
     * Permet de trouver les équipe/score d'un test classés du premier au dernier
     * @param test
     * @return 
     * @throws java.lang.Exception 
     */
    public Map<Equipe, Long> findEquipesClassement(Test test) throws Exception;
    
    /**
     * Permet de trouver les équipe/score d'un marathon classés du premier au dernier
     * <pre>La catégorie doit être explicite</pre>
     * @param marathon
     * @param categorieEquipe
     * @return 
     * @throws java.lang.Exception 
     */
    public Map<Equipe, Long> findEquipesClassement(Marathon marathon, CategorieEquipe categorieEquipe) throws Exception;
    
    /**
     * Permet de calculer le score de l'équipe pour un test donné
     * <pre>La catégorie est implicite</pre>
     * @param equipe
     * @param test
     * @return 
     * @throws java.lang.Exception 
     */
    public Long calculateEquipeScore(Equipe equipe, Test test) throws Exception;
    
    /**
     * Permet de calculer le score de l'équipe pour un test donné
     * @param equipe
     * @param marathon
     * @return 
     * @throws java.lang.Exception 
     */
    public Long calculateEquipeScore(Equipe equipe, Marathon marathon) throws Exception;
    
    /**
     * Permet de calculer le pourcentage réalisé par une équipe lors d'un test donné
     * @param equipe
     * @param test
     * @return 
     * @throws java.lang.Exception 
     */
    public Double calculateEquipeScorePercent(Equipe equipe, Test test) throws Exception;
}
