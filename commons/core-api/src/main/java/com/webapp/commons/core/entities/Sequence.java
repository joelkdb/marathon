/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.entities;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author komilo
 */
@Entity
@Table(name = "core_sequences")
public class Sequence extends BaseEntity {
    
    public static final long SEQUENCE_START_VALUE = 1;
    
    @Id
    @Column(name = "code", nullable = false, length = 32)
    private String code;
    
    @Column(name = "sequence_year", nullable = false)
    private int sequenceYear;
    
    @Column(name = "sequence_value", nullable = false)
    private long sequenceValue;

    public Sequence() {
        this.sequenceValue = 1;
    }

    public Sequence(String code, long sequenceValue) {
        this.code = code;
        this.sequenceYear = LocalDate.now().getYear();
        this.sequenceValue = sequenceValue;
    }

    public Sequence(String code, int sequenceYear, long sequenceValue) {
        this.code = code;
        this.sequenceYear = sequenceYear;
        this.sequenceValue = sequenceValue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSequenceYear() {
        return sequenceYear;
    }

    public void setSequenceYear(int sequenceYear) {
        this.sequenceYear = sequenceYear;
    }

    public long getSequenceValue() {
        return sequenceValue;
    }

    public void setSequenceValue(long sequenceValue) {
        this.sequenceValue = sequenceValue;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sequence other = (Sequence) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return "Sequence{" + "code=" + code + ", sequenceYear=" + sequenceYear + ", sequenceValue=" + sequenceValue + '}';
    }
}
