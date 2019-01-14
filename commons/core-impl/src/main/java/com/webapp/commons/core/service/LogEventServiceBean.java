/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.dao.LogEventDAOBeanLocal;
import com.webapp.commons.core.entities.LogEvent;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author Ramses
 */
@Stateless
public class LogEventServiceBean extends GenericServiceBean<LogEvent, Long>
        implements LogEventServiceBeanRemote {

    @EJB
    private LogEventDAOBeanLocal dao;

    @Override
    protected GenericDAOBeanLocal<LogEvent, Long> getDAO() {
        return this.dao;
    }

    @Override
    public Long getId(LogEvent e) {
        return e.getId();
    }

}
