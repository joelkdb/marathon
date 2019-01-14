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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "langages")
public class Langage extends BaseEntity{
    
    public static final String SEQUENCE= "langage";
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "acronyme", nullable = false, length = 5)
    private String acronyme;
    
    @Column(name = "nom", nullable = false, length = 15)
    private String nom;
    
    @OneToMany(cascade = {}, mappedBy = "langage", fetch = FetchType.EAGER)
    private List<EquipeExercice> equipeExercices = new ArrayList<>();

    public Langage() {
    }

    public Langage(String acronyme, String nom) {
        this.acronyme = acronyme;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronyme() {
        return acronyme;
    }

    public void setAcronyme(String acronyme) {
        this.acronyme = acronyme;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<EquipeExercice> getEquipeExercices() {
        return equipeExercices;
    }

    public void setEquipeExercices(List<EquipeExercice> equipeExercices) {
        this.equipeExercices = equipeExercices;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Langage other = (Langage) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Langage{" + "id=" + id + ", acronyme=" + acronyme + ", nom=" + nom + '}';
    }
    
}
