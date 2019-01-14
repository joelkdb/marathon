/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.entities;

import com.webapp.commons.core.entities.BaseEntity;
import com.webapp.commons.core.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author joelkdb
 */
@Entity
@Table(name = "equipes")
public class Equipe extends BaseEntity{
    
    public static final String SEQUENCE = "equipe";
    
    @Id
    @Column(name = "code", length = 5, nullable = false)
    private String code;
    
    @Column(name = "nom", length = 30, nullable = false, unique = true)
    private String nom;
    
    @Column(name = "chemin", nullable = true)
    private String chemin;
    
    @ManyToOne(cascade = {}, optional = false)
    @JoinColumn(name = "code_categorie_equipe")
    private CategorieEquipe categorieEquipe;
    
    @ManyToOne(cascade = {})
    @JoinColumn(name = "id_user")
    private User user;
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "equipe", fetch = FetchType.EAGER)
    private List<Participant> participants = new ArrayList<>();
    
    @OneToMany(cascade = {}, mappedBy = "equipe")
    private List<MarathonEquipe> marathonEquipes = new ArrayList<>();
    
    @OneToMany(cascade = {}, mappedBy = "equipe")
    private List<ParticipationMarathon> participationMarathons = new ArrayList<>();
    
    @OneToMany(cascade = {}, mappedBy = "equipe", fetch = FetchType.EAGER)
    private List<EquipeExercice> equipeExercices = new ArrayList<>();

    public Equipe() {
    }

    public Equipe(String code, String nom, CategorieEquipe categorieEquipe, User user) {
        this.code = code;
        this.nom = nom;
        this.categorieEquipe = categorieEquipe;
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
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

    public List<EquipeExercice> getEquipeExercices() {
        return equipeExercices;
    }

    public void setEquipeExercices(List<EquipeExercice> equipeExercices) {
        this.equipeExercices = equipeExercices;
    }

    public CategorieEquipe getCategorieEquipe() {
        return categorieEquipe;
    }

    public void setCategorieEquipe(CategorieEquipe categorieEquipe) {
        this.categorieEquipe = categorieEquipe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.code);
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
        final Equipe other = (Equipe) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Equipe{" + "code=" + code + ", nom=" + nom + '}';
    }
    
    
}
