/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.service;

import com.jsoft.marathon.dao.CategorieEquipeDAOBeanLocal;
import com.jsoft.marathon.entities.CategorieEquipe;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.exception.BusinessException;
import com.webapp.commons.core.service.GenericServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author joelkdb
 */
@Stateless
public class CategorieEquipeServiceBean extends GenericServiceBean<CategorieEquipe, String>
        implements CategorieEquipeServiceBeanRemote {

    @EJB
    private CategorieEquipeDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<CategorieEquipe, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(CategorieEquipe e) {
        return e.getCode();
    }

    @Override
    public void deleteOne(CategorieEquipe e) {
        try {
            super.deleteOne(e);
//            this.logService.info(message, categoryCode);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void addOne(CategorieEquipe e) {
        try {
            super.addOne(e);
//            this.logService.info(message, categoryCode);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

    @Override
    public void update(CategorieEquipe ce) {
        try {
            this.dao.updateOne(ce);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
    }

}