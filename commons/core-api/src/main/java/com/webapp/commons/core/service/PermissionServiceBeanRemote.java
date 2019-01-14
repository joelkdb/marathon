/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import java.util.List;
import javax.ejb.Remote;
import com.webapp.commons.core.entities.Permission;
import com.webapp.commons.core.entities.PermissionCategory;
import com.webapp.commons.core.utils.SortParams;

/**
 *
 * @author persistence
 */
@Remote
public interface PermissionServiceBeanRemote extends GenericServiceBeanRemote<Permission, String> {

    /**
     * Renvoie la liste des permissions d'une catégorie de permission.
     * @param category La catégorie.
     * @return La liste.
     */
    List<Permission> getAll(PermissionCategory category);
    
    /**
     * Renvoie la liste des permissions d'une catégorie de permission triée selon les 
     * SortParam.
     * @param category La catégorie.
     * @param sortParams Les paramètres de tri.
     * @return La liste.
     */
    List<Permission> getAll(PermissionCategory category, SortParams sortParams);
}
