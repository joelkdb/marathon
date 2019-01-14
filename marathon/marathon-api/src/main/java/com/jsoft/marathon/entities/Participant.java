/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.entities;

import com.webapp.commons.core.entities.BaseEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "participants")
public class Participant extends BaseEntity {

    public static final String SEQUENCE = "participant";

    @Id
    @Column(name = "code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "nom", nullable = false, length = 30)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 30)
    private String prenom;
    
    @Column(name = "etablissement", nullable = true, length = 30)
    private String etablissement;

    @Column(name = "niveau_etude", nullable = false, length = 5)
    private String niveauEtude;

    @ManyToOne(cascade = {}, optional = false)
    @JoinColumn(name = "code_equipe")
    private Equipe equipe;

    public Participant() {
    }

    public Participant(String nom, String prenom, String etablissement, String niveauEtude, Equipe equipe) {
        this.nom = nom;
        this.prenom = prenom;
        this.etablissement = etablissement;
        this.niveauEtude = niveauEtude;
        this.equipe = equipe;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
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
        hash = 13 * hash + Objects.hashCode(this.code);
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
        final Participant other = (Participant) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Participant{" + "code=" + code + ", nom=" + nom + ", prenom=" + prenom + ", niveauEtude="
                + niveauEtude + '}';
    }

}
