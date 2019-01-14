/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.entities.LogEvent;
import com.webapp.commons.core.entities.LogLevel;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Ramses
 */
@Stateless
public class LogServiceBean implements LogServiceBeanRemote {

    @EJB
    private LogLevelServiceBeanRemote levelService;

    @EJB
    private LogEventServiceBeanRemote eventService;

    @EJB
    private LogCategoryServiceBeanRemote categoryService;

    @EJB
    private UserServiceBeanRemote userService;

    @Override
    public void trace(String message) {
        this.log(message, null, LogLevel.LOG_LEVEL_TRACE_ID);
    }

    @Override
    public void trace(String message, String categoryCode) {
        this.log(message, categoryCode, LogLevel.LOG_LEVEL_TRACE_ID);
    }

    @Override
    public void debug(String message) {
        this.log(message, null, LogLevel.LOG_LEVEL_TRACE_ID);
    }

    @Override
    public void debug(String message, String categoryCode) {
        this.log(message, categoryCode, LogLevel.LOG_LEVEL_TRACE_ID);
    }

    @Override
    public void info(String message) {
        this.log(message, null, LogLevel.LOG_LEVEL_TRACE_ID);
    }

    @Override
    public void info(String message, String categoryCode) {
        this.log(message, categoryCode, LogLevel.LOG_LEVEL_TRACE_ID);
    }

    @Override
    public void warn(String message) {
        this.log(message, null, LogLevel.LOG_LEVEL_TRACE_ID);
    }

    @Override
    public void warn(String message, String categoryCode) {
        this.log(message, categoryCode, LogLevel.LOG_LEVEL_TRACE_ID);
    }

    @Override
    public void error(String message) {
        this.log(message, null, LogLevel.LOG_LEVEL_TRACE_ID);
    }

    @Override
    public void error(String message, String categoryCode) {
        this.log(message, categoryCode, LogLevel.LOG_LEVEL_TRACE_ID);
    }

    private void log(String message, String categoryCode, Integer levelId) {
        LogEvent event = new LogEvent(message, new Date(), this.userService.getClientIPAdress());
        event.setLevel(this.levelService.getOne(levelId));
        event.setCategory(this.categoryService.getOne(categoryCode));
        event.setUser(this.userService.getCurrent());
        this.eventService.addOne(event);
    }
}
