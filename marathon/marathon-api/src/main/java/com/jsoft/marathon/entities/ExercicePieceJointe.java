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
@Table(name = "exercie_piece_jointes")
public class ExercicePieceJointe extends BaseEntity{
    
    @EmbeddedId
    protected ExercicePieceJointePK exercicePieceJointePK;
    
    @ManyToOne(optional = false, cascade = {})
    @JoinColumn(name = "code_exercice", insertable = false, updatable = false)
    private Exercice exercice;
    
    @ManyToOne(optional = false, cascade = {})
    @JoinColumn(name = "id_piece_jointe", insertable = false, updatable = false)
    private PieceJointe pieceJointe;

    public ExercicePieceJointe() {
    }

    public ExercicePieceJointe(Exercice exercice, PieceJointe pieceJointe) {
        this.exercice = exercice;
        this.pieceJointe = pieceJointe;
    }

    public ExercicePieceJointe(ExercicePieceJointePK exercicePieceJointePK, Exercice exercice, 
            PieceJointe pieceJointe) {
        this.exercicePieceJointePK = exercicePieceJointePK;
        this.exercice = exercice;
        this.pieceJointe = pieceJointe;
    }
    
    public ExercicePieceJointe(String codeExercice, String idPieceJointe){
        this.exercicePieceJointePK = new ExercicePieceJointePK(codeExercice, idPieceJointe);
    }

    public ExercicePieceJointePK getExercicePieceJointePK() {
        return exercicePieceJointePK;
    }

    public void setExercicePieceJointePK(ExercicePieceJointePK exercicePieceJointePK) {
        this.exercicePieceJointePK = exercicePieceJointePK;
    }

    public Exercice getExercice() {
        return exercice;
    }

    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
    }

    public PieceJointe getPieceJointe() {
        return pieceJointe;
    }

    public void setPieceJointe(PieceJointe pieceJointe) {
        this.pieceJointe = pieceJointe;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.exercicePieceJointePK);
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
        final ExercicePieceJointe other = (ExercicePieceJointe) obj;
        if (!Objects.equals(this.exercicePieceJointePK, other.exercicePieceJointePK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExercicePieceJointe{" + "exercicePieceJointePK=" + exercicePieceJointePK + ", exercice=" 
                + exercice + ", pieceJointe=" + pieceJointe + '}';
    }
    
}
