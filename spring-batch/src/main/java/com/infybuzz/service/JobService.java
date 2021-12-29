package com.infybuzz.service;

import com.infybuzz.model.JobParamsRequest;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class JobService {

    private final JobLauncher jobLauncher;

    private final Job taskletJob;
    private final Job chunkJob;
    private final Job chunkJobWithoutProcessor;
    private final Job combinedJob;

    public JobService(JobLauncher jobLauncher, @Qualifier("taskletJob") Job taskletJob, @Qualifier("chunkJob") Job chunkJob, @Qualifier("chunkJobWithoutProcessor") Job chunkJobWithoutProcessor, @Qualifier("combinedJob") Job combinedJob) {
        this.jobLauncher = jobLauncher;
        this.taskletJob = taskletJob;
        this.chunkJob = chunkJob;
        this.chunkJobWithoutProcessor = chunkJobWithoutProcessor;
        this.combinedJob = combinedJob;
    }

    @Async
    public void startJob(String jobName, List<JobParamsRequest> jobParamsRequestList) {
        Job job;
        if (Objects.equals(jobName, "First Job")) {
            job = taskletJob;
        } else if (Objects.equals(jobName, "Chunk Job")) {
            job = chunkJob;
        } else if (Objects.equals(jobName, "Chunk Job Without Processor")) {
            job = chunkJobWithoutProcessor;
        } else if (Objects.equals(jobName, "Combined Job")) {
            job = combinedJob;
        } else {
            return;
        }
        Map<String, JobParameter> params = new HashMap<>();
        params.put("currentTime", new JobParameter(System.currentTimeMillis()));
        jobParamsRequestList.forEach(jobParamsRequest -> params.put(jobParamsRequest.getParamKey(), new JobParameter(jobParamsRequest.getParamValue())));
        JobParameters jobParameters = new JobParameters(params);
        try {
            final JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            System.out.println("Job Execution ID = " + jobExecution.getJobId());
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
