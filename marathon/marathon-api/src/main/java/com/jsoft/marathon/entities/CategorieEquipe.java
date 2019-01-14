/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.entities;

import com.webapp.commons.core.entities.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "categorie_equipes")
public class CategorieEquipe extends BaseEntity{
    
    public static final String SEQUENCE = "categorie_equipe";
    
    @Id
    @Column(name = "code", nullable = false, length = 5)
    private String code;
    
    @Column(name = "libelle", nullable = false, length = 30)
    private String libelle;
    
    @OneToMany(cascade = {}, mappedBy = "categorieEquipe")
    private List<Test> tests = new ArrayList<>();
    
    @OneToMany(cascade = {}, mappedBy = "categorieEquipe")
    private List<Equipe> equipes = new ArrayList<>();

    public CategorieEquipe() {
    }

    public CategorieEquipe(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
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

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
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
        final CategorieEquipe other = (CategorieEquipe) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CategorieEquipe{" + "code=" + code + ", libelle=" + libelle + '}';
    }
    
}
