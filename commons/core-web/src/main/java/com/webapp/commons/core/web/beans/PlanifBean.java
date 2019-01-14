/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.web.beans;

import com.webapp.commons.core.api.Task;
import com.webapp.commons.core.scheduler.Entities;
import com.webapp.commons.core.scheduler.MSJob;
import com.webapp.commons.core.scheduler.Planification;
import java.util.ServiceLoader;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author El Phenox
 */
@ManagedBean(name = "planif")
@ViewScoped
public class PlanifBean {

    private Planification planification = new Planification();

    //Comparaison
    private String valeur;
    private String valeur1;

    /**
     * Creates a new instance of Planif
     */
    public PlanifBean() {
        planification = new Planification();
    }

    public void Select() throws SchedulerException {
        ServiceLoader<Task> loader = ServiceLoader.load(Task.class);
        for (Task implClass : loader) {
            SchedulerFactory schedFact = new StdSchedulerFactory();

            Scheduler scheduler = schedFact.getScheduler();
            Entities s = new Entities();
            s.setName(implClass.getClass().getSimpleName());
            s.setDescription(implClass.getDescription());
            s.setGroup(implClass.getGroup());
            JobDetail job = newJob(MSJob.class).setJobData((JobDataMap) implClass)
                    .withIdentity(implClass.getClass().getSimpleName(), implClass.getGroup())
                    .build();

            switch (valeur) {
                case "quoti":
                    if (valeur1.equals("toujours")) {
                        Trigger toujoursQuoti = newTrigger()
                                .withIdentity("Toujours", "All")
                                .startAt(planification.getDateDebut())
                                .withSchedule(simpleSchedule()
                                        .withIntervalInHours(24)
                                        .repeatForever())
                                .build();
                        break;
                    } else {
                        Trigger allQuoti = newTrigger()
                                .withIdentity("Fin", "All")
                                .startAt(planification.getDateDebut())
                                .endAt(planification.getDailyEndDate())
                                .withSchedule(simpleSchedule()
                                        .withIntervalInHours(24)
                                        .withRepeatCount(planification.getDailyStep()))
                                .build();
                        break;
                    }

                case "hebdo":
                    if (valeur1.equals("toujours")) {
                        Trigger toujoursHebdo = newTrigger()
                                .withIdentity("Toujours", "All")
                                .startAt(planification.getDateDebut())
                                .withSchedule(simpleSchedule()
                                        .repeatForever())
                                .build();
                        break;
                    } else {
                        Trigger allHebdo = newTrigger()
                                .withIdentity("Fin", "All")
                                .startAt(planification.getDateDebut())
                                .endAt(planification.getWeeklyEndDate())
                                .withSchedule(simpleSchedule()
                                        .withIntervalInHours(planification.getEveryWeek() * 24)
                                        .withRepeatCount(planification.getWeekStep()))
                                .build();
                        break;
                    }

                case "men":
                    if (valeur1.equals("toujours")) {
                        Trigger toujoursMois = newTrigger()
                                .withIdentity("Toujours", "All")
                                .startAt(planification.getDateDebut())
                                .withSchedule(calendarIntervalSchedule()
                                        .withIntervalInMonths(planification.getEveryMonth())
                                )
                                .build();
                        break;
                    } else {
                        Trigger allMois = newTrigger()
                                .withIdentity("Fin", "All")
                                .startAt(planification.getDateDebut())
                                .endAt(planification.getMonthEndDate())
                                .withSchedule(simpleSchedule()
                                        .withRepeatCount(planification.getMonthStep()))
                                .build();
                        break;
                    }
                case "an":
                    if (valeur1.equals("toujours")) {
                        Trigger toujoursAn = newTrigger()
                                .withIdentity("Toujours", "All")
                                .startAt(planification.getDateDebut())
                                .withSchedule(calendarIntervalSchedule()
                                        .withIntervalInYears(planification.getEveryYear())
                                )
                                .build();
                        break;
                    } else {
                        Trigger allAn = newTrigger()
                                .withIdentity("Fin", "All")
                                .startAt(planification.getDateDebut())
                                .endAt(planification.getYearEndDate())
                                .withSchedule(simpleSchedule()
                                        .withIntervalInHours(365 * 24 * planification.getEveryYear())
                                        .withRepeatCount(planification.getYearStep())
                                )
                                .build();
                        break;
                    }
            }
        }
        planification = new Planification();
    }

    //Getters and setters
    public Planification getPlanification() {
        return planification;
    }

    public void setPlanification(Planification planification) {
        this.planification = planification;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur1() {
        return valeur1;
    }

    public void setValeur1(String valeur1) {
        this.valeur1 = valeur1;
    }

}
