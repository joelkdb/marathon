/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.web.beans;

import com.webapp.commons.core.service.UserServiceBeanRemote;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

/**
 *
 * @author komilo
 */
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {
    
    public static final String HOME_URL = "index.xhtml";
    
    @EJB
    private UserServiceBeanRemote userService;
    
    private String username;
    
    private String password;
    
    private boolean rememberMe;

    public LoginBean() {
    }
    
    public void login() {
        if (this.userService.login(this.username, this.password, this.rememberMe)) {
            try {
                SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
                Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : HOME_URL);
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Messages.addGlobalError("Login ou mot de passe incorrect.");
        }
    }
    
    public void logout() throws IOException {
        this.userService.logout();
        Faces.invalidateSession();
        Faces.redirect(HOME_URL);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
