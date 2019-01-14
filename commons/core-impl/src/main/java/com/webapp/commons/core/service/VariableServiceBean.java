/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.dao.VariableDAOBeanLocal;
import com.webapp.commons.core.entities.Variable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author komilo
 */
@Stateless
public class VariableServiceBean extends GenericServiceBean<Variable, String> 
        implements VariableServiceBeanRemote {
    
    @EJB
    private VariableDAOBeanLocal dao;
    
    private final Logger logger;

    public VariableServiceBean() {
        this.logger = Logger.getLogger(VariableServiceBean.class.getName());
    }

    @Override
    protected GenericDAOBeanLocal<Variable, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Variable e) {
        return e.getName();
    }

    @Override
    public void setValue(String name, Object value) {
        this.setValue(name, value.toString());
    }

    @Override
    public void setValue(String name, String value) {
        Variable variable = this.dao.getOne(name);
        if (variable == null) {
            variable = new Variable(name);
            this.logger.log(Level.WARNING, "La variable nommée '{0}' n'existe pas; Création...", name);
        }
        variable.setValue(value);
        this.dao.updateOne(variable);
    }

    @Override
    public String getValue(String name) {
        Variable variable = this.dao.getOne(name);
        if (variable == null) {
            this.logger.log(Level.WARNING, "La variable nommée '{0}' n'existe pas; 'null' renvoyée.", name);
            return null;
        }
        return variable.getValue();
    }

    @Override
    public int getIntValue(String name) {
        String value = this.getValue(name);
        return value == null ? 0 : Integer.valueOf(value);
    }

    @Override
    public long getLongValue(String name) {
        String value = this.getValue(name);
        return value == null ? 0l : Long.valueOf(value);
    }

    @Override
    public float getFloatValue(String name) {
        String value = this.getValue(name);
        return value == null ? 0.f : Float.valueOf(value);
    }

    @Override
    public double getDoubleValue(String name) {
        String value = this.getValue(name);
        return value == null ? 0. : Double.valueOf(value);
    }

    @Override
    public Date getDateValue(String name) {
        try {
            String value = this.getValue(name);
            return value == null ? null : new SimpleDateFormat("dd/MM/yyyy").parse(value);
        } catch (ParseException ex) {
            this.logger.log(Level.WARNING, "Erreur de conversion de la date: {0}.", ex.getMessage());
            return null;
        }
    }
}
