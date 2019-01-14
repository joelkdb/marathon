/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.entities.Exercice;
import com.jsoft.marathon.entities.Test;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import javax.ejb.Remote;

/**
 *
 * @author joelkdb
 */
@Remote
public interface ExerciceServiceBeanRemote extends GenericServiceBeanRemote<Exercice, String> {

    void update(Exercice e);

    /**
     * Méthode renvoyant le code généré pour un exercice
     *
     * @return
     */
    String generateCode();
    
        /** 
     * Permet d'avoir le nombre de jokers que procure la résolution de l'exercice
     * @param exercice
     * @return 
     * @throws java.lang.Exception 
     */
    public Integer findNbJokersToWin(Exercice exercice) throws Exception;
    
    /** 
     * Permet d'avoir le nombre de jokers qu'il faut pour voir les indications de l'exercice
     * @param exercice
     * @return 
     * @throws java.lang.Exception 
     */
    public Integer findNbJokersToLoose(Exercice exercice) throws Exception;
}
