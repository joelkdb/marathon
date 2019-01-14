/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.scheduler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author El Phenox
 */
public class Planification implements Serializable {

    private Date dateDebut;
    //Quotidien
    private Date dailyEndDate;
    private Integer dailyStep = 1;
    //Hebdomadaire
    private Date weeklyEndDate;
    private List<String> days = new ArrayList<>();
    
    //  private String[] tabHebdo = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    private Integer weekStep = 1;
    private Integer everyWeek = 1;
    //Mois
    private Date monthEndDate;
    private Integer everyMonth = 1;
    private Integer monthStep = 1;
    //Année
    private Date yearEndDate;
    private Integer everyYear = 1;
    private Integer yearStep = 1;

    //Getters and setters
    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDailyEndDate() {
        return dailyEndDate;
    }

    public void setDailyEndDate(Date dailyEndDate) {
        this.dailyEndDate = dailyEndDate;
    }

    public Integer getDailyStep() {
        return dailyStep;
    }

    public void setDailyStep(Integer dailyStep) {
        this.dailyStep = dailyStep;
    }

    public Date getWeeklyEndDate() {
        return weeklyEndDate;
    }

    public void setWeeklyEndDate(Date weeklyEndDate) {
        this.weeklyEndDate = weeklyEndDate;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public Integer getWeekStep() {
        return weekStep;
    }

    public void setWeekStep(Integer weekStep) {
        this.weekStep = weekStep;
    }

    public Integer getEveryWeek() {
        return everyWeek;
    }

    public void setEveryWeek(Integer everyWeek) {
        this.everyWeek = everyWeek;
    }

    public Date getMonthEndDate() {
        return monthEndDate;
    }

    public void setMonthEndDate(Date monthEndDate) {
        this.monthEndDate = monthEndDate;
    }

    public Integer getEveryMonth() {
        return everyMonth;
    }

    public void setEveryMonth(Integer everyMonth) {
        this.everyMonth = everyMonth;
    }

    public Integer getMonthStep() {
        return monthStep;
    }

    public void setMonthStep(Integer monthStep) {
        this.monthStep = monthStep;
    }

    public Date getYearEndDate() {
        return yearEndDate;
    }

    public void setYearEndDate(Date yearEndDate) {
        this.yearEndDate = yearEndDate;
    }

    public Integer getEveryYear() {
        return everyYear;
    }

    public void setEveryYear(Integer everyYear) {
        this.everyYear = everyYear;
    }

    public Integer getYearStep() {
        return yearStep;
    }

    public void setYearStep(Integer yearStep) {
        this.yearStep = yearStep;
    }

}
