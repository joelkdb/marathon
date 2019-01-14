/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import com.webapp.commons.core.dao.SequenceDAOBeanLocal;
import com.webapp.commons.core.entities.Sequence;
import java.time.LocalDate;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author komilo
 */
@Stateless
public class SequenceServiceBean extends GenericServiceBean<Sequence, String> 
        implements SequenceServiceBeanRemote {
    
    @EJB
    private SequenceDAOBeanLocal dao;
    
    @Override
    protected GenericDAOBeanLocal<Sequence, String> getDAO() {
        return this.dao;
    }

    @Override
    public String getId(Sequence e) {
        return e.getCode();
    }

    @Override
    public long getNextValue(String code) {
        return this.getNextSequence(code).getSequenceValue();
    }

    @Override
    public Sequence getNextSequence(String code) {
        Sequence sequence = this.dao.getOne(code);
        if (sequence == null) {
            sequence = this.init(code);
        }
        int sequenceYear = LocalDate.now().getYear();
        if (sequenceYear > sequence.getSequenceYear()) {
            sequence.setSequenceYear(sequenceYear);
            sequence.setSequenceValue(Sequence.SEQUENCE_START_VALUE);
        } else {
            sequence.setSequenceValue(sequence.getSequenceValue() + 1);
        }
        sequence = this.dao.updateOne(sequence);
        return sequence;
    }

    @Override
    public Sequence init(String code) {
        Sequence sequence = new Sequence(code,
                LocalDate.now().getYear(), Sequence.SEQUENCE_START_VALUE);
        this.dao.addOne(sequence);
        return this.dao.getOne(code);
    }
    
}
