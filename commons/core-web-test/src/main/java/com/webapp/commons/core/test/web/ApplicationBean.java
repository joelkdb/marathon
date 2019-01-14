/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.test.web;

import com.webapp.commons.core.web.beans.BaseApplicationBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author komilo
 */
@ManagedBean
@ApplicationScoped
public class ApplicationBean extends BaseApplicationBean {
    
    public static final String MENU_FILE_PATH = "/test/templates/menu.xhtml";
    public static final String SECONDARY_MENU_FILE_PATH = "/test/templates/secondary-menu.xhtml";

    @Override
    public String getApplicationName() {
        return "core-web-test";
    }

    @Override
    public String getApplicationDisplayName() {
        return "Commons Web Test";
    }

    @Override
    public String getApplicationSlogan() {
        return "Votre Slogan!";
    }

    @Override
    public String getMenuFilePath() {
        return MENU_FILE_PATH;
    }

    @Override
    public String getSecondaryMenuFilePath() {
        return SECONDARY_MENU_FILE_PATH;
    }
    
}
