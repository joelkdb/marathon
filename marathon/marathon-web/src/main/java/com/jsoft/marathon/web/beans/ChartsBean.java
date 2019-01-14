/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.web.beans;

import com.jsoft.marathon.entities.CategorieEquipe;
import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.utils.MarathonConstants;
import com.jsoft.marathon.utils.MarathonUtils;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 *
 * @author joelkdb
 */
@ManagedBean(name = "chartsBean")
@SessionScoped
public class ChartsBean implements Serializable {

    public String createStyle(int nbEquipes) {
        String style;
        if (nbEquipes > 10) {
            style = "height : " + (nbEquipes * 50) + "px";
        } else {
            style = "height : " + (nbEquipes * 100) + "px";
        }
        return style;
    }

    /**
     * Permet de créer un graphique de classement d'équipes
     *
     * @param categorieEquipe
     * @param classement
     * @param totalPoints
     * @return
     */
    public HorizontalBarChartModel createModel(CategorieEquipe categorieEquipe,
            Map<Equipe, Long> classement, Long totalPoints) {
        HorizontalBarChartModel chartModel = null;
        try {
            chartModel = new HorizontalBarChartModel();

            ChartSeries series = new ChartSeries(
                    MarathonUtils.getValueFromBundle("TESTCLASSEMENT.SCORES", MarathonConstants.BUNDLE_NAME)
            );
            classement.entrySet().stream().forEach((entry) -> {
                series.set(entry.getKey().getNom(), entry.getValue());
            });

            chartModel.addSeries(series);
            chartModel.setTitle(
                    MarathonUtils.getValueFromBundle("TEMPLATE.CLASSEMENT", MarathonConstants.BUNDLE_NAME)
                    + " - "
                    + categorieEquipe.getLibelle()
            );
            chartModel.setLegendPosition("e");
            chartModel.setStacked(true);

            Axis xAxis = chartModel.getAxis(AxisType.X);
            xAxis.setLabel(
                    MarathonUtils.getValueFromBundle("TESTCLASSEMENT.POINTS", MarathonConstants.BUNDLE_NAME)
            );
            xAxis.setMin(0);
            xAxis.setMax(totalPoints);

            Axis yAxis = chartModel.getAxis(AxisType.Y);
            yAxis.setLabel(
                    MarathonUtils.getValueFromBundle("TESTCLASSEMENT.EQUIPES", MarathonConstants.BUNDLE_NAME)
            );
        } catch (Exception ex) {
            Logger.getLogger(ChartsBean.class.getName()).log(Level.SEVERE, null, ex);
            MarathonUtils.getValueFromBundle("MSG_ERROR_DB_LOAD", MarathonConstants.BUNDLE_NAME);
        }
        return chartModel;
    }
}
