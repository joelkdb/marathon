/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.entities.MarathonEquipe;
import com.jsoft.marathon.entities.MarathonEquipePK;
import com.webapp.commons.core.entities.User;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import javax.ejb.Remote;

/**
 *
 * @author joelkdb
 */
@Remote
public interface MarathonEquipeServiceBeanRemote extends
        GenericServiceBeanRemote<MarathonEquipe, MarathonEquipePK> {

    /**
     * Permet de mettre à jour une occurrence de MarathonEquipe
     * @param t
     */
    void update(MarathonEquipe t);
    
    /**
     * Permet de trouver l'équipe (pour le marathon en cours) à partir de l'utilisateur connecté et du marathon en cours
     * @param user
     * @param marathon
     * @return 
     * @throws java.lang.Exception 
     */
    public MarathonEquipe findActualMarathonEquipe(User user, Marathon marathon) throws Exception;
}
