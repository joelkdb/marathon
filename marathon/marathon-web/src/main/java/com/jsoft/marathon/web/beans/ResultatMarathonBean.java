/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.web.beans;

import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.Marathon;
import com.jsoft.marathon.service.CategorieEquipeServiceBeanRemote;
import com.jsoft.marathon.service.EquipeServiceBeanRemote;
import com.jsoft.marathon.service.MarathonServiceBeanRemote;
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
public class ResultatMarathonBean implements Serializable {

    @EJB
    private EquipeServiceBeanRemote equipeService;
    @EJB
    private MarathonServiceBeanRemote marathonService;
    @EJB
    private CategorieEquipeServiceBeanRemote categorieEquipeService;

    @ManagedProperty(value = "#{chartsBean}")
    private ChartsBean chartsBean;

    private Marathon marathon;

    private String[] styles;
    private List<CategorieEquipe> categorieEquipeList;
    private List<HorizontalBarChartModel> chartModelList;

    @PostConstruct
    public void init() {
        try {
            setMarathon(getMarathonService().findCurrentMarathon());
            setCategorieEquipeList(getCategorieEquipeService().getAll());
            setChartModelList(new ArrayList<>());
            setStyles(new String[getCategorieEquipeList().size()]);
            CategorieEquipe categorieEquipe;
            Map<Equipe, Long> equipesClassement;
            for (int i = 0, testListSize = getCategorieEquipeList().size(), nbEquipes;
                    i < testListSize;
                    i++) {
                categorieEquipe = getCategorieEquipeList().get(i);
                equipesClassement = getEquipeService().findEquipesClassement(
                        getMarathon(),
                        categorieEquipe
                );
                nbEquipes = equipesClassement.size();
                getChartModelList().add(
                        getChartsBean().createModel(
                                categorieEquipe,
                                equipesClassement,
                                getMarathonService().calculateTotalPoints(
                                        getMarathon(),
                                        categorieEquipe
                                )
                        )
                );
                getStyles()[i] = getChartsBean().createStyle(nbEquipes);
            }
        } catch (Exception ex) {
            Logger.getLogger(ResultatMarathonBean.class.getName()).log(Level.SEVERE, null, ex);
            MarathonUtils.getValueFromBundle("MSG_ERROR_DB_LOAD", MarathonConstants.BUNDLE_NAME);
        }
    }

    public EquipeServiceBeanRemote getEquipeService() {
        return equipeService;
    }

    public MarathonServiceBeanRemote getMarathonService() {
        return marathonService;
    }

    public CategorieEquipeServiceBeanRemote getCategorieEquipeService() {
        return categorieEquipeService;
    }

    public ChartsBean getChartsBean() {
        return chartsBean;
    }

    public Marathon getMarathon() {
        return marathon;
    }

    public String[] getStyles() {
        return styles;
    }

    public List<CategorieEquipe> getCategorieEquipeList() {
        return categorieEquipeList;
    }

    public List<HorizontalBarChartModel> getChartModelList() {
        return chartModelList;
    }

    public void setChartsBean(ChartsBean chartsBean) {
        this.chartsBean = chartsBean;
    }

    public void setMarathon(Marathon marathon) {
        this.marathon = marathon;
    }

    public void setStyles(String[] styles) {
        this.styles = styles;
    }

    public void setCategorieEquipeList(List<CategorieEquipe> categorieEquipeList) {
        this.categorieEquipeList = categorieEquipeList;
    }

    public void setChartModelList(List<HorizontalBarChartModel> chartModelList) {
        this.chartModelList = chartModelList;
    }

}
