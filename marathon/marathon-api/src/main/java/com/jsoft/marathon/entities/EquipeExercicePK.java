/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author joelkdb
 */
@Embeddable
public class EquipeExercicePK implements Serializable {
    
    @Basic(optional = false)
    @Column(name = "code_equipe", nullable = false)
    private String codeEquipe;
    
    @Basic(optional = false)
    @Column(name = "code_exercice", nullable = false)
    private String codeExercice;

    public EquipeExercicePK() {
    }

    public EquipeExercicePK(String codeEquipe, String codeExercice) {
        this.codeEquipe = codeEquipe;
        this.codeExercice = codeExercice;
    }

    public String getCodeEquipe() {
        return codeEquipe;
    }

    public void setCodeEquipe(String codeEquipe) {
        this.codeEquipe = codeEquipe;
    }

    public String getCodeExercice() {
        return codeExercice;
    }

    public void setCodeExercice(String codeExercice) {
        this.codeExercice = codeExercice;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.codeEquipe);
        hash = 47 * hash + Objects.hashCode(this.codeExercice);
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
        final EquipeExercicePK other = (EquipeExercicePK) obj;
        if (!Objects.equals(this.codeEquipe, other.codeEquipe)) {
            return false;
        }
        if (!Objects.equals(this.codeExercice, other.codeExercice)) {
            return false;
        }
        return true;
    }
    
}
