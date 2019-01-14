/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.web.listner;

import com.webapp.commons.core.service.ConfigServiceBeanRemote;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author komilo
 */
@WebListener
public class MSCoreServletListener implements ServletContextListener {

    @EJB
    private ConfigServiceBeanRemote configService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.configService.initConfig();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
