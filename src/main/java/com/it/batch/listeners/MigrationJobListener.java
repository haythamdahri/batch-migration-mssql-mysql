package com.it.batch.listeners;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author Haytham DAHRI
 */
@Component
@Log4j2
public class MigrationJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("BEFORE JOB EXECUTION");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("AFTER JOB EXECUTION | JOB STATUS: " + jobExecution.getStatus());
    }
}
