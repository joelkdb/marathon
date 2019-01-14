/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.entities;

import com.webapp.commons.core.entities.BaseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "marathons")
public class Marathon extends BaseEntity {

    public static final String SEQUENCE = "marathon";

    @Id
    @Column(name = "code", nullable = false, length = 5)
    private String code;

    @Column(name = "libelle", nullable = false, length = 30)
    private String libelle;

    @Column(name = "date_debut", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Column(name = "date_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @OneToMany(cascade = {}, mappedBy = "marathon")
    private List<Test> tests = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "marathon")
    private List<MarathonEquipe> marathonEquipes = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "marathon")
    private List<ParticipationMarathon> participationMarathons = new ArrayList<>();

    public Marathon() {
    }

    public Marathon(String code, String libelle, Date dateDebut, Date dateFin) {
        this.code = code;
        this.libelle = libelle;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<MarathonEquipe> getMarathonEquipes() {
        return marathonEquipes;
    }

    public void setMarathonEquipes(List<MarathonEquipe> marathonEquipes) {
        this.marathonEquipes = marathonEquipes;
    }

    public List<ParticipationMarathon> getParticipationMarathons() {
        return participationMarathons;
    }

    public void setParticipationMarathons(List<ParticipationMarathon> participationMarathons) {
        this.participationMarathons = participationMarathons;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.code);
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
        final Marathon other = (Marathon) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Marathon{" + "code=" + code + ", libelle=" + libelle + ", dateDebut=" + dateDebut + ", dateFin="
                + dateFin + '}';
    }

}
