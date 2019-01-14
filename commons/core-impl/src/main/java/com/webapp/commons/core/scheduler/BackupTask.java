/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.scheduler;

import com.webapp.commons.core.api.Task;
import com.webapp.commons.core.service.BackupServiceBeanRemote;
import com.webapp.commons.core.utils.BackupConfiguration;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author El Phenox
 */
@Stateless
public class BackupTask implements Task{

    @EJB
    private BackupServiceBeanRemote backupService;
    BackupConfiguration configuration;
    @Override
    public String getName() {
        return "Backup";
    }

    @Override
    public String getDescription() {
      return  "Planifié vos sauvegarde afin de se proteger contre d'eventuels sinistre";
    }

    @Override
    public String getGroup() {
       return "TaskGroup";
    }

    @Override
    public void run() {
        backupService.backup(configuration);
    }
    
}
