/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import java.util.Date;
import javax.ejb.Remote;
import com.webapp.commons.core.entities.Variable;

/**
 *
 * @author komilo
 */
@Remote
public interface VariableServiceBeanRemote extends GenericServiceBeanRemote<Variable, String> {
    
    void setValue(String name, Object value);
    
    void setValue(String name, String value);
    
    String getValue(String name);
    
    int getIntValue(String name);
    
    long getLongValue(String name);
    
    float getFloatValue(String name);
    
    double getDoubleValue(String name);
    
    Date getDateValue(String name);
}
