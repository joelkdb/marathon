/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.CategorieEquipe;
import com.webapp.commons.core.dao.GenericDAOBean;
import javax.ejb.Stateless;


/**
 *
 * @author joelkdb
 */
@Stateless
public class CategorieEquipeDAOBean extends GenericDAOBean<CategorieEquipe, String>
        implements CategorieEquipeDAOBeanLocal {

    public CategorieEquipeDAOBean() {
        super(CategorieEquipe.class);
    }
}
