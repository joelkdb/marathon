/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.entities.Sequence;
import javax.ejb.Remote;

/**
 *
 * @author komilo
 */
@Remote
public interface SequenceServiceBeanRemote 
        extends GenericServiceBeanRemote<Sequence, String> {
    
    long getNextValue(String code);
    Sequence getNextSequence(String code);
    Sequence init(String code);
}
