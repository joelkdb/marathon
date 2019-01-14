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
import javax.faces.bean.RequestScoped;
import com.webapp.commons.core.api.Task;
import com.webapp.commons.core.scheduler.Entities;
import com.webapp.commons.core.scheduler.MSJob;
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
@ManagedBean
@RequestScoped
public class ExecuteAutomaticBean implements Serializable {

    @SuppressWarnings("FieldMayBeFinal")
    private List<Entities> list = new ArrayList<>();

    /**
     * Creates a new instance of Liste
     *
     * @throws org.quartz.SchedulerException
     */
    public ExecuteAutomaticBean() throws SchedulerException {
    }

    public void restore() throws SchedulerException {
        ServiceLoader<Task> loader = ServiceLoader.load(Task.class);
        for (Task implClass : loader) {
            SchedulerFactory schedFact = new StdSchedulerFactory();

            Scheduler scheduler = schedFact.getScheduler();
            Entities s = new Entities();
            s.setName(implClass.getClass().getSimpleName());
            s.setDescription(implClass.getDescription());
            s.setGroup(implClass.getGroup());
            list.add(s);
            JobDetail job = newJob(MSJob.class).setJobData((JobDataMap) implClass)
                    .withIdentity(implClass.getClass().getSimpleName(), implClass.getGroup())
                    .build();

            // Trigger the job to run now
            Trigger automatic = newTrigger()
                    .withIdentity("run", "all")
                    .startNow()
                    .withSchedule(simpleSchedule())
                    .build();
            scheduler.scheduleJob(job, automatic);
        }
    }

        //Getters, setters
    public List<Entities> getList() {
        return list;
    }

    public void setList(List<Entities> list) {
        this.list = list;
    }

}
