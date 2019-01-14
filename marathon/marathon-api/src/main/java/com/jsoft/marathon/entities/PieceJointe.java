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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "piece_jointes")
public class PieceJointe extends BaseEntity {

    public static final String SEQUENCE = "piece_jointe";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 30)
    private String nom;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "pieceJointe")
    private List<ExercicePieceJointe> exercicePieceJointes = new ArrayList<>();

    public PieceJointe() {
    }

    public PieceJointe(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExercicePieceJointe> getExercicePieceJointes() {
        return exercicePieceJointes;
    }

    public void setExercicePieceJointes(List<ExercicePieceJointe> exercicePieceJointes) {
        this.exercicePieceJointes = exercicePieceJointes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final PieceJointe other = (PieceJointe) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PieceJointe{" + "id=" + id + ", nom=" + nom + ", description=" + description + '}';
    }

}
