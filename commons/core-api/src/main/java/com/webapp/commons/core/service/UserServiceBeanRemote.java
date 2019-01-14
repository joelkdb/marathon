/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import java.util.List;
import com.webapp.commons.core.entities.User;
import javax.ejb.Remote;
import com.webapp.commons.core.entities.Permission;
import com.webapp.commons.core.entities.Role;

/** 
 *
 * @author persistence
 */
@Remote
public interface UserServiceBeanRemote extends GenericServiceBeanRemote<User, Long> {

    User findByUsername(String username);

    List<Role> findRolesForUser(Long id);

    List<Permission> findPermissionsForRole(Long roleId);

    User getCurrent();

    String getClientIPAdress();

    boolean login(User user);

    public boolean exists(Long id, String userName);

    public String saltPassword(String password);

    boolean login(String username, String password);

    boolean login(String username, String password, boolean rememberMe);

    void logout();

    void logout(String token);

    boolean isPermitted(String permissionCode);

    boolean isPermitted(Permission permission);
}
