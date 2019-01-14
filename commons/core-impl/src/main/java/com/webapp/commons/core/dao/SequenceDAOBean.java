/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.dao;

import com.webapp.commons.core.entities.Sequence;
import javax.ejb.Stateless;


/**
 *
 * @author komilo
 */
@Stateless
public class SequenceDAOBean extends GenericDAOBean<Sequence, String> 
        implements SequenceDAOBeanLocal {
    
    public SequenceDAOBean() {
        super(Sequence.class);
    }
    
}
