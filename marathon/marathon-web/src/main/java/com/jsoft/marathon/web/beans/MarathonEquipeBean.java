/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.web.beans;

import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.entities.MarathonEquipe;
import com.jsoft.marathon.entities.MarathonEquipePK;
import com.jsoft.marathon.service.MarathonEquipeServiceBeanRemote;
import com.webapp.commons.core.exception.BusinessException;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import com.webapp.commons.core.web.beans.GenericBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@ViewScoped
public class MarathonEquipeBean extends GenericBean<MarathonEquipe, MarathonEquipePK> {

    @EJB
    private MarathonEquipeServiceBeanRemote service;
    
    private Marathon marathon;
    private Equipe equipe;
    private MarathonEquipePK marathonEquipePK;

    private boolean disableSave;
    private boolean disableEdit;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new MarathonEquipe();
        this.marathonEquipePK = new MarathonEquipePK();
        this.marathon = new Marathon();
        this.equipe = new Equipe();
        this.disableSave = false;
        this.disableEdit = true;
    }

    @Override
    public GenericServiceBeanRemote<MarathonEquipe, MarathonEquipePK> getService() {
        return service;
    }
    
    @Override
    public String delete(MarathonEquipe me) {
        try {
            this.service.deleteOne(me);
            Messages.addFlashGlobalInfo("Suppression effectué avec succés !!!");
        } catch (BusinessException be) {
            Messages.addFlashGlobalError("Erreur lors du processus !!!");
            System.out.println(be.getMessage());
        }
        return null;
    }

    @Override
    public String update() {
        try {
            this.service.update(this.entity);
            Messages.addFlashGlobalInfo("Modification effectué avec succés !!!");
            this.init();
        } catch (BusinessException be) {
            Messages.addFlashGlobalError("Erreur lors du processus !!!");
            System.out.println(be.getMessage());
        }
        return null;
    }

    @Override
    public String add() {
        try {
            this.marathonEquipePK.setCodeMarathon(this.getMarathon().getCode());
            this.marathonEquipePK.setCodeEquipe(this.getEquipe().getCode());
            this.entity.setMarathonEquipePK(marathonEquipePK);
            this.entity.setMarathon(this.marathon);
            this.entity.setEquipe(this.equipe);
            this.service.addOne(this.entity);
            this.init();
            Messages.addFlashGlobalInfo("Ajout effectué avec succés !!!");
        } catch (BusinessException be) {
            Messages.addFlashGlobalError("Erreur lors du processus !!!");
            System.out.println(be.getMessage());
            this.init();
        }
        return null;
    }

    @Override
    public boolean canAdd() {
        return true;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public boolean canDelete() {
        return true;
    }

    @Override
    public void initAdd() {
        this.entity = new MarathonEquipe();
    }

    @Override
    public void setEntity(MarathonEquipe entity) {
        super.setEntity(entity);
        this.disableEdit = false;
        this.disableSave = true;
    }

    @Override
    public LazyDataModel<MarathonEquipe> getModel() {
        return super.getModel();
    }

    public boolean isDisableSave() {
        return disableSave;
    }

    public void setDisableSave(boolean disableSave) {
        this.disableSave = disableSave;
    }

    public boolean isDisableEdit() {
        return disableEdit;
    }

    public void setDisableEdit(boolean disableEdit) {
        this.disableEdit = disableEdit;
    }

    public Marathon getMarathon() {
        return marathon;
    }

    public void setMarathon(Marathon marathon) {
        this.marathon = marathon;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public MarathonEquipePK getMarathonEquipePK() {
        return marathonEquipePK;
    }

    public void setMarathonEquipePK(MarathonEquipePK marathonEquipePK) {
        this.marathonEquipePK = marathonEquipePK;
    }
  
}
