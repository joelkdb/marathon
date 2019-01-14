/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.web.beans;

import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.service.MarathonServiceBeanRemote;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import com.webapp.commons.core.web.beans.GenericBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author joelkdb
 */
@Named(value = "marathonCrudBean")
@ViewScoped
@ManagedBean(name = "marathonCrudBean")
public class MarathonCrudBean extends GenericBean<Marathon, String> {

    @EJB
    private MarathonServiceBeanRemote service;

    Marathon actual;

    private boolean disableSave;
    private boolean disableEdit;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        //this.setActual(this.service.findCurrentMarathon());
        this.entity = new Marathon();
        this.disableSave = false;
        this.disableEdit = true;
    }

    @Override
    public GenericServiceBeanRemote<Marathon, String> getService() {
        return service;
    }

    public List<Marathon> getMarathons() {
        return this.service.getAll();
    }

    @Override
    public String delete(Marathon m) {
        try {
            this.service.deleteOne(m);
            Messages.addFlashGlobalInfo("Suppression effectué avec succés !!!");
        } catch (Exception be) {
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
        } catch (Exception be) {
            Messages.addFlashGlobalError("Erreur lors du processus !!!");
            System.out.println(be.getMessage());
        }
        return null;
    }

    @Override
    public String add() {
//        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.service.addOne(this.entity);
            this.init();
            Messages.addFlashGlobalInfo("Ajout effectué avec succés !!!");
        } catch (Exception be) {
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
        this.entity = new Marathon();
    }

    @Override
    public void setEntity(Marathon entity) {
        super.setEntity(entity);
        this.disableEdit = false;
        this.disableSave = true;
    }

    @Override
    public LazyDataModel<Marathon> getModel() {
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

}
