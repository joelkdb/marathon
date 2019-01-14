/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.entities.CategorieEquipe;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import javax.ejb.Remote;

/**
 *
 * @author joelkdb
 */
@Remote
public interface CategorieEquipeServiceBeanRemote extends
        GenericServiceBeanRemote<CategorieEquipe, String> {
    void update(CategorieEquipe ce);
}
