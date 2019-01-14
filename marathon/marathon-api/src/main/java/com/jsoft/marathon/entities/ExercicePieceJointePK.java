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
public class ExercicePieceJointePK implements Serializable {
    
    @Basic(optional = false)
    @Column(name = "code_exercice", nullable = false)
    private String codeExercice;
    
    @Basic(optional = false)
    @Column(name = "id_piece_jointe", nullable = false)
    private String idPieceJointe;

    public ExercicePieceJointePK() {
    }

    public ExercicePieceJointePK(String codeExercice, String idPieceJointe) {
        this.codeExercice = codeExercice;
        this.idPieceJointe = idPieceJointe;
    }

    public String getCodeExercice() {
        return codeExercice;
    }

    public void setCodeExercice(String codeExercice) {
        this.codeExercice = codeExercice;
    }

    public String getIdPieceJointe() {
        return idPieceJointe;
    }

    public void setIdPieceJointe(String idPieceJointe) {
        this.idPieceJointe = idPieceJointe;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.codeExercice);
        hash = 43 * hash + Objects.hashCode(this.idPieceJointe);
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
        final ExercicePieceJointePK other = (ExercicePieceJointePK) obj;
        if (!Objects.equals(this.codeExercice, other.codeExercice)) {
            return false;
        }
        if (!Objects.equals(this.idPieceJointe, other.idPieceJointe)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExercicePieceJointePK{" + "codeExercice=" + codeExercice + ", idPieceJointe=" + idPieceJointe + '}';
    }
    
}
