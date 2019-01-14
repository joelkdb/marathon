/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.webapp.commons.core.api.Task;
import com.webapp.commons.core.scheduler.Structure;

/**
 *
 * @author El Phenox
 */
@ManagedBean
@ViewScoped
public class StructureBean implements Serializable {

    private List<Structure> data = new ArrayList<>();

    private boolean renderedPanel;
    private boolean renderedPanel1;
    private boolean renderedPanel2;
    private boolean renderedPanel3;
    private String value;

    private boolean radiocheck;
    private boolean radiocheck1;
    private boolean radiocheck2;
    private boolean radiocheck3;
    private boolean radiocheck4;
    private boolean radiocheck5;
    private boolean radiocheck6;
    private boolean radiocheck7;
    private String radio;

    /**
     * Creates a new instance of StructureBean
     */
    public StructureBean() {
        renderedPanel = false;
        renderedPanel1 = false;
        renderedPanel2 = false;
        renderedPanel3 = false;
        radiocheck = false;
        radiocheck1 = false;
        radiocheck2 = false;
        radiocheck3 = false;
        radiocheck4 = false;
        radiocheck5 = false;
        radiocheck6 = false;
        radiocheck7 = false;
        value = null;
        radio = null;
    }

    public List<Structure> getData() {
        afficher();
        return data;
    }

    public void setData(List<Structure> data) {
        this.data = data;
    }
    
    //Affichage des jobs
    public void afficher() {
        data = new ArrayList<>();
        ServiceLoader<Task> loader = ServiceLoader.load(Task.class);
        for (Task implClass : loader) {
            Structure s = new Structure();
            s.setName(implClass.getClass().getSimpleName());
            s.setDescription(implClass.getDescription());
            data.add(s);
        }
    }

    //Liste deroulante
    public void listenerPanel() {
        switch (value) {

            case "aucun":
                renderedPanel = false;
                renderedPanel1 = false;
                renderedPanel2 = false;
                renderedPanel3 = false;
                break;
            case "quoti":
                renderedPanel = true;
                renderedPanel1 = false;
                renderedPanel2 = false;
                renderedPanel3 = false;
                break;
            case "hebdo":
                renderedPanel = false;
                renderedPanel1 = true;
                renderedPanel2 = false;
                renderedPanel3 = false;
                break;
            case "men":
                renderedPanel = false;
                renderedPanel1 = false;
                renderedPanel2 = true;
                renderedPanel3 = false;
                break;
            case "an":
                renderedPanel = false;
                renderedPanel1 = false;
                renderedPanel2 = false;
                renderedPanel3 = true;
                break;
        }

    }

    //Panels de repetition
    public void affich() {
        switch (radio) {
            case "toujours":
                radiocheck = false;
                radiocheck1 = false;
                break;
            case "fin":
                radiocheck = false;
                radiocheck1 = true;
                break;
            case "nbre":
                radiocheck = true;
                radiocheck1 = false;
                break;
        }
        radio = null;
    }

    public void affichHebdo() {
        switch (radio) {
            case "toujours":
                radiocheck2 = false;
                radiocheck3 = false;
                break;
            case "fin":
                radiocheck2 = false;
                radiocheck3 = true;
                break;
            case "nbre":
                radiocheck2 = true;
                radiocheck3 = false;
                break;
        }
        radio = null;
    }

    public void affichMois() {
        switch (radio) {
            case "toujours":
                radiocheck4 = false;
                radiocheck5 = false;
                break;
            case "fin":
                radiocheck4 = false;
                radiocheck5 = true;
                break;
            case "nbre":
                radiocheck4 = true;
                radiocheck5 = false;
                break;
        }
        radio = null;
    }

    public void affichAn() {
        switch (radio) {
            case "toujours":
                radiocheck6 = false;
                radiocheck7 = false;
                break;
            case "fin":
                radiocheck6 = false;
                radiocheck7 = true;
                break;
            case "nbre":
                radiocheck6 = true;
                radiocheck7 = false;
                break;
        }
        radio = null;
    }

    //Getters and Setters
    public boolean isRenderedPanel() {
        return renderedPanel;
    }

    public void setRenderedPanel(boolean renderedPanel) {
        this.renderedPanel = renderedPanel;
    }

    public boolean isRenderedPanel1() {
        return renderedPanel1;
    }

    public void setRenderedPanel1(boolean renderedPanel1) {
        this.renderedPanel1 = renderedPanel1;
    }

    public boolean isRenderedPanel2() {
        return renderedPanel2;
    }

    public void setRenderedPanel2(boolean renderedPanel2) {
        this.renderedPanel2 = renderedPanel2;
    }

    public boolean isRenderedPanel3() {
        return renderedPanel3;
    }

    public void setRenderedPanel3(boolean renderedPanel3) {
        this.renderedPanel3 = renderedPanel3;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRadiocheck() {
        return radiocheck;
    }

    public void setRadiocheck(boolean radiocheck) {
        this.radiocheck = radiocheck;
    }

    public boolean isRadiocheck1() {
        return radiocheck1;
    }

    public void setRadiocheck1(boolean radiocheck1) {
        this.radiocheck1 = radiocheck1;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public boolean isRadiocheck2() {
        return radiocheck2;
    }

    public void setRadiocheck2(boolean radiocheck2) {
        this.radiocheck2 = radiocheck2;
    }

    public boolean isRadiocheck3() {
        return radiocheck3;
    }

    public void setRadiocheck3(boolean radiocheck3) {
        this.radiocheck3 = radiocheck3;
    }

    public boolean isRadiocheck4() {
        return radiocheck4;
    }

    public void setRadiocheck4(boolean radiocheck4) {
        this.radiocheck4 = radiocheck4;
    }

    public boolean isRadiocheck5() {
        return radiocheck5;
    }

    public void setRadiocheck5(boolean radiocheck5) {
        this.radiocheck5 = radiocheck5;
    }

    public boolean isRadiocheck6() {
        return radiocheck6;
    }

    public void setRadiocheck6(boolean radiocheck6) {
        this.radiocheck6 = radiocheck6;
    }

    public boolean isRadiocheck7() {
        return radiocheck7;
    }

    public void setRadiocheck7(boolean radiocheck7) {
        this.radiocheck7 = radiocheck7;
    }

}
