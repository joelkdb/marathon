/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.web.valdations;

import com.webapp.commons.core.service.UserServiceBeanRemote;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import com.webapp.commons.core.entities.User;


/**
 *
 * @author Shell
 */
@FacesValidator("userNameValidator")
//@ManagedBean
//@RequestScoped
public class UserNameValidator implements Validator {

//    @EJB
    private UserServiceBeanRemote userServiceBean;
    private User user;

    public UserNameValidator(UserServiceBeanRemote userService, User user) {
        this.userServiceBean = userService;
        this.user= user;
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (userServiceBean.exists(user.getId(),String.valueOf(value))) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Ce nom d'utilisateur est déja pris", null));
        }
    }

}
