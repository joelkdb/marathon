/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.web.beans;

import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.Test;
import com.jsoft.marathon.exception.NoCurrentTestsException;
import com.jsoft.marathon.exception.NoLastTestsException;
import com.jsoft.marathon.service.EquipeServiceBeanRemote;
import com.jsoft.marathon.service.TestServiceBeanRemote;
import com.jsoft.marathon.utils.MarathonConstants;
import com.jsoft.marathon.utils.MarathonUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 *
 * @author joelkdb
 */
@ViewScoped
@ManagedBean
public class ResultatTestBean implements Serializable {

    @EJB
    private EquipeServiceBeanRemote equipeService;
    @EJB
    private TestServiceBeanRemote testService;

    @ManagedProperty(value = "#{chartsBean}")
    private ChartsBean chartsBean;

    private String[] styles;
    private List<Test> testList;
    private List<HorizontalBarChartModel> chartModelList;

    @PostConstruct
    public void init() {
        System.out.println("RENTRER 1");
        try {
            setTestList(getTestService().findCurrentTests());
            System.out.println("RENTRER 2");
        } catch (NoCurrentTestsException ex) {
            try {
                System.out.println("RENTRER 3");
                setTestList(getTestService().findLastTests());
            } catch (NoLastTestsException ex1) {
                MarathonUtils.getValueFromBundle("MSG_NO_LAST_TEST", MarathonConstants.BUNDLE_NAME);
                return;
            } catch (Exception ex1) {
                Logger.getLogger(ResultatTestBean.class.getName()).log(Level.SEVERE, null, ex1);
                MarathonUtils.getValueFromBundle("MSG_ERROR_DB_LOAD", MarathonConstants.BUNDLE_NAME);
                System.out.println("Erreur DB_LOAD 1");
            }
        } catch (Exception ex) {
            Logger.getLogger(ResultatTestBean.class.getName()).log(Level.SEVERE, null, ex);
            MarathonUtils.getValueFromBundle("MSG_ERROR_DB_LOAD", MarathonConstants.BUNDLE_NAME);
            System.out.println("Erreur DB_LOAD 2");
        }
        try {
            setChartModelList(new ArrayList<>());
            setStyles(new String[getTestList().size()]);
            Test test;
            Map<Equipe, Long> equipesClassement;
            for (int i = 0, testListSize = testList.size(), nbEquipes; i < testListSize; i++) {
                test = testList.get(i);
                equipesClassement = getEquipeService().findEquipesClassement(test);
                nbEquipes = equipesClassement.size();
                getChartModelList().add(
                        getChartsBean().createModel(
                                test.getCategorieEquipe(),
                                equipesClassement,
                                getTestService().calculateTotalPoints(test)
                        )
                );
                getStyles()[i] = getChartsBean().createStyle(nbEquipes);
            }
        } catch (Exception ex) {
            Logger.getLogger(ResultatTestBean.class.getName()).log(Level.SEVERE, null, ex);
            MarathonUtils.getValueFromBundle("MSG_ERROR_DB_LOAD", MarathonConstants.BUNDLE_NAME);
            System.out.println("Erreur DB_LOAD 1");
        }
    }

    public EquipeServiceBeanRemote getEquipeService() {
        return equipeService;
    }

    public TestServiceBeanRemote getTestService() {
        return testService;
    }

    public ChartsBean getChartsBean() {
        return chartsBean;
    }

    public String[] getStyles() {
        return styles;
    }

    public List<Test> getTestList() {
        return testList;
    }

    public List<HorizontalBarChartModel> getChartModelList() {
        return chartModelList;
    }

    public void setChartsBean(ChartsBean chartsBean) {
        this.chartsBean = chartsBean;
    }

    public void setStyles(String[] styles) {
        this.styles = styles;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public void setChartModelList(List<HorizontalBarChartModel> chartModelList) {
        this.chartModelList = chartModelList;
    }

}
