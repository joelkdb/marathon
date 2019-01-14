/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.entities;

import com.webapp.commons.core.entities.BaseEntity;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "participation_marathons")
public class ParticipationMarathon extends BaseEntity {
    
    @EmbeddedId
    protected ParticipationMarathonPK participationMarathonPK;
    
    @ManyToOne(optional = false, cascade = {})
    @JoinColumn(name = "code_marathon", insertable = false, updatable = false)
    private Marathon marathon;

    @ManyToOne(optional = false, cascade = {})
    @JoinColumn(name = "code_equipe", insertable = false, updatable = false)
    private Equipe equipe;

    public ParticipationMarathon() {
    }

    public ParticipationMarathon(Marathon marathon, Equipe equipe) {
        this.marathon = marathon;
        this.equipe = equipe;
    }

    public ParticipationMarathon(ParticipationMarathonPK participationMarathonPK, Marathon marathon, Equipe equipe) {
        this.participationMarathonPK = participationMarathonPK;
        this.marathon = marathon;
        this.equipe = equipe;
    }
    
    public ParticipationMarathon(String codeMarathon, String codeEquipe){
        this.participationMarathonPK = new ParticipationMarathonPK(codeMarathon, codeEquipe);
    }

    public ParticipationMarathonPK getParticipationMarathonPK() {
        return participationMarathonPK;
    }

    public void setParticipationMarathonPK(ParticipationMarathonPK participationMarathonPK) {
        this.participationMarathonPK = participationMarathonPK;
    }

    public Marathon getMarathon() {
        return marathon;
    }

    public void setMarathon(Marathon marathon) {
        this.marathon = marathon;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.participationMarathonPK);
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
        final ParticipationMarathon other = (ParticipationMarathon) obj;
        if (!Objects.equals(this.participationMarathonPK, other.participationMarathonPK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ParticipationMarathon{" + "participationMarathonPK=" + participationMarathonPK + ", marathon=" 
                + marathon + ", equipe=" + equipe + '}';
    }
    
    
}
