/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.web.beans;

import com.webapp.commons.core.service.GenericServiceBeanRemote;
import com.webapp.commons.core.service.RoleServiceBeanRemote;
import com.webapp.commons.core.utils.CoreConstants;
import com.webapp.commons.core.web.valdations.UserNameValidator;
import com.webapp.commons.core.entities.Role;
import com.webapp.commons.core.entities.User;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author komilo
 */
@ManagedBean
@ViewScoped
public class UserBean extends GenericBean<User, Long> {

    @EJB
    private RoleServiceBeanRemote roleService;

    private List<Role> roles;

    private String lastPassword = null;
    
    private String firstRole;

    public UserBean() {
        super();
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new User();
        this.firstRole = this.roleService.getAll(getCurrentUser()).get(0).getLabel();
//        this.entity.getRoles().size();
        this.roles = this.roleService.getAll();
    }

    @Override
    public void initUpdate() {
        super.initUpdate();
        this.entity.setPassword("");
    }

    public String updateByUser() {
        try {
            if (!userService.saltPassword(lastPassword).equals(userService.getOne(this.entity.getId()).getPassword())) {
                Messages.addGlobalError("ERREUR : Votre ancien mot de passe est incorrect", null);
                return "";
            }
        } catch (Exception e) {
            Messages.addGlobalError("ERREUR : " + e.getMessage(), null);
        }
        super.update(); //To change body of generated methods, choose Tools | Templates.
        initEntity();
        return "";
    }

    public User getCurrentUser() {
        return this.userService.getCurrent();
    }

    public UserNameValidator getUserNameValidator() {
        return new UserNameValidator(userService, this.entity);
    }

    @Override
    public GenericServiceBeanRemote getService() {
        return this.userService;
    }

    public List<Role> getRoles(User user) {
        return this.roleService.getAll(user);
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public String getFirstRole() {
        return firstRole;
    }

    public void setFirstRole(String firstRole) {
        this.firstRole = firstRole;
    }

    public String getLastPassword() {
        return lastPassword;
    }

//    @Override
//    public String delete() {
//        System.out.println("-- Call to delete()!");
//        return "list?faces-redirect=true";
//    }
    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(CoreConstants.PERM_SECURITY_USER_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(CoreConstants.PERM_SECURITY_USER_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(CoreConstants.PERM_SECURITY_USER_DELETE);
    }

    @Override
    public void initAdd() {
        
    }
}
