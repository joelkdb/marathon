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
import javax.validation.constraints.Size;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "equipe_exercices")
public class EquipeExercice extends BaseEntity {
    
    @EmbeddedId
    protected EquipeExercicePK equipeExercicePK;
    
    @ManyToOne(optional = false, cascade = {})
    @JoinColumn(name = "code_equipe", insertable = false, updatable = false)
    private Equipe equipe;
    
    @ManyToOne(optional = false, cascade = {})
    @JoinColumn(name = "code_exercice", insertable = false, updatable = false)
    private Exercice exercice;
    
//    @ManyToOne(optional = false, cascade = {})
//    @JoinColumn(name = "id_langage")
//    private Langage langage;
   
    @Column(name = "nom_langage", nullable = true)
    private String nomLangage;
    
    @Column(name = "jocker_actif", nullable = false)
    private boolean jockerActif;
    
    @Column(name = "solution_envoye", length = 25)
    private String solutionEnvoye;
    
    @Size(max = 1050)
    @Column(name = "code_source_envoye")
    private String codeSourceEnvoye;
    
    @Column(name = "resolu", nullable = false)
    private boolean resolu;

    public EquipeExercice() {
    }

    public EquipeExercice(Equipe equipe, Exercice exercice, Langage langage, boolean jockerActif, 
            String solutionEnvoye, String codeSourceEnvoye, boolean resolu) {
        this.equipe = equipe;
        this.exercice = exercice;
        //this.langage = langage;
        this.jockerActif = jockerActif;
        this.solutionEnvoye = solutionEnvoye;
        this.codeSourceEnvoye = codeSourceEnvoye;
        this.resolu = resolu;
    }

    public EquipeExercice(EquipeExercicePK equipeExercicePK, Equipe equipe, Exercice exercice, Langage langage, 
            boolean jockerActif, String solutionEnvoye, String codeSourceEnvoye, boolean resolu) {
        this.equipeExercicePK = equipeExercicePK;
        this.equipe = equipe;
        this.exercice = exercice;
        //this.langage = langage;
        this.jockerActif = jockerActif;
        this.solutionEnvoye = solutionEnvoye;
        this.codeSourceEnvoye = codeSourceEnvoye;
        this.resolu = resolu;
    }
    
    public EquipeExercice(String codeEquipe, String codeExercice){
        this.equipeExercicePK = new EquipeExercicePK(codeEquipe, codeExercice);
    }

    public EquipeExercicePK getEquipeExercicePK() {
        return equipeExercicePK;
    }

    public void setEquipeExercicePK(EquipeExercicePK equipeExercicePK) {
        this.equipeExercicePK = equipeExercicePK;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Exercice getExercice() {
        return exercice;
    }

    public void setExercice(Exercice exercice) {
        this.exercice = exercice;
    }

//    public Langage getLangage() {
//        return langage;
//    }
//
//    public void setLangage(Langage langage) {
//        this.langage = langage;
//    }

    public boolean isJockerActif() {
        return jockerActif;
    }

    public void setJockerActif(boolean jockerActif) {
        this.jockerActif = jockerActif;
    }

    public String getSolutionEnvoye() {
        return solutionEnvoye;
    }

    public void setSolutionEnvoye(String solutionEnvoye) {
        this.solutionEnvoye = solutionEnvoye;
    }

    public String getCodeSourceEnvoye() {
        return codeSourceEnvoye;
    }

    public void setCodeSourceEnvoye(String codeSourceEnvoye) {
        this.codeSourceEnvoye = codeSourceEnvoye;
    }

    public boolean isResolu() {
        return resolu;
    }

    public void setResolu(boolean resolu) {
        this.resolu = resolu;
    }

    public String getNomLangage() {
        return nomLangage;
    }

    public void setNomLangage(String nomLangage) {
        this.nomLangage = nomLangage;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.equipeExercicePK);
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
        final EquipeExercice other = (EquipeExercice) obj;
        if (!Objects.equals(this.equipeExercicePK, other.equipeExercicePK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EquipeExercice{" + "equipeExercicePK=" + equipeExercicePK + ", equipe=" + equipe + ", exercice=" 
                + exercice + ", jockerActif=" + jockerActif + ", solutionEnvoye=" + solutionEnvoye
                + ", codeSourceEnvoye=" + codeSourceEnvoye + ", resolu=" + resolu + '}';
    }
    
}
