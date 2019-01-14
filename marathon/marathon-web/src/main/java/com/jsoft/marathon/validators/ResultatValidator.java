/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.validators;

import com.jsoft.marathon.entities.EquipeExercice;
import com.jsoft.marathon.entities.Exercice;
import com.jsoft.marathon.service.EquipeExerciceServiceBeanRemote;
import com.jsoft.marathon.service.ExerciceServiceBeanRemote;
import com.jsoft.marathon.utils.MarathonConstants;
import com.jsoft.marathon.utils.MarathonUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.spi.CDI;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.primefaces.component.inputtext.InputText;

/**
 *
 * @author joelkdb
 */
@FacesValidator(value = "resultatValid")
public class ResultatValidator implements Validator {

    //EJB is not supported here
    //@EJB
    private ExerciceServiceBeanRemote service;
    //EJB is not supported here
    //@EJB
    private EquipeExerciceServiceBeanRemote ees;
//java:global/marathon-web/EquipeServiceBean!com.jsoft.marathon.service.EquipeServiceBeanRemote, java:global/marathon-web/EquipeServiceBean

    public ResultatValidator() throws NamingException {
        InitialContext context = new InitialContext();
        this.service = (ExerciceServiceBeanRemote) context.lookup("java:global/marathon-web/ExerciceServiceBean");
        this.ees = (EquipeExerciceServiceBeanRemote) context.lookup("java:global/marathon-web/EquipeExerciceServiceBean");
    }

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
        String submitted = String.valueOf(value).trim();
        String numExo = (((InputText) uic).getLabel());
        System.out.println(numExo);
        Exercice e;
        Boolean isSolutionCorrect = false;
        try {
            e = this.service.getOne(numExo);
            EquipeExercice equipeExercice = new EquipeExercice();
            equipeExercice.setExercice(e);
            equipeExercice.setSolutionEnvoye(submitted);
            isSolutionCorrect = this.ees.isSolutionCorrect(equipeExercice);
        } catch (Exception ex) {
            Logger.getLogger(ResultatValidator.class.getName()).log(Level.SEVERE, null, ex);
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    null
            ));
        }
        if (submitted == null || submitted.isEmpty() || !isSolutionCorrect) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    MarathonUtils.getValueFromBundle("TESTEXERCICE.RESULTATINCORECT",
                            MarathonConstants.BUNDLE_NAME),
                    null
            ));
        }
    }

    public ExerciceServiceBeanRemote getService() {
        return service;
    }

    public EquipeExerciceServiceBeanRemote getEes() {
        return ees;
    }

}
