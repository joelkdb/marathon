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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "exercices")
public class Exercice extends BaseEntity{
    
    public static final String SEQUENCE = "exercice";
    
    @Id
    @Column(name = "code", nullable = false, length = 8)
    private String code;
    
    @Column(name = "titre", nullable = false, length = 50)
    private String titre;
    
    @Lob @Basic(fetch = FetchType.LAZY, optional = true)
    @Column(name = "enonce", nullable = true)
    private String enonce;
    
    @Column(name = "resume", nullable = false)
    private String resume;
    
    @Column(name = "solution", nullable = false, length = 30)
    private String solution;
    
    @Column(name = "point", nullable = false)
    private Integer point;
    
    @Column(name = "indication", nullable = false, length = 255)
    private String indication;
    
    @ManyToOne(cascade = {}, optional = false)
    @JoinColumn(name = "code_test")
    private Test test;
    
//    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "exercice")
//    private List<ParticipationMarathon> participationMarathons = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "exercice")
    private List<EquipeExercice> equipeExercices = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "exercice", fetch = FetchType.EAGER)
    private List<ExercicePieceJointe> exercicePieceJointes = new ArrayList<>();

    public Exercice() {
    }

    public Exercice(String code, String titre, String enonce, String solution, Integer point, String indication) {
        this.code = code;
        this.titre = titre;
        this.enonce = enonce;
        this.solution = solution;
        this.point = point;
        this.indication = indication;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
    
//    public List<ParticipationMarathon> getParticipationMarathons() {
//        return participationMarathons;
//    }
//
//    public void setParticipationMarathons(List<ParticipationMarathon> participationMarathons) {
//        this.participationMarathons = participationMarathons;
//    }

    public List<EquipeExercice> getEquipeExercices() {
        return equipeExercices;
    }

    public void setEquipeExercices(List<EquipeExercice> equipeExercices) {
        this.equipeExercices = equipeExercices;
    }

    public List<ExercicePieceJointe> getExercicePieceJointes() {
        return exercicePieceJointes;
    }

    public void setExercicePieceJointes(List<ExercicePieceJointe> exercicePieceJointes) {
        this.exercicePieceJointes = exercicePieceJointes;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.code);
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
        final Exercice other = (Exercice) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Exercice{" + "code=" + code + ", titre=" + titre + ", solution=" + solution 
                + ", point=" + point + ", indication=" + indication + '}';
    }
    
}
