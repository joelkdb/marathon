/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.web.beans;

import com.webapp.commons.core.entities.Permission;
import com.webapp.commons.core.entities.PermissionCategory;
import com.webapp.commons.core.entities.Role;
import com.webapp.commons.core.entities.User;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import com.webapp.commons.core.service.PermissionCategoryServiceBeanRemote;
import com.webapp.commons.core.service.PermissionServiceBeanRemote;
import com.webapp.commons.core.service.RoleServiceBeanRemote;
import com.webapp.commons.core.utils.CoreConstants;
import com.webapp.commons.core.utils.SortDirection;
import com.webapp.commons.core.utils.SortParams;
import com.webapp.commons.core.web.filters.RoleFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author komilo
 */
@ManagedBean
@ViewScoped
public class RoleBean extends GenericBean<Role, Long> implements Converter {

    @EJB
    private RoleServiceBeanRemote roleService;

    @EJB
    private PermissionCategoryServiceBeanRemote permissionCategoryService;

    @EJB
    private PermissionServiceBeanRemote permissionService;

    private final Map<String, Boolean> checkMap = new HashMap<>();
    private RoleFilter roleFilter;

    public RoleBean() {
        super();
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Role();
        this.roleFilter = new RoleFilter();
    }

    @Override
    public void initEntity() {
        super.initEntity(); //To change body of generated methods, choose Tools | Templates.
//        this.entity.setPermissions(permissionService.getAll(SortParams.from("code", true), FilterParams.from("", this.entity)));
    }

    @Override
    public void initUpdate() {
        super.initUpdate();
        for (Permission permission : this.entity.getPermissions()) {
            this.checkMap.put(permission.getCode(), Boolean.TRUE);
        }
    }

    @Override
    public GenericServiceBeanRemote<Role, Long> getService() {
        return this.roleService;
    }

    public List<Role> completeRoles(String s) {
        s = s == null ? "" : s;
        roleFilter.setMaxResult(20);
        this.roleFilter.setLabel(s);
        return this.roleService.getAll(this.roleFilter);
    }

    public List<PermissionCategory> getPermissionCategories() {
        return this.permissionCategoryService.getAll(SortParams.from("label", SortDirection.ASCENDING));
    }

    public List<Permission> getPermissions(PermissionCategory category) {
        return this.permissionService.getAll(category);
    }

    public List<Role> getRoles(User user) {
        user.setRoles(this.roleService.getAll(user));
        return user.getRoles();
    }

    @Override
    public void beforeAdd() {
        super.beforeAdd();
        this.updateSelectedPermissions();
    }

    @Override
    public void beforeUpdate() {
        super.beforeUpdate();
        this.updateSelectedPermissions();
    }

    private void updateSelectedPermissions() {
        for (Map.Entry<String, Boolean> entry : this.checkMap.entrySet()) {
            if (entry.getValue()) {
                this.entity.getPermissions().add(this.permissionService.getOne(entry.getKey()));
            }
        }
    }

    @Override
    public boolean canAdd() {
        return this.userService.isPermitted(CoreConstants.PERM_SECURITY_ROLE_ADD);
    }

    @Override
    public boolean canUpdate() {
        return this.userService.isPermitted(CoreConstants.PERM_SECURITY_ROLE_EDIT);
    }

    @Override
    public boolean canDelete() {
        return this.userService.isPermitted(CoreConstants.PERM_SECURITY_ROLE_DELETE);
    }

    public Map<String, Boolean> getCheckMap() {
        return checkMap;
    }

    public RoleServiceBeanRemote getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleServiceBeanRemote roleService) {
        this.roleService = roleService;
    }

    public RoleFilter getRoleFilter() {
        return roleFilter;
    }

    public void setRoleFilter(RoleFilter roleFilter) {
        this.roleFilter = roleFilter;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return roleService.getOne(Long.valueOf(value));
        } catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            return ((Role) value).getId().toString();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void initAdd() {
        
    }
}
