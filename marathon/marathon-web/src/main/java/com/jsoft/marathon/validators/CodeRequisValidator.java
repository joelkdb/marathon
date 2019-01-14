/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.validators;

import com.jsoft.marathon.utils.MarathonConstants;
import com.jsoft.marathon.utils.MarathonUtils;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author joelkdb
 */
@FacesValidator(value = "codeRequisValid")
public class CodeRequisValidator implements Validator{

    public CodeRequisValidator() {
        ELContext elc = FacesContext.getCurrentInstance().getELContext();
    }

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
      String val = String.valueOf(value).trim();
      if(val.isEmpty() || val ==null) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    MarathonUtils.getValueFromBundle("TESTEXERCICE.CODEREQUIS",
                    MarathonConstants.BUNDLE_NAME),
                    null
            ));
        }else if(val.length() < 50) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    MarathonUtils.getValueFromBundle("TESTEXERCICE.CODEINVALIDE",
                    MarathonConstants.BUNDLE_NAME),
                    null
            ));
        }
    }
    
}
