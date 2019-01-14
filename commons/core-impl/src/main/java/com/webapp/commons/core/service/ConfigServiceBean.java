/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.dao.LogCategoryDAOBeanLocal;
import com.webapp.commons.core.dao.LogLevelDAOBeanLocal;
import com.webapp.commons.core.dao.PermissionCategoryDAOBeanLocal;
import com.webapp.commons.core.dao.PermissionDAOBeanLocal;
import com.webapp.commons.core.entities.LogCategory;
import com.webapp.commons.core.entities.LogLevel;
import com.webapp.commons.core.entities.Permission;
import com.webapp.commons.core.entities.PermissionCategory;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.ini4j.Ini;

/**
 *
 * @author komilo
 */
@Stateless
public class ConfigServiceBean implements ConfigServiceBeanRemote {

    public static final String CONFIG_FILE_NAME = "config.ini";
    public static final String CORE_CONFIG_FILE_NAME = "core-config.ini";

    @EJB
    private LogLevelDAOBeanLocal logLevelDAO;

    @EJB
    private LogCategoryDAOBeanLocal logCategoryDAO;

    @EJB
    private PermissionCategoryDAOBeanLocal permissionCategoryDAO;

    @EJB
    private PermissionDAOBeanLocal permissionDAO;
    
    private final Logger logger;

    public ConfigServiceBean() {
        this.logger = Logger.getLogger(ConfigServiceBean.class.getName());
    }

    @Override
    public void initConfig() {
        this.initConfig(CORE_CONFIG_FILE_NAME);
        this.initConfig(CONFIG_FILE_NAME);
    }

    private void initConfig(String configFile) {
        this.logger.log(Level.INFO, "Chargement de la configuration... fichier ''{0}''.", configFile);
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFile);
        if (stream == null) {
            this.logger.log(Level.WARNING, "Fichier ''{0}'' non trouvé! Chargement avorté.", configFile);
        } else {
            try {
                Ini ini = new Ini(stream);
                // init log
                this.initLog(ini.get("log"));
                // init perms
                this.initPermissions(ini.get("perms"));
            } catch (IOException ex) {
                this.logger.log(Level.WARNING, "Accès impossible au fichier ''{0}''! Chargement avorté.", configFile);
            }
        }
    }

    private void initLog(Ini.Section section) {
        if (section == null) {
            this.logger.warning("Section [log] non trouvée!");
        } else {
            this.logger.info("Section [log] trouvée.");
            this.initLogLevels(section.getChild("level"));
            this.initLogCategories(section.getChild("category"));
        }
    }

    private void initLogLevels(Ini.Section section) {
        if (section == null) {
            this.logger.warning("Section [log/level] non trouvée!");
        } else {
            this.logger.info("Section [log/level] trouvée.");
            
            for (String key : section.keySet()) {
                Integer id = Integer.valueOf(key);
                if (! this.logLevelDAO.exists(Integer.valueOf(key))) {
                    LogLevel level = new LogLevel(id, section.get(key));
                    this.logLevelDAO.addOne(level);
                    this.logger.log(Level.INFO, "Niveau de log ajouté: {0}.", level);
                }
            }
        }
    }

    private void initLogCategories(Ini.Section section) {
        if (section == null) {
            this.logger.warning("Section [log/category] non trouvée!");
        } else {
            this.logger.info("Section [log/category] trouvée.");
            
            for (String key : section.keySet()) {
                if (! this.logCategoryDAO.exists(key)) {
                    LogCategory category = new LogCategory(key, section.get(key));
                    this.logCategoryDAO.addOne(category);
                    this.logger.log(Level.INFO, "Catégorie de log ajoutée: {0}.", category);
                }
            }
        }
    }

    private void initPermissions(Ini.Section section) {
        if (section == null) {
            this.logger.warning("Section [perms] non trouvée!");
        } else {
            this.logger.info("Section [perms] trouvée.");
            
            for (String key : section.keySet()) {
                // Ajout de la catégorie de permission.
                if (! this.permissionCategoryDAO.exists(key)) {
                    PermissionCategory category = new PermissionCategory(key, section.get(key));
                    this.permissionCategoryDAO.addOne(category);
                    this.logger.log(Level.INFO, "Catégorie de permission ajoutée: {0}.", category);
                }
                
                // Permissions de la catégorie
                Ini.Section permsSection = section.getChild(key);
                if (permsSection == null) {
                    this.logger.log(Level.WARNING, "Section [perms/{0}] non trouvée!", key);
                } else {
                    PermissionCategory permCategory = this.permissionCategoryDAO.getOne(key);
                    for (String permKey : permsSection.keySet()) {
                        String permId = permKey.replace('.', ':');
                        if (! this.permissionDAO.exists(permId)) {
                            Permission permission = new Permission(permId, 
                                    permsSection.get(permKey), permCategory);
                            this.permissionDAO.addOne(permission);
                            this.logger.log(Level.INFO, "Permission ajoutée: {0}.", permission);
                        }
                    }
                }
            }
        }
    }
}
