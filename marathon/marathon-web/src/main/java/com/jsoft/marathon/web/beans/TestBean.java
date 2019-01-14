/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.web.beans;

import com.joel.webapp.utils.Mtm;
import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.MarathonEquipe;
import com.jsoft.marathon.entities.Test;
import com.jsoft.marathon.exception.DisqualifiedTeamException;
import com.jsoft.marathon.exception.NoCurrentTestException;
import com.jsoft.marathon.exception.NoLastTestException;
import com.jsoft.marathon.service.MarathonEquipeServiceBeanRemote;
import com.jsoft.marathon.service.TestServiceBeanRemote;
import com.jsoft.marathon.utils.MarathonConstants;
import com.jsoft.marathon.utils.MarathonUtils;
import com.webapp.commons.core.exception.BusinessException;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import com.webapp.commons.core.service.UserServiceBeanRemote;
import com.webapp.commons.core.web.beans.GenericBean;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author joelkdb
 */
@ManagedBean(name = "testBean")
@ViewScoped
public class TestBean extends GenericBean<Test, String> {

    @EJB
    private TestServiceBeanRemote service;
    @EJB
    private MarathonEquipeServiceBeanRemote marathonEquipeService;
    @EJB
    private UserServiceBeanRemote userService;

    @ManagedProperty(value = "#{marathonBean}")
    private MarathonBean marathonBean;

    private Test test;
    private MarathonEquipe marathonEquipe;

    private boolean disableSave;
    private boolean disableEdit;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Test();
        this.disableSave = false;
        this.disableEdit = true;
        Equipe equipe = null;
        try {
            // Récupération de l'équipe connectée
            setMarathonEquipe(this.marathonEquipeService.findActualMarathonEquipe(this.userService.getCurrent(),
                    getMarathonBean().getActual()));
            System.out.println("MARATHON-EQUIPE : " + getMarathonEquipe().getMarathon().getCode()+ " --> "
                    + getMarathonEquipe().getEquipe().getCode());
            equipe = getMarathonEquipe().getEquipe();
            //Récupération du test actuel
            setTest(this.service.findCurrentTest(equipe.getCategorieEquipe()));
        } catch (NoCurrentTestException e) {
            if (getMarathonBean().isMarathonAvailable()) {
                try {
                    // Récupération du test récent s'il ya un marathon en cours
                    setTest(this.service.findLastTest(equipe.getCategorieEquipe()));
                } catch (NoLastTestException ex) {
                    Messages.addFlashError(null, MarathonUtils.getValueFromBundle("MSG_NO_LAST_TEST",
                            MarathonConstants.BUNDLE_NAME));
                } catch (Exception ex1) {
                    Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex1);
                    Mtm.joelMessageErrorPerso(MarathonUtils.getValueFromBundle("MSG_ERROR_DB_LOAD", 
                            MarathonConstants.BUNDLE_NAME));
//                    Messages.addError(null, MarathonUtils.getValueFromBundle("MSG_ERROR_DB_LOAD",
//                            MarathonConstants.BUNDLE_NAME));
                }
            }
        } catch (DisqualifiedTeamException ex) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "",
                            MarathonUtils.getValueFromBundle("MSG_ERROR_DISQUALIFIED_TEAM",
                                    MarathonConstants.BUNDLE_NAME)));
        } catch (Exception ex) {
            Logger.getLogger(TestBean.class.getName()).log(Level.SEVERE, null, ex);
            Mtm.joelMessageErrorPerso(MarathonUtils.getValueFromBundle("MSG_ERROR_DB_LOAD", 
                            MarathonConstants.BUNDLE_NAME));
//            Messages.addError(null, MarathonUtils.getValueFromBundle("MSG_ERROR_DB_LOAD",
//                    MarathonConstants.BUNDLE_NAME));
        }
    }

    public Boolean isATestAvailable() {
        return this.test!= null;
    }

    @Override
    public GenericServiceBeanRemote<Test, String> getService() {
        return service;
    }

    public List<Test> getTests() {
        return this.service.getAll();
    }

    @Override
    public String delete(Test t) {
        try {
            this.service.deleteOne(t);
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
        this.entity = new Test();
    }

    @Override
    public void setEntity(Test entity) {
        super.setEntity(entity);
        this.disableEdit = false;
        this.disableSave = true;
    }

    @Override
    public LazyDataModel<Test> getModel() {
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

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public MarathonEquipe getMarathonEquipe() {
        return marathonEquipe;
    }

    public void setMarathonEquipe(MarathonEquipe marathonEquipe) {
        this.marathonEquipe = marathonEquipe;
    }

    public MarathonEquipeServiceBeanRemote getMarathonEquipeService() {
        return marathonEquipeService;
    }

    public void setMarathonEquipeService(MarathonEquipeServiceBeanRemote marathonEquipeService) {
        this.marathonEquipeService = marathonEquipeService;
    }

    public UserServiceBeanRemote getUserService() {
        return userService;
    }

    public void setUserService(UserServiceBeanRemote userService) {
        this.userService = userService;
    }

    public MarathonBean getMarathonBean() {
        return marathonBean;
    }

    public void setMarathonBean(MarathonBean marathonBean) {
        this.marathonBean = marathonBean;
    }

}
