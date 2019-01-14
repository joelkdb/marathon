/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.entities;

import com.webapp.commons.core.entities.BaseEntity;
import java.util.Objects;
import javax.persistence.Column;
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
@Table(name = "marathon_equipes")
public class MarathonEquipe extends BaseEntity {

    @EmbeddedId
    protected MarathonEquipePK marathonEquipePK;

    @ManyToOne(optional = false, cascade = {})
    @JoinColumn(name = "code_marathon", insertable = false, updatable = false)
    private Marathon marathon;

    @ManyToOne(optional = false, cascade = {})
    @JoinColumn(name = "code_equipe", insertable = false, updatable = false)
    private Equipe equipe;

    @Column(name = "authorized", nullable = false)
    private boolean authorized;

    @Column(name = "nombre_jockers", nullable = false)
    private int nombreJockers;

    public MarathonEquipe() {
    }

    public MarathonEquipe(Marathon marathon, Equipe equipe, boolean authorized, int nombreJockers) {
        this.marathon = marathon;
        this.equipe = equipe;
        this.authorized = authorized;
        this.nombreJockers = nombreJockers;
    }

    public MarathonEquipe(MarathonEquipePK marathonEquipePK, Marathon marathon, Equipe equipe,
            boolean authorized, int nombreJockers) {
        this.marathonEquipePK = marathonEquipePK;
        this.marathon = marathon;
        this.equipe = equipe;
        this.authorized = authorized;
        this.nombreJockers = nombreJockers;
    }

    public MarathonEquipe(String codeMarathon, String codeEquipe) {
        this.marathonEquipePK = new MarathonEquipePK(codeMarathon, codeEquipe);
    }

    public MarathonEquipePK getMarathonEquipePK() {
        return marathonEquipePK;
    }

    public void setMarathonEquipePK(MarathonEquipePK marathonEquipePK) {
        this.marathonEquipePK = marathonEquipePK;
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

    public String getAuthorized1() {
        return this.isAuthorized() ? "Oui" : "Non";
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public int getNombreJockers() {
        return nombreJockers;
    }

    public void setNombreJockers(int nombreJockers) {
        this.nombreJockers = nombreJockers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.marathonEquipePK);
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
        final MarathonEquipe other = (MarathonEquipe) obj;
        if (!Objects.equals(this.marathonEquipePK, other.marathonEquipePK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MarathonEquipe{" + "marathonEquipePK=" + marathonEquipePK + ", marathon=" + marathon
                + ", equipe=" + equipe + ", authorized=" + authorized + ", nombreJockers=" + nombreJockers + '}';
    }

}
