/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.web.beans;

import com.webapp.commons.core.entities.User;
import com.webapp.commons.core.service.UserServiceBeanRemote;
import com.webapp.commons.core.utils.CoreConstants;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author komilo
 */
public abstract class BaseApplicationBean implements Serializable {

    @EJB
    private UserServiceBeanRemote userService;

    public abstract String getApplicationName();

    public abstract String getApplicationDisplayName();

    public abstract String getApplicationSlogan();

    public abstract String getMenuFilePath();

    public abstract String getSecondaryMenuFilePath();

    public User getCurrentUser() {
        return this.userService.getCurrent();
    }

    public String logout() {
        this.userService.logout();
        return "/";
    }

    // Menu permissions
    public boolean canAccessUsers() {
        return this.userService.isPermitted(CoreConstants.PERM_SECURITY_USER_LIST);
    }

    public boolean canAccessRoles() {
        return this.userService.isPermitted(CoreConstants.PERM_SECURITY_ROLE_LIST);
    }

    public boolean canAccessLogs() {
        return this.userService.isPermitted(CoreConstants.PERM_LOG_LIST);
    }

    public boolean canAccessBackupBackup() {
        return this.userService.isPermitted(CoreConstants.PERM_BACKUP_BACKUP);
    }

    public boolean canAccessBackupRestore() {
        return this.userService.isPermitted(CoreConstants.PERM_BACKUP_RESTORE);
    }

    public boolean canAccessBackupConfigure() {
        return this.userService.isPermitted(CoreConstants.PERM_BACKUP_CONFIGURE);
    }

    public boolean canAccessSchedulerConsult() {
        return this.userService.isPermitted(CoreConstants.PERM_SCHEDULER_CONSULT);
    }

    public boolean canAccessSchedulerExecute() {
        return this.userService.isPermitted(CoreConstants.PERM_SCHEDULER_EXECUTE);
    }

    public boolean canAccessSchedulerPlanify() {
        return this.userService.isPermitted(CoreConstants.PERM_SCHEDULER_PLANIFY);
    }

    public boolean canAccessSchedulerView() {
        return this.userService.isPermitted(CoreConstants.PERM_SCHEDULER_VIEW);
    }
}
