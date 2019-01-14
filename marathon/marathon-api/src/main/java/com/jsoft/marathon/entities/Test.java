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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "test")
public class Test extends BaseEntity{
    
    public static final String SEQUENCE = "test";
    
    @Id
    @Column(name = "code", nullable = false, length = 5)
    private String code;
    
    @Column(name = "libelle", nullable = false, length = 100)
    private String libelle;
    
    @Size(min = 1, max = 350)
    @Column(name = "prerequis", nullable = false)
    private String prerequis;
    
    @Column(name = "date_test", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateTest;
    
    @Column(name = "heure_debut", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date heureDebut;
    
    @Column(name = "heure_fin", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date heureFin;
    
    @Column(name = "test_final", nullable = false)
    private boolean testFinal;
    
    @ManyToOne(cascade = {}, optional = false)
    @JoinColumn(name = "code_marathon")
    private Marathon marathon;
    
    @ManyToOne(cascade = {}, optional = false)
    @JoinColumn(name = "code_categorie_equipe")
    private CategorieEquipe categorieEquipe;
    
    @OneToMany(cascade = {}, mappedBy = "test", fetch = FetchType.EAGER)
    private List<Exercice> exercices = new ArrayList<>();

    public Test() {
    }

    public Test(String code, String libelle, String prerequis, Date date, Date heureDebut, Date heureFin, 
            boolean testFinal) {
        this.code = code;
        this.libelle = libelle;
        this.prerequis = prerequis;
        this.dateTest = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.testFinal = testFinal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrerequis() {
        return prerequis;
    }

    public void setPrerequis(String prerequis) {
        this.prerequis = prerequis;
    }

    public Date getDateTest() {
        return dateTest;
    }

    public void setDateTest(Date dateTest) {
        this.dateTest = dateTest;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }
    
    public String getTestFinal1(){
        return this.isTestFinal()? "Oui" : "Non";
    }

    public boolean isTestFinal() {
        return testFinal;
    }

    public void setTestFinal(boolean testFinal) {
        this.testFinal = testFinal;
    }

    public Marathon getMarathon() {
        return marathon;
    }

    public void setMarathon(Marathon marathon) {
        this.marathon = marathon;
    }

    public CategorieEquipe getCategorieEquipe() {
        return categorieEquipe;
    }

    public void setCategorieEquipe(CategorieEquipe categorieEquipe) {
        this.categorieEquipe = categorieEquipe;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

    public void setExercices(List<Exercice> exercices) {
        this.exercices = exercices;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.code);
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
        final Test other = (Test) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Test{" + "code=" + code + ", prerequis=" + prerequis + ", date=" + dateTest + ", heureDebut=" 
                + heureDebut + ", heureFin=" + heureFin + ", testFinal=" + testFinal + '}';
    }
    
}
