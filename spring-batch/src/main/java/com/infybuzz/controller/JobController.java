package com.infybuzz.controller;

import com.infybuzz.model.JobParamsRequest;
import com.infybuzz.service.JobService;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;

    private final JobOperator jobOperator;

    public JobController(JobService jobService, JobOperator jobOperator) {
        this.jobService = jobService;
        this.jobOperator = jobOperator;
    }

    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName, @RequestBody List<JobParamsRequest> jobParamsRequestList) {
        jobService.startJob(jobName, jobParamsRequestList);
        return "Job started";
    }

    @GetMapping("/stop/{jobExecutionId}")
    public String stopJob(@PathVariable Long jobExecutionId) {
        try {
            jobOperator.stop(jobExecutionId);
        } catch (NoSuchJobExecutionException | JobExecutionNotRunningException e) {
            e.printStackTrace();
        }
        return "Job stopped";
    }
}
