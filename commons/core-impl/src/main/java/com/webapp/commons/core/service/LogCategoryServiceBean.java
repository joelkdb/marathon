/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.dao.LogCategoryDAOBeanLocal;
import com.webapp.commons.core.entities.LogCategory;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author Ramses
 */
@Stateless
public class LogCategoryServiceBean extends GenericServiceBean<LogCategory, String> 
        implements LogCategoryServiceBeanRemote{

    @EJB
    private LogCategoryDAOBeanLocal dao;
    
    @Override
    protected GenericDAOBeanLocal<LogCategory, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(LogCategory e) {
        return e.getCode();
    }

    @Override
    public LogCategory findbylabelCategory(String label) {
        return this.dao.findByLabelCategory(label);
    }
}