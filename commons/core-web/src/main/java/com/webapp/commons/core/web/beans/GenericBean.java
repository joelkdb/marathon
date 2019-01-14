/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.web.beans;

import com.webapp.commons.core.entities.BaseEntity;
import com.webapp.commons.core.exception.BusinessException;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import com.webapp.commons.core.service.UserServiceBeanRemote;
import com.webapp.commons.core.web.models.GenericDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;

/**
 * Managed bean de base pour les opérations CRUD.
 *
 * @author USER
 * @param <E> classe entité
 * @param <ID> classe de la clé primaire de l'entité
 */
public abstract class GenericBean<E extends BaseEntity, ID extends Serializable>
        implements Serializable {

    private static final String LIST_OUTCOME = "list";
    private static final String EDIT_OUTCOME = "edit";

    @EJB
    protected UserServiceBeanRemote userService;

    protected E entity;

    protected ID entityId;

    protected LazyDataModel<E> model;

    protected final Logger logger;
    
    protected List<E> selected;

    public GenericBean() {
        this.logger = Logger.getLogger(this.getClass().getName());
        this.selected = new ArrayList<>();
    }

    public abstract GenericServiceBeanRemote<E, ID> getService();

    @PostConstruct
    public void init() {
        this.model = new GenericDataModel<>(this.getService());
    }

    public abstract boolean canAdd();

    public abstract boolean canUpdate();

    public abstract boolean canDelete();

    public LazyDataModel<E> getModel() {
        return this.model;
    }

    /**
     * Instanciation de <code>this.entity</code>.
     */
    public abstract void initAdd();
    
    public void initUpdate() {
        this.entity = this.getService().getOne(this.entityId);
    }

    public void initEntity() {
        if (this.entityId == null) {
            this.initAdd();
        } else {
            this.initUpdate();
        }
    }

    public void beforeAdd() {
    }

    public String add() {
        try {
            this.getService().addOne(this.entity);
            this.init();
            Messages.addFlashGlobalInfo("Ajout effectué avec succès.");
            return LIST_OUTCOME + "?faces-redirect=true";
        } catch (BusinessException ex) {
            Messages.addGlobalError(ex.getMessage());
            this.logger.log(Level.SEVERE, "Erreur à l'ajout de l'objet: {0}", this.entity);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Messages.addGlobalError("Une erreur est survenue lors de l'ajout.");
            this.logger.log(Level.SEVERE, "Erreur à l'ajout de l'objet: {0}", this.entity);
            ex.printStackTrace();
            return null;
        }
    }

    public void beforeUpdate() {
    }

    public String update() {
        try {
            this.entity = this.getService().updateOne(this.entity);
            Messages.addFlashGlobalInfo("Mise à jour effectuée avec succès.");
            return LIST_OUTCOME + "?faces-redirect=true";
        } catch (BusinessException ex) {
            Messages.addGlobalError(ex.getMessage());
            this.logger.log(Level.SEVERE, "Erreur à la mise à jour de l'objet: {0}", this.entity);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            Messages.addGlobalError("Une erreur est survenue lors de la mise à jour.");
            this.logger.log(Level.SEVERE, "Erreur à la mise à jour de l'objet: {0}", this.entity);
            ex.printStackTrace();
            return null;
        }
    }

    public void beforeDelete() {
    }

    public String delete(E e) {
        try {
            System.out.println("- deleting : " + e);
            this.getService().deleteOne(e);
            Messages.addFlashGlobalInfo("Suppression effectuée avec succès.");
        } catch (BusinessException ex) {
            Messages.addFlashGlobalError(ex.getMessage());
            this.logger.log(Level.SEVERE, "Erreur à la suppression de l'objet: {0}", e);
            ex.printStackTrace();
        } catch (Exception ex) {
            Messages.addFlashGlobalError("Une erreur est survenue lors de la suppression.");
            this.logger.log(Level.SEVERE, "Erreur à la suppression de l'objet: {0}", e);
            ex.printStackTrace();
        }
        return LIST_OUTCOME + "?faces-redirect=true";
    }

    public String delete() {
        return this.delete(this.entity);
//        System.out.println("-- Call to delete()!");
//        return LIST_OUTCOME + "?faces-redirect=true";
    }

    public String cancel() {
        return LIST_OUTCOME + "?faces-redirect=true";
    }

    public ID getId(E e) {
        return this.getService().getId(e);
    }

    public boolean isUpdating() {
        return this.entityId != null;
    }

    public E getEntity() {
        return this.entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public ID getEntityId() {
        return entityId;
    }

    public void setEntityId(ID entityId) {
        this.entityId = entityId;
    }

    public List<E> getSelected() {
        return selected;
    }

    public void setSelected(List<E> selected) {
        this.selected = selected;
    }
}
