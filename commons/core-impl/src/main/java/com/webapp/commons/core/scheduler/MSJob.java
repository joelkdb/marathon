/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.scheduler;


import com.webapp.commons.core.api.Task;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 *
 * @author El Phenox
 */
public class MSJob implements Job {
    
    private Task task;

    public MSJob() {
    }

    public MSJob(Task task) {
        this.task = task;
    }
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        this.task.run();
    } 
}
