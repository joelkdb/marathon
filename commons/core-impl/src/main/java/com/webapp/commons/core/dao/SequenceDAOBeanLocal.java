/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.dao;

import com.webapp.commons.core.entities.Sequence;
import javax.ejb.Local;


/**
 *
 * @author komilo
 */
@Local
public interface SequenceDAOBeanLocal extends GenericDAOBeanLocal<Sequence, String> {
    
}
