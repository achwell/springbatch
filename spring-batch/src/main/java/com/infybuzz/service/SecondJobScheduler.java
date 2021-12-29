package com.infybuzz.service;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecondJobScheduler {

    private final JobLauncher jobLauncher;
    private final Job chunkJob;

    public SecondJobScheduler(JobLauncher jobLauncher, @Qualifier("chunkJob") Job chunkJob) {
        this.jobLauncher = jobLauncher;
        this.chunkJob = chunkJob;
    }

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void secondJobStarter() {
        Map<String, JobParameter> params = new HashMap<>();
        params.put("currentTime", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(params);
        try {
            final JobExecution jobExecution = jobLauncher.run(chunkJob, jobParameters);
            System.out.println("Job Execution ID = " + jobExecution.getJobId());
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }

    }
}
