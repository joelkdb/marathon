/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.web.beans;

import com.jsoft.marathon.entities.Parametre;
import com.jsoft.marathon.service.ParametreServiceBeanRemote;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import com.webapp.commons.core.web.beans.GenericBean;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author joelkdb
 */
@ManagedBean
@SessionScoped
public class ParametreBean extends GenericBean<Parametre, String>{

    @EJB
    private ParametreServiceBeanRemote service;
    
    @Override
    public GenericServiceBeanRemote<Parametre, String> getService() {
        return this.service;
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
        this.entity = new Parametre();
    }
    
    /**
     * Permet de récupérer un texte spliter par des retours à la ligne
     * @param texte
     * @return une liste de lines
     */
    public List<String> getLines(String texte) {
        try {
            return this.service.getLines(texte);
        } catch (Exception ex) {
            Logger.getLogger(ParametreBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
