/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.dao;

import com.webapp.commons.core.entities.LogLevel;
import javax.ejb.Stateless;


/**
 *
 * @author Ramses
 */
@Stateless
public class LogLevelDAOBean extends GenericDAOBean<LogLevel, Integer> implements LogLevelDAOBeanLocal {

    public LogLevelDAOBean() {
        super(LogLevel.class);
    }
}
