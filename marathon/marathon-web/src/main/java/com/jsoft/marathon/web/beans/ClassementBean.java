/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.web.beans;

import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.Test;
import com.jsoft.marathon.service.EquipeServiceBeanRemote;
import com.jsoft.marathon.service.TestServiceBeanRemote;
import com.jsoft.marathon.utils.MarathonConstants;
import com.jsoft.marathon.utils.MarathonUtils;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 *
 * @author joelkdb
 */
@ManagedBean(name = "classementBean")
@ViewScoped
public class ClassementBean implements Serializable {

    @EJB
    private EquipeServiceBeanRemote equipeService;

    @EJB
    private TestServiceBeanRemote testService;

    @ManagedProperty(value = "#{testBean}")
    private TestBean testBean;
    @ManagedProperty(value = "#{chartsBean}")
    private ChartsBean chartsBean;

    private HorizontalBarChartModel model;
    private String style;

    @PostConstruct
    public void init() {
        try {
            Test test = getTestBean().getTest();
            Map<Equipe, Long> equipesClassement = this.equipeService.findEquipesClassement(test);
            setStyle(getChartsBean().createStyle(equipesClassement.size()));
            setModel(getChartsBean().createModel(
                    test.getCategorieEquipe(),
                    equipesClassement,
                    this.testService.calculateTotalPoints(test)));
        } catch (Exception ex) {
            Logger.getLogger(ClassementBean.class.getName()).log(Level.SEVERE, null, ex);
            MarathonUtils.getValueFromBundle("MSG_ERROR_ADD", MarathonConstants.BUNDLE_NAME);
        }
    }

    public EquipeServiceBeanRemote getEquipeService() {
        return equipeService;
    }

    public TestServiceBeanRemote getTestService() {
        return testService;
    }

    public TestBean getTestBean() {
        return testBean;
    }

    public ChartsBean getChartsBean() {
        return chartsBean;
    }

    public void setTestBean(TestBean testBean) {
        this.testBean = testBean;
    }

    public void setChartsBean(ChartsBean chartsBean) {
        this.chartsBean = chartsBean;
    }

    public HorizontalBarChartModel getModel() {
        return model;
    }

    public void setModel(HorizontalBarChartModel model) {
        this.model = model;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

}
