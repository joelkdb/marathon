/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.web.beans;

import com.webapp.commons.core.entities.LogEvent;
import com.webapp.commons.core.service.LogEventServiceBeanRemote;
import com.webapp.commons.core.web.models.GenericDataModel;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;

/**
 *
 * @author komilo
 */
@ManagedBean
@ViewScoped
public class LogEventBean implements Serializable {
    
    @EJB
    private LogEventServiceBeanRemote service;
    
    public DataModel<LogEvent> getModel() {
        return new GenericDataModel<>(this.service);
    }
}
