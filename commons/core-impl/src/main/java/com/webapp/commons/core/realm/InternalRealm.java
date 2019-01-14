/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.realm;


import com.webapp.commons.core.entities.Permission;
import com.webapp.commons.core.entities.Role;
import com.webapp.commons.core.entities.User;
import com.webapp.commons.core.service.UserServiceBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 * @author persistence
 */
public class InternalRealm extends AuthorizingRealm {

    private UserServiceBeanRemote userService = null;
    
    private String lookupName;

    public InternalRealm() {
        this.setName("internalRealm");
    }
    
    private synchronized UserServiceBeanRemote getUserService() {
        if (this.userService == null) {
            try {
                Context context = new InitialContext();
                this.userService = (UserServiceBeanRemote) context.lookup(this.lookupName);
            } catch (NamingException ex) {
                Logger.getLogger(InternalRealm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.userService;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) at;
        User user = this.getUserService().findByUsername(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        } else {
            return null;
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        //Le parcours de la collection de principals pr avoir l'id de l'utilisateur courant
        //On préfère récuperer le Username
        String username = (String) pc.fromRealm(this.getName()).iterator().next();
        User user = this.getUserService().findByUsername(username);
        if (user == null) {
            return null;
        } else {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for (Role role : this.getUserService().findRolesForUser(user.getId())) {
                info.addRole(role.getLabel());
                //liste des permissions pour cet utilisateur
                for (Permission permission : this.getUserService().findPermissionsForRole(role.getId())) {
                    info.addStringPermission(permission.getCode());
                }
            }
            return info;
        }
    }
    
    public void setLookupName(String lookupName) {
        this.lookupName = lookupName;
    }

}
