/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Marathon;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import javax.ejb.Remote;

/**
 *
 * @author joelkdb
 */
@Remote
public interface MarathonServiceBeanRemote extends GenericServiceBeanRemote<Marathon, String> {

    /**
     * Permet de modifier une occurence de Marathon
     *
     * @param m
     */
    void update(Marathon m);

    /**
     * Méthode renvoyant le code généré pour un marathon
     *
     * @return
     */
    String generateCode();

    /**
     * Permet de renvoyer le marathon actuel
     *
     * @return
     */
    Marathon findCurrentMarathon();

    /**
     * Permet de calculer le nombre total de points pondéré au marathon pour une
     * catégorie donnée (somme des points pondérés aux tests passés)
     *
     * @param marathon
     * @param categorieEquipe
     * @return
     * @throws java.lang.Exception
     */
    public Long calculateTotalPoints(Marathon marathon, CategorieEquipe categorieEquipe) throws Exception;
}
