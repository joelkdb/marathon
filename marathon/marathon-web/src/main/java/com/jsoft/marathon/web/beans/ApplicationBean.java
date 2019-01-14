/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.web.beans;

import com.jsoft.marathon.service.EquipeServiceBeanRemote;
import com.jsoft.marathon.utils.MarathonConstants;
import com.webapp.commons.core.service.UserServiceBeanRemote;
import com.webapp.commons.core.utils.CoreConstants;
import com.webapp.commons.core.web.beans.BaseApplicationBean;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author komilo
 */
@ManagedBean
@ApplicationScoped
public class ApplicationBean extends BaseApplicationBean {

    @EJB
    private UserServiceBeanRemote userService;

    @EJB
    private EquipeServiceBeanRemote teamService;
    
    public boolean canAccessAdmin() {
        return this.userService.isPermitted(CoreConstants.PERM_SECURITY_ALL);
    }

    public boolean canAccessEquipe() {
        return this.userService.isPermitted(MarathonConstants.PERMS_CONCOURIR_EQUIPE_PLAY);
    }

    public boolean canAccessSpectator() {
        return this.userService.isPermitted(MarathonConstants.PERMS_VISUALISER_ALL);
    }

    public String getTeamCategory() {
        return this.teamService.getCategoryOfCurrentTeam();
    }

    @Override
    public String getApplicationName() {
        return "Marathon";
    }

    @Override
    public String getApplicationDisplayName() {
        return null;
    }

    @Override
    public String getApplicationSlogan() {
        return null;
    }

    @Override
    public String getMenuFilePath() {
        return null;
    }

    @Override
    public String getSecondaryMenuFilePath() {
        return null;
    }

}
