/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.utils;

/**
 *
 * @author komilo
 */
public class CoreConstants {

    // Permissions
    public static final String PERM_ALL = "*";
    public static final String PERM_SECURITY_ALL = "security:*";
    public static final String PERM_SECURITY_USER_ALL = "security:user:*";
    public static final String PERM_SECURITY_USER_ADD = "security:user:add";
    public static final String PERM_SECURITY_USER_EDIT = "security:user:edit";
    public static final String PERM_SECURITY_USER_VIEW = "security:user:view";
    public static final String PERM_SECURITY_USER_LIST = "security:user:list";
    public static final String PERM_SECURITY_USER_DELETE = "security:user:delete";
    public static final String PERM_SECURITY_ROLE_ALL = "security:role:*";
    public static final String PERM_SECURITY_ROLE_CHANGE_ANY_PASSWORD = "security:role:changeanypassword";
    public static final String PERM_SECURITY_ROLE_CHANGE_OWN_PASSWORD = "security:role:changeownpassword";
    public static final String PERM_SECURITY_ROLE_ADD = "security:role:add";
    public static final String PERM_SECURITY_ROLE_EDIT = "security:role:edit";
    public static final String PERM_SECURITY_ROLE_VIEW = "security:role:view";
    public static final String PERM_SECURITY_ROLE_LIST = "security:role:list";
    public static final String PERM_SECURITY_ROLE_DELETE = "security:role:delete";
    public static final String PERM_LOG_ALL = "log:*";
    public static final String PERM_LOG_VIEW = "log:view";
    public static final String PERM_LOG_LIST = "log:list";

    // Catégories de logs
    public static final String LOG_CORE = "core";
    public static final String LOG_SECURITY = "security";
    public static final String LOG_SCHEDULER = "scheduler";
    public static final String LOG_BACKUP = "backup";

    //Sauvegarde et restauration
    public static final String PERM_BACKUP_BACKUP = "backup.backup";
    public static final String PERM_BACKUP_RESTORE = "backup.restore";
    public static final String PERM_BACKUP_CONFIGURE = "backup.configure";

    // Permission sur le scheduler
    public static final String PERM_SCHEDULER_VIEW = "scheduler:view";
    public static final String PERM_SCHEDULER_EXECUTE = "scheduler:execute";
    public static final String PERM_SCHEDULER_PLANIFY = "scheduler:planify";
    public static final String PERM_SCHEDULER_CONSULT = "scheduler:consult";
    
    // REST HTTP header names
    public static final String REST_HTTP_HEADER_SERVICE_KEY = "service_key";
    public static final String REST_HTTP_HEADER_AUTH_TOKEN = "auth_token";
}
