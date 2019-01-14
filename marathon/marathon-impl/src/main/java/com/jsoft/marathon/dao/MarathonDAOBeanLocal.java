/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.Marathon;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import javax.ejb.Local;


/**
 *
 * @author joelkdb
 */
@Local
public interface MarathonDAOBeanLocal extends GenericDAOBeanLocal<Marathon, String>{
    
    /**
     * Permet de récupérer le marathon actuel
     * @return le marathon actuel
     */
    Marathon findCurrentMarathon();
}
