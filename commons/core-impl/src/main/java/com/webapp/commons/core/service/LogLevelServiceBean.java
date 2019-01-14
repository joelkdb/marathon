/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.dao.LogLevelDAOBeanLocal;
import com.webapp.commons.core.entities.LogLevel;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author Ramses
 */
@Stateless
public class LogLevelServiceBean extends GenericServiceBean<LogLevel, Integer> 
        implements LogLevelServiceBeanRemote{

    @EJB
    private LogLevelDAOBeanLocal dao;
    
    @Override
    protected GenericDAOBeanLocal<LogLevel, Integer> getDAO() {
        return this.dao;
    }

    @Override
    public Integer getId(LogLevel e) {
        return e.getId();
    }
    
}
