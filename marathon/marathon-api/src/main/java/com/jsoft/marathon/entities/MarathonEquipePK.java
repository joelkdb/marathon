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
public class MarathonEquipePK  implements Serializable {
    
    @Basic(optional = false)
    @Column(name = "code_marathon", nullable = false)
    private String codeMarathon;
    
    @Basic(optional = false)
    @Column(name = "code_equipe", nullable = false)
    private String codeEquipe;

    public MarathonEquipePK() {
    }

    public MarathonEquipePK(String codeMarathon, String codeEquipe) {
        this.codeMarathon = codeMarathon;
        this.codeEquipe = codeEquipe;
    }

    public String getCodeMarathon() {
        return codeMarathon;
    }

    public void setCodeMarathon(String codeMarathon) {
        this.codeMarathon = codeMarathon;
    }

    public String getCodeEquipe() {
        return codeEquipe;
    }

    public void setCodeEquipe(String codeEquipe) {
        this.codeEquipe = codeEquipe;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.codeMarathon);
        hash = 67 * hash + Objects.hashCode(this.codeEquipe);
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
        final MarathonEquipePK other = (MarathonEquipePK) obj;
        if (!Objects.equals(this.codeMarathon, other.codeMarathon)) {
            return false;
        }
        if (!Objects.equals(this.codeEquipe, other.codeEquipe)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MarathonEquipePK{" + "codeMarathon=" + codeMarathon + ", codeEquipe=" + codeEquipe + '}';
    }
    
}
