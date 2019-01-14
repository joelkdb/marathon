/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.web.beans;

import com.joel.webapp.utils.Mtm;
import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.Participant;
import com.jsoft.marathon.entities.Test;
import com.jsoft.marathon.service.CategorieEquipeServiceBeanRemote;
import com.jsoft.marathon.service.EquipeServiceBeanRemote;
import com.jsoft.marathon.utils.MarathonConstants;
import com.jsoft.marathon.utils.MarathonUtils;
import com.webapp.commons.core.entities.User;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import com.webapp.commons.core.service.UserServiceBeanRemote;
import com.webapp.commons.core.web.beans.GenericBean;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@ViewScoped
public class EquipeBean extends GenericBean<Equipe, String> {

    @EJB
    private EquipeServiceBeanRemote service;

    @EJB
    private CategorieEquipeServiceBeanRemote categorieEquipeService;

    @EJB
    private UserServiceBeanRemote userService;

    private Participant participant;

    private boolean disableSave;
    private boolean disableEdit;

    @ManagedProperty(value = "#{testBean}")
    private TestBean testBean;

    private Test test;

    private List<Equipe> dataList;

    @Override
    public void initAdd() {
        this.entity = new Equipe();
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Equipe();
        this.participant = new Participant();
        this.disableSave = false;
        this.disableEdit = true;
        this.dataList = new ArrayList<>();
        try {
            setTest(getTestBean().getTest());
            setDataList(this.service.findParticipants(getTest()));
        } catch (Exception ex) {
            Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
            Mtm.joelMessageErrorPerso(MarathonUtils.getValueFromBundle("MSG_ERROR_DB_LOAD",
                    MarathonConstants.BUNDLE_NAME));
        }
    }

    @Override
    public GenericServiceBeanRemote<Equipe, String> getService() {
        return service;
    }

    public void renitParticipant() {
        this.participant.setEquipe(this.entity);
        this.participant = new Participant();
    }

    @Override
    public String delete(Equipe e) {
        try {
            this.service.deleteOne(e);
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

    public List<Equipe> getEquipes() {
        return this.service.getAll();
    }

    public List<CategorieEquipe> getCategories() {
        return this.categorieEquipeService.getAll();
    }

    public List<User> getUsers() {
        return this.userService.getAll("id", false);
    }

    @Override
    public void setEntity(Equipe entity) {
        super.setEntity(entity);
        this.disableEdit = false;
        this.disableSave = true;
    }

    @Override
    public LazyDataModel<Equipe> getModel() {
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

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public TestBean getTestBean() {
        return testBean;
    }

    public void setTestBean(TestBean testBean) {
        this.testBean = testBean;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Equipe> getDataList() {
        return dataList;
    }

    public void setDataList(List<Equipe> dataList) {
        this.dataList = dataList;
    }

}
